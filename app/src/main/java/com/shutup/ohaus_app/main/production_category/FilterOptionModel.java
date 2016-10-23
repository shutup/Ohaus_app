package com.shutup.ohaus_app.main.production_category;

/**
 * Created by shutup on 2016/10/22.
 */

public class FilterOptionModel {
    private int type;
    private String name;
    private String filterOptionName;
    private boolean isSelected;

    public FilterOptionModel(int type, String name, String filterOptionName ) {
         this(type, name, filterOptionName, false);
    }

    public FilterOptionModel(int type, String name, String filterOptionName ,boolean isSelected) {
        this.type = type;
        this.name = name;
        this.filterOptionName = filterOptionName;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilterOptionName() {
        return filterOptionName;
    }

    public void setFilterOptionName(String filterOptionName) {
        this.filterOptionName = filterOptionName;
    }
}
