package com.thief_book.idea;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainUi implements ToolWindowFactory {

    private PersistentState persistentState = PersistentState.getInstance();

    private JPanel panel;

    //缓存文件页数所对应的seek，以免每次跳页后重新读取文件
    Map<Integer, Long> seekDictionary = new LinkedHashMap<>();

    private int cacheInterval = 200;

    // 文件路径
    private String bookFile = persistentState.getBookPathText();

    private String type = persistentState.getFontType();

    private String size = persistentState.getFontSize();

    private Integer lineCount= Integer.parseInt(persistentState.getLineCount());

    private Integer lineSpace= Integer.parseInt(persistentState.getLineSpace());

    JTextArea textArea;

    JTextField current;

    JLabel total = new JLabel();

    //
    long seek = 0;

    //总行数
    int totalLine = 0;

    //当前行
    int currentLine = 0;

    private String welcome = "Memory leak detection has started....";

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        try {
            panel = new JPanel();

            textArea = new JTextArea();
            textArea.append("");
            textArea.setOpaque(false);
            textArea.setRows(1);
            textArea.setColumns(50);
            textArea.setTabSize(14);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setFont(new Font(type, Font.PLAIN, Integer.parseInt(size)));


            panel.add(textArea, BorderLayout.WEST);

            JPanel panelRight = new JPanel();
            // 当前行
            current = new JTextField("current line:");
            current.setText(currentLine/ lineCount + "");
            current.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    //判断按下的键是否是回车键
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        try {
                            String input = current.getText();
                            String inputCurrent = input.split("/")[0].trim();

                            int i = Integer.parseInt(inputCurrent);
                            if (i <= 1) {
                                seek = 0;
                                currentLine = 0;
                            } else {
                                currentLine =(i - 1) * lineCount;
                                countSeek();
                            }
                            textArea.setText(readBook());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            textArea.setText(e1.toString());
                        }

                    }
                }
            });

            // 总行数
            total.setText("/" + (totalLine%lineCount == 0 ? totalLine/lineCount : totalLine/lineCount + 1));

            panelRight.add(current, BorderLayout.EAST);
            panelRight.add(total, BorderLayout.EAST);

            //重新加载设置
            JButton refresh = new JButton("刷新");
            refresh.addActionListener(e -> {
                try {
                    persistentState = PersistentState.getInstanceForce();

                    if (StringUtils.isEmpty(persistentState.getBookPathText())||!bookFile.equals(persistentState.getBookPathText())) {
                        bookFile = persistentState.getBookPathText();
                        currentLine = 0;
                        seek = 0;
                        seekDictionary.clear();
                        totalLine = countLine();
                        countSeek();
                    }
                    else  {
                        // 初始化当前行数
                        if (StringUtils.isNotEmpty(persistentState.getCurrentLine())) {
                            currentLine = Integer.parseInt(persistentState.getCurrentLine());
                        }
                        if (seekDictionary.size() <= 5 || totalLine == 0) {
                            totalLine = countLine();
                            countSeek();
                        }
                    }

                    type = persistentState.getFontType();
                    size = persistentState.getFontSize();
                    lineCount = Integer.parseInt(persistentState.getLineCount());
                    lineSpace =Integer.parseInt(persistentState.getLineSpace());
                    textArea.setText("已刷新");
                    current.setText(" " + currentLine/lineCount);
                    total.setText("/" + (totalLine%lineCount == 0 ? totalLine/lineCount : totalLine/lineCount + 1));
                    textArea.setFont(new Font(type, Font.PLAIN, Integer.parseInt(size)));
                } catch (Exception newE) {
                    newE.printStackTrace();
                }
            });
            panelRight.add(refresh, BorderLayout.EAST);

            //上一页
            JButton afterB = new JButton("上页");
            afterB.addActionListener(e -> {
                if (currentLine > totalLine) {
                    return;
                }
                if (currentLine/lineCount > 1) {
                    if (currentLine % lineCount == 0) {
                        currentLine = currentLine - lineCount * 2;
                    }
                    else {
                        while (currentLine % lineCount != 0) {
                            currentLine --;
                        }
                        currentLine -= lineCount;
                    }
                    try {
                        countSeek();
                        textArea.setText(readBook());
                        current.setText(" " + currentLine/lineCount);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            });

            panelRight.add(afterB, BorderLayout.EAST);

            //下一页
            JButton nextB = new JButton("下页");

            nextB.addActionListener(e -> {

                if (currentLine < totalLine) {
                    try {
                        if (currentLine/lineCount <= 1) {
                            countSeek();
                        }
                        textArea.setText(readBook());
                        current.setText(" " + (currentLine%lineCount == 0 ? currentLine / lineCount : currentLine / lineCount + 1) );
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            });

            afterB.registerKeyboardAction(afterB.getActionListeners()[0], KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.ALT_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
            nextB.registerKeyboardAction(nextB.getActionListeners()[0], KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.ALT_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

            panelRight.add(nextB, BorderLayout.EAST);
            panel.add(panelRight, BorderLayout.EAST);

            // 界面加载完先设置标语
            textArea.setText(welcome);

            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            Content content = contentFactory.createContent(panel, "Control", false);
            toolWindow.getContentManager().addContent(content);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public String readBook() throws IOException {
        RandomAccessFile ra = null;
        String str = "";
        String nStr = "";
        try {
            ra = new RandomAccessFile(bookFile, "r");
            ra.seek(seek);
            for (Integer j = 0; j < lineSpace; j++) {
                nStr+="\n";
            }
            String temp;
            for (Integer i = 0; i < lineCount &&  (temp = ra.readLine()) != null; i++) {
                str+= new String(temp.getBytes("ISO-8859-1"), "gbk")+nStr;
                currentLine ++;
            }
            //实例化当前行数
            persistentState.setCurrentLine(String.valueOf(currentLine));
            seek = ra.getFilePointer();
            if (currentLine % cacheInterval == 0) {
                seekDictionary.put(currentLine, seek);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ra != null) {
                ra.close();
            }
        }
        return str;
    }

    // 查询总行数
    public int countLine() throws IOException {
        RandomAccessFile ra = null;

        try {
            ra = new RandomAccessFile(bookFile, "r");
            int i = 0;
            seekDictionary.put(0,ra.getFilePointer());
            while (ra.readLine() != null) {
                i++;
                if (i % cacheInterval == 0) {
                    seekDictionary.put(i, ra.getFilePointer());
                }
            }

            return i;

        } catch (Exception e) {
            e.printStackTrace();
            return Integer.MAX_VALUE;
        } finally {
            ra.close();
        }
    }


    public void countSeek() throws IOException {

        RandomAccessFile ra = null;

        try {
            if (seekDictionary.containsKey(currentLine)) {
                this.seek = seekDictionary.get(currentLine);
            }
            else {
                ra = new RandomAccessFile(bookFile, "r");
                int line = 0;
                for (int i = 0; cacheInterval * i < currentLine; i ++){
                    line = cacheInterval * i;
                    ra.seek(seekDictionary.get(line));
                }
                while (ra.readLine() != null) {
                    line++;
                    if (line == currentLine) {
                        this.seek = ra.getFilePointer();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ra != null) {
                ra.close();
            }
        }
    }

}
