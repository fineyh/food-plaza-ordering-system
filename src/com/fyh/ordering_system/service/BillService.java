package com.fyh.ordering_system.service;

import com.fyh.ordering_system.dao.BillDAO;
import com.fyh.ordering_system.domain.Bill;

import java.util.List;
import java.util.UUID;

public class BillService {
    //定义 BillDAO 属性
    private BillDAO billDAO = new BillDAO();
    //定义 MenuService 属性
    private MenuService menuService = new MenuService();
    //定义 DiningTableService 属性
    private DiningTableService diningTableService = new DiningTableService();

    /*
    点餐方法
    1. 生成账单
    2.需要更新对应餐桌的状态
    3.如果成功返回true，否则返回false
     */
    public boolean orderMenu(int menuId, int nums, int diningTableId) {
        //生成一个账单号，UUID
        String billID = UUID.randomUUID().toString();

        //将账单生成到bill表
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,now(),'Not paid out')",
                billID, menuId, nums, menuService.getMenuById(menuId).getPrice() * nums, diningTableId);

        if (update <= 0) {
            return false;
        }

        //需要更新对应餐桌的状态
        return diningTableService.updateDiningTableState(diningTableId, "Eating");
    }

    //返回所有的账单，提供给 View 调用
    public List<Bill> list() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }
}
