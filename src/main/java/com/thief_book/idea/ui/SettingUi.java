package com.thief_book.idea.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.thief_book.idea.PersistentState;
import com.thief_book.idea.util.SettingUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SettingUi {

//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("SettingUi");
//        frame.setContentPane(new SettingUi().mainPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }


    public JPanel mainPanel;
    public JLabel chooseFileLabel;
    public JLabel label1;
    public JButton button2;
    public JLabel Label3;
    public JComboBox fontSize;
    public JComboBox fontType;
    public JLabel label5;
    public JLabel label4;
    public JTextField before;
    public JTextField next;
    public JTextField bookPathText;
    public JLabel l;
    public JLabel fontSizeLabel;
	public JLabel label6;
    public JComboBox lineCount;


    public SettingUi() {
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(mainPanel);
                File file = fileChooser.getSelectedFile();
                bookPathText.setText( file.getPath());
            }
        });
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();

        for (int i = 11; i < 25; i ++) {
            defaultComboBoxModel1.addElement(i + "");
        }

        fontSize.setModel(defaultComboBoxModel1);
        fontSize.setToolTipText("");
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        for (String font : SettingUtil.getAllFontType()) {
            defaultComboBoxModel2.addElement(font);
        }
        fontType.setModel(defaultComboBoxModel2);
        fontType.setToolTipText("");

        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        for (int i = 1; i < 11; i ++) {
            defaultComboBoxModel3.addElement(i + "");
        }
        lineCount.setModel(defaultComboBoxModel3);
        lineCount.setToolTipText("");
    }



    public void innit(PersistentState persistentState) {
        if (fontSize.getSelectedItem() == null) {
            fontSize.setSelectedItem(14);
        }
        bookPathText.setText(persistentState.getBookPathText());
        fontSize.setSelectedItem(persistentState.getFontSize());
        fontType.setSelectedItem(persistentState.getFontType());
        before.setText(persistentState.getBefore());
        next.setText(persistentState.getNext());
        lineCount.setSelectedItem(Integer.parseInt(persistentState.getLineCount()));

        before.setEditable(false);
        next.setEditable(false);
        bookPathText.setEditable(false);


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
        Label3 = new JLabel();
        Label3.setText("自动翻页(秒):");
        mainPanel.add(Label3, cc.xy(3, 7));
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