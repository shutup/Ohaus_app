package com.shutup.ohaus_app.main.production_category;

/**
 * Created by shutup on 16/9/13.
 */
public class ProductionCategoryMenuItem {

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
}
