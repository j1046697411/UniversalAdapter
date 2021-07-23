package org.jzl.android.recyclerview.core.animation;

import java.io.Serializable;
import java.util.Date;

public class Animation implements Serializable {

    private int icon;
    private String title;
    private String description;
    private Date time;

    public Animation() {
    }

    public Animation(int icon, String title, String description, Date time) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.time = time;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
