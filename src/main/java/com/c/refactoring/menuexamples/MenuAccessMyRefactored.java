package com.c.refactoring.menuexamples;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MenuAccessMyRefactored {

    public void setAuthorizationsInEachMenus(List<MenuItem> menuItems, Role[] roles) {

        if (roles == null) {
            return;
        }

        menuItems.stream().forEach(menuItem -> setAccessOfMenuItem(roles, menuItem));

    }

    void setAccessOfMenuItem(Role[] roles, MenuItem menuItem) {
        if (doesUserHaveTheRole(roles, menuItem.getWriteAccessRole())) {
            menuItem.setAccess(Constants.WRITE);
            menuItem.setVisible(true);
            return;
        }


        if (doesUserHaveTheRole(roles, menuItem.getReadAccessRole())) {
            menuItem.setAccess(Constants.READ);
            menuItem.setVisible(true);
        }
    }

    boolean doesUserHaveTheRole(Role[] roles, String readAccessRole) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(readAccessRole));
    }
}
