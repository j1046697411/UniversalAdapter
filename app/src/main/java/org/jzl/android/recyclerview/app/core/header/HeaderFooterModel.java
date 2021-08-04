package org.jzl.android.recyclerview.app.core.header;

import java.io.Serializable;

public class HeaderFooterModel implements Serializable {
    private String title;
    private String description;

    public HeaderFooterModel() {
    }

    public HeaderFooterModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
