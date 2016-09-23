package com.shutup.ohaus_app.model;

/**
 * Created by shutup on 16/9/13.
 */
public class ProductionCategoryMenuItem {



    private String id;
    private String menuTitle;
    private boolean isSelected;

    public ProductionCategoryMenuItem(String menuTitle, boolean isSelected) {
        this.menuTitle = menuTitle;
        this.isSelected = isSelected;
    }

    public ProductionCategoryMenuItem(String menuTitle) {
        this.menuTitle = menuTitle;
        this.isSelected = false;
    }

    public ProductionCategoryMenuItem(String id, String menuTitle, boolean isSelected) {
        this.id = id;
        this.menuTitle = menuTitle;
        this.isSelected = isSelected;
    }

    public ProductionCategoryMenuItem(String id, String menuTitle) {
        this.id = id;
        this.menuTitle = menuTitle;
        this.isSelected = false;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }



    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
