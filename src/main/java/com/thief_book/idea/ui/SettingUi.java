package com.thief_book.idea.ui;


import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.thief_book.idea.PersistentState;
import com.thief_book.idea.util.SettingUtil;
import com.thief_book.idea.verify.InputLengthVerify;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SettingUi {

    public JPanel mainPanel;
    public JLabel chooseFileLabel;
    public JLabel label1;
    public JButton button2;
    public JLabel label3;
    public JComboBox<Integer> fontSize;
    public JComboBox<String> fontType;
    public JLabel label5;
    public JLabel label4;
    public JTextField before;
    public JTextField next;
    public JTextField bookPathText;
    public JLabel l;
    public JLabel fontSizeLabel;
    public JLabel label6;
    public JComboBox<Integer> lineCount;
    public JComboBox<Integer> lineSpace;
    public JLabel label7;


    public SettingUi() {
        button2.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            File downloadDir = new File(System.getProperty("user.home") + "\\Downloads");
            if (downloadDir.exists()) {
                fileChooser.setCurrentDirectory(downloadDir);
            }
            fileChooser.setFileFilter(new FileNameExtensionFilter("text", "txt"));
            fileChooser.showOpenDialog(mainPanel);
            File file = fileChooser.getSelectedFile();
            bookPathText.setText(file.getPath());
        });
        final DefaultComboBoxModel<Integer> defaultComboBoxModel1 = new DefaultComboBoxModel<>();

        for (int i = 11; i < 25; i++) {
            defaultComboBoxModel1.addElement(i);
        }

        fontSize.setModel(defaultComboBoxModel1);
        fontSize.setToolTipText("");
        final DefaultComboBoxModel<String> defaultComboBoxModel2 = new DefaultComboBoxModel<>();
        for (String font : SettingUtil.getAllFontType()) {
            defaultComboBoxModel2.addElement(font);
        }
        fontType.setModel(defaultComboBoxModel2);
        fontType.setToolTipText("");

        final DefaultComboBoxModel<Integer> defaultComboBoxModel3 = new DefaultComboBoxModel<>();
        for (int i = 1; i < 11; i++) {
            defaultComboBoxModel3.addElement(i);
        }
        lineCount.setModel(defaultComboBoxModel3);
        lineCount.setToolTipText("");

        final DefaultComboBoxModel<Integer> defaultComboBoxModel4 = new DefaultComboBoxModel<>();
        for (int i = 0; i < 3; i++) {
            defaultComboBoxModel4.addElement(i);
        }
        lineSpace.setModel(defaultComboBoxModel4);
        lineSpace.setToolTipText("");
    }


    public void innit(PersistentState persistentState) {
        if (fontSize.getSelectedItem() == null) {
            fontSize.setSelectedItem(14);
        } else {
            fontSize.setSelectedItem(persistentState.getFontSize());
        }
        bookPathText.setText(persistentState.getBookPathText());
        fontType.setSelectedItem(persistentState.getFontType());
        before.setText(persistentState.getBefore().toString());
        next.setText(persistentState.getNext().toString());
        lineCount.setSelectedItem(persistentState.getLineCount());
        lineSpace.setSelectedItem(persistentState.getLineSpace());

        bookPathText.setEditable(false);
        before.addKeyListener(new InputLengthVerify(1, before));
        next.addKeyListener(new InputLengthVerify(1, next));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FormLayout("fill:max(d;4px):noGrow,left:13dlu:noGrow,left:46dlu:noGrow,fill:203px:noGrow,fill:51px:noGrow,fill:max(d;4px):noGrow,fill:max(d;4px):noGrow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        chooseFileLabel = new JLabel();
        chooseFileLabel.setHorizontalAlignment(11);
        chooseFileLabel.setText("选择文件:");
        CellConstraints cc = new CellConstraints();
        mainPanel.add(chooseFileLabel, cc.xy(3, 3, CellConstraints.LEFT, CellConstraints.FILL));
        label1 = new JLabel();
        label1.setText("");
        mainPanel.add(label1, cc.xy(1, 3));
        button2 = new JButton();
        button2.setText("...");
        mainPanel.add(button2, cc.xy(5, 3, CellConstraints.CENTER, CellConstraints.CENTER));
        label3 = new JLabel();
        label3.setText("自动翻页(秒):");
        mainPanel.add(label3, cc.xy(3, 7));
        fontSize = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("11");
        defaultComboBoxModel1.addElement("12");
        defaultComboBoxModel1.addElement("13");
        defaultComboBoxModel1.addElement("14");
        defaultComboBoxModel1.addElement("15");
        defaultComboBoxModel1.addElement("16");
        defaultComboBoxModel1.addElement("17");
        defaultComboBoxModel1.addElement("18");
        defaultComboBoxModel1.addElement("19");
        defaultComboBoxModel1.addElement("20");
        defaultComboBoxModel1.addElement("21");
        defaultComboBoxModel1.addElement("22");
        defaultComboBoxModel1.addElement("23");
        defaultComboBoxModel1.addElement("24");
        fontSize.setModel(defaultComboBoxModel1);
        fontSize.setToolTipText("");
        DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        for (String font : SettingUtil.getAllFontType()) {
            defaultComboBoxModel2.addElement(font);
        }
        fontType.setModel(defaultComboBoxModel2);
        fontType.setToolTipText("");
        mainPanel.add(fontSize, cc.xy(4, 7));
        mainPanel.add(fontType, cc.xy(4, 9));
        label4 = new JLabel();
        label4.setText("上一页热键:");
        mainPanel.add(label4, cc.xy(3, 11));
        label5 = new JLabel();
        label5.setText("下一页热键:");
        mainPanel.add(label5, cc.xy(3, 13));
        before = new JTextField();
        before.setText("");
        mainPanel.add(before, cc.xy(4, 11, CellConstraints.FILL, CellConstraints.DEFAULT));
        next = new JTextField();
        mainPanel.add(next, cc.xy(4, 13, CellConstraints.FILL, CellConstraints.DEFAULT));
        bookPathText = new JTextField();
        mainPanel.add(bookPathText, cc.xy(4, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
        l = new JLabel();
        l.setText("");
        mainPanel.add(l, cc.xy(2, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}