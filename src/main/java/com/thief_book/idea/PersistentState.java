package com.thief_book.idea;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(
        name = "PersistentState",
        storages = {@Storage(
                value = "thief-book.xml"
        )}
)
public class PersistentState implements PersistentStateComponent<Element> {

    private static PersistentState persistentState;

    private String bookPathText;

    private String showFlag;

    private Integer fontSize;

    private String fontType;

    private String before;

    private String next;

    private Long currentLine;

    private Integer lineCount;

    private Integer lineSpace;



    public PersistentState() {
    }

    public static PersistentState getInstance() {
        if (persistentState == null) {
            persistentState = ServiceManager.getService(PersistentState.class);
        }
        return persistentState;
    }

    public static PersistentState getInstanceForce() {
        return ServiceManager.getService(PersistentState.class);
    }


    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("PersistentState");
        element.setAttribute("bookPath", this.getBookPathText());
        element.setAttribute("showFlag", this.getShowFlag());
        element.setAttribute("fontSize", this.getFontSize().toString());
        element.setAttribute("before", this.getBefore());
        element.setAttribute("next", this.getNext());
        element.setAttribute("currentLine", this.getCurrentLine().toString());
        element.setAttribute("fontType", this.getFontType());
        element.setAttribute("lineCount",this.getLineCount().toString());
        element.setAttribute("lineSpace",this.getLineSpace().toString());
        return element;
    }

    @Override
    public void loadState(@NotNull Element state) {
        this.setBookPathText(state.getAttributeValue("bookPath"));
        this.setShowFlag(state.getAttributeValue("showFlag"));
        this.setFontSize(Integer.valueOf(state.getAttributeValue("fontSize")));
        this.setBefore(state.getAttributeValue("before"));
        this.setNext(state.getAttributeValue("next"));
        this.setCurrentLine(Long.valueOf(state.getAttributeValue("currentLine")));
        this.setFontType(state.getAttributeValue("fontType"));
        this.setLineCount(Integer.valueOf(state.getAttributeValue("lineCount")));
        this.setLineSpace(Integer.valueOf(state.getAttributeValue("lineSpace")));

    }

    @Override
    public void noStateLoaded() {

    }

    public String getBookPathText() {
        return bookPathText;
    }

    public void setBookPathText(String bookPathText) {
        this.bookPathText = bookPathText;
    }

    public String getShowFlag() {
        return StringUtils.isEmpty(showFlag) ? "0" : this.showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getBefore() {
        return StringUtils.isEmpty(before) ? "Shift + ←" : this.before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getNext() {
        return StringUtils.isEmpty(next) ? "Shift + →" : this.next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getFontType() {
        return StringUtils.isEmpty(fontType) ? "Microsoft JhengHei" : this.fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Long getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(Long currentLine) {
        this.currentLine = currentLine;
    }

    public Integer getLineCount() {
        return lineCount;
    }

    public void setLineCount(Integer lineCount) {
        this.lineCount = lineCount;
    }

    public Integer getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(Integer lineSpace) {
        this.lineSpace = lineSpace;
    }
}
