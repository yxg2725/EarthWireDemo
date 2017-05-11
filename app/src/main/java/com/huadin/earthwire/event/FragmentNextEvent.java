package com.huadin.earthwire.event;

/**
 * Created by Jack Zhang on 2017/5/11.
 */

public class FragmentNextEvent {
    private String tag;
    private int viewId;

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
