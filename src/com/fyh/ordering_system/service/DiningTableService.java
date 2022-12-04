package com.fyh.ordering_system.service;

import com.fyh.ordering_system.dao.DiningTableDAO;
import com.fyh.ordering_system.domain.DiningTable;

import java.util.List;

public class DiningTableService { // 业务层
    //定义一个 DiningTableDAO 对象
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //返回所有餐桌的信息
    public List<DiningTable> list() {
        return diningTableDAO.queryMulti("select id, state from diningTable", DiningTable.class);
    }
}
