package org.jzl.android.recyclerview.app.core.databinding;

import java.io.Serializable;

public class DataBindingModel implements Serializable {

    private int icon;
    private String title;
    private int length;
    private String content;
    private int price;

    public DataBindingModel(int icon, String title, int length, String content, int price) {
        this.icon = icon;
        this.title = title;
        this.length = length;
        this.content = content;
        this.price = price;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
