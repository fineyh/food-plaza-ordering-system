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

    //根据id，返回menu对象
    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }

    //提供方法，返回表的行数
    public long getCount() {
        return (long) menuDAO.queryCount("select count(*) from menu");
    }
}
