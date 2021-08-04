package org.jzl.android.recyclerview.app.core.home;

import org.jzl.android.recyclerview.app.UniversalRecyclerViewActivity;

import java.io.Serializable;

public class HomeItem implements Serializable {

    private String name;
    private int icon;
    private Class<? extends UniversalRecyclerViewActivity.IUniversalRecyclerView> type;

    public HomeItem(String name, int icon, Class<? extends UniversalRecyclerViewActivity.IUniversalRecyclerView> type) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<? extends UniversalRecyclerViewActivity.IUniversalRecyclerView> getType() {
        return type;
    }

    public void setType(Class<? extends UniversalRecyclerViewActivity.IUniversalRecyclerView> type) {
        this.type = type;
    }
}
