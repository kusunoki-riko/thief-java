package com.thief_book.idea;

import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.thief_book.idea.ui.SettingUi;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Setting implements SearchableConfigurable {

    private SettingUi settingUi;

    private PersistentState persistentState = PersistentState.getInstance();


    @SuppressWarnings("FieldCanBeLocal")
    private final Project project;


    public Setting(@NotNull Project project) {
        this.project = project;
    }

    @NotNull
    @Override
    public String getId() {
        return "thief.id";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "thief-book-config";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {

        if (settingUi == null) {
            settingUi = new SettingUi();
        }
        settingUi.innit(persistentState);

        return settingUi.mainPanel;

    }

    @Override
    public boolean isModified() {
        return !StringUtils.equals(persistentState.getBookPathText(), settingUi.bookPathText.getText())
                || persistentState.getFontSize() != settingUi.fontSize.getSelectedItem()
                || persistentState.getBefore() != settingUi.before.getText().charAt(0)
                || persistentState.getNext() != settingUi.next.getText().charAt(0)
                || persistentState.getLineCount() != settingUi.lineCount.getSelectedItem()
                || persistentState.getLineSpace() != settingUi.lineSpace.getSelectedItem()
                || !StringUtils.equals(persistentState.getFontType(), settingUi.fontType.getSelectedItem().toString());

    }

    @Override
    public void apply() {
        persistentState.setBookPathText(settingUi.bookPathText.getText());
        persistentState.setFontSize(Integer.valueOf(settingUi.fontSize.getSelectedItem().toString()));
        persistentState.setBefore(settingUi.before.getText().charAt(0));
        persistentState.setNext(settingUi.next.getText().charAt(0));
        persistentState.setLineCount(Integer.valueOf(settingUi.lineCount.getSelectedItem().toString()));
        persistentState.setFontType(settingUi.fontType.getSelectedItem().toString());
        persistentState.setLineSpace(Integer.valueOf(settingUi.lineSpace.getSelectedItem().toString()));
    }

    @Override
    public void reset() {
        settingUi.bookPathText.setText(persistentState.getBookPathText());
        settingUi.fontSize.setSelectedItem(persistentState.getFontSize());
        settingUi.before.setText(persistentState.getBefore().toString());
        settingUi.next.setText(persistentState.getNext().toString());
        settingUi.fontType.setSelectedItem(persistentState);
        settingUi.lineCount.setSelectedItem(persistentState.getLineCount());
        settingUi.lineSpace.setSelectedItem(persistentState.getLineSpace());
    }

    @Override
    public void disposeUIResources() {

    }
}