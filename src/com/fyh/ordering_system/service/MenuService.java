package com.fyh.ordering_system.service;

import com.fyh.ordering_system.dao.MenuDAO;
import com.fyh.ordering_system.domain.Menu;

import java.util.List;

public class MenuService {
    //定义 MenuDAO 属性
    private MenuDAO menuDAO = new MenuDAO();

    //返回所有的菜品，返回给界面使用
    public List<Menu> list() {
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }
}
