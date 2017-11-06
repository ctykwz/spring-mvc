package com.ctyk.mobile.template.model.request.message;

import java.io.Serializable;

/**
 * @author wei.yang on 2017/10/23 10:16.
 */
public class MessageModel implements Serializable {

    private static final long serialVersionUID = -6487839157908352120L;

    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
