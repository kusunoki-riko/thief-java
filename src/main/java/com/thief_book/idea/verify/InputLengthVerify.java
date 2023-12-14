package com.thief_book.idea.verify;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputLengthVerify extends KeyAdapter {

    private final Integer length;
    private final JTextField jTextField;

    public InputLengthVerify(Integer length, JTextField jTextField) {
        this.length = length;
        this.jTextField = jTextField;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (jTextField.getText().length() >= length) {
            jTextField.setText("");
        }
    }
}
