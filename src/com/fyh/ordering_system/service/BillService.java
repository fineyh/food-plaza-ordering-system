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

    //查看某张餐桌是否有未结账的账单
    public boolean hasPayBillByDiningTableId(int diningTableId) {
        Bill bill = billDAO.querySingle("select * from bill where diningTableId=? and state= 'Not paid out' limit 0, 1", Bill.class, diningTableId);
        return bill != null;
    }

    //完成结账[如果餐桌存在，并且该餐桌有未结账的账单]
    //传入结账方式payMode
    //如果成功，返回true，失败返回false
    public boolean payBill(int diningTableId, String payMode) {
        //1. 修改bill表
        int update = billDAO.update("update bill set state=? where diningTableId=? and state='Not paid out'", payMode, diningTableId );

        //如果更新没有成功，则表示失败
        if (update <= 0) {
            return false;
        }

        //2. 修改diningTable表
        //调用 DiningTableService 方法 完成更新
        if (diningTableService.updateDiningTableStateToFree(diningTableId, "empty")) {
            return false;
        }

        return true;
    }

}
