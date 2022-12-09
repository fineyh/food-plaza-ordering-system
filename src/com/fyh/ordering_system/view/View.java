package com.fyh.ordering_system.view;

import com.fyh.ordering_system.domain.Bill;
import com.fyh.ordering_system.domain.DiningTable;
import com.fyh.ordering_system.domain.Employee;
import com.fyh.ordering_system.domain.Menu;
import com.fyh.ordering_system.service.BillService;
import com.fyh.ordering_system.service.DiningTableService;
import com.fyh.ordering_system.service.EmployeeService;
import com.fyh.ordering_system.service.MenuService;
import com.fyh.ordering_system.utils.Utility;

import java.util.List;

public class View {
    public static void main(String[] args) {
        new View().mainMenu();
    }

    //显示所有餐桌状态
    public void listDiningTable() {
        List<DiningTable> list = diningTableService.list();
        System.out.println("\nTable Number\tTable State");
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }
        System.out.println("=========Display Complete=========");
    }

    //完成订座
    public void orderDiningTable() {
        System.out.println("=========Order Dining Table=========");
        System.out.println("Please select the table number (-1 to exit)");
        int orderId = Utility.readInt();
        if(orderId == -1) {
            System.out.println("=========Cancel the table reservation=========");
            return;
        }

        //该方法得到的是 Y 或者 N
        char key = Utility.readConfirmSelection();
        if (key == 'Y') { // 要预定
            //根据 orderId 返回对应DiningTable对象，如果为null，说明该对象不存在
            DiningTable diningTable = diningTableService.getDiningTableById(orderId);
            if (diningTable == null) { // 输入餐桌不存在
                System.out.println("=========The reserved table does not exist=========");
                return;
            }
            //判断该餐桌的状态是否为“empty”
            if (!("empty".equals(diningTable.getState()))) { // 说明当前这张餐桌不是“empty”状态
                System.out.println("=========The table has been reserved or is being served=========");
                return;
            }

            //接收预定信息
            System.out.print("Name of the reservation person: ");
            String orderName = Utility.readString(50);
            System.out.print("Phone number of the reservation person: ");
            String orderTel = Utility.readString(50);

            //更新餐桌状态
            if (diningTableService.orderDiningTable(orderId, orderName, orderTel)) {
                System.out.println("=========Order the table successfully=========");
            } else {
                System.out.println("=========Order the table failed=========");
            }

        } else {
            System.out.println("=========Cancel the table reservation=========");
        }
    }

    //显示所有菜品
    public void listMenu() {
        List<Menu> list = menuService.list();
        System.out.println("\nDish No\t\tName\t\tType\t\tPrice");
        for (Menu menu : list) {
            System.out.println(menu);
        }
        System.out.println("=========Display Complete=========");
    }

    //完成点餐
    public void orderMenu(){
        System.out.println("=========Order food service=========");

        System.out.print("Please enter the table number for ordering(-1 to exit): "); // 点餐的桌号
        int orderDiningTableId = Utility.readInt();
        if (orderDiningTableId == -1) {
            System.out.println("=========Cancel the table reservation=========");
            return;
        }

        System.out.print("Please enter the order menu id of the order(-1 to exit): "); // 点餐的菜品号
        int orderMenuId = Utility.readInt();
        if (orderMenuId == -1) {
            System.out.println("=========Cancel the table reservation=========");
            return;
        }

        System.out.print("Please enter the number of the order(-1 to exit): "); // 点餐的菜品量
        int orderNums = Utility.readInt();
        if (orderNums == -1) {
            System.out.println("=========Cancel the table reservation=========");
            return;
        }

        //验证餐桌号是否存在
        DiningTable diningTable = diningTableService.getDiningTableById(orderDiningTableId);
        if (diningTable == null) {
            System.out.println("=========Table number does not exist=========");
            return;
        }

        //验证菜品编号
        Menu menu = menuService.getMenuById(orderMenuId);
        if (menu == null) {
            System.out.println("=========Dish's id does not exist=========");
            return;
        }

        //点餐
        if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
            System.out.println("=========Order successfully=========");
        } else {
            System.out.println("=========Order failed=========");
        }
    }

    //显示账单信息
    public void listBill() {
        List<Bill> bills = billService.list();
        System.out.println("\nID\t\tDish No\t\tDish number\t\tPrice\t\tTable ID\t\tDate\t\t\t\t\t\t\tState");
        for (Bill bill : bills) {
            System.out.println(bill);
        }
        System.out.println("=========Display Complete=========");
    }

    //控制是否退出菜单
    private boolean loop = true;
    private String key = ""; // 接收用户的选择
    //定义 EmployeeService 的属性
    private EmployeeService employeeService = new EmployeeService();
    //定义 DiningTableService 的属性
    private DiningTableService diningTableService = new DiningTableService();
    //定义 MenuService 的属性
    private MenuService menuService = new MenuService();
    //定义 BillService 的属性
    private BillService billService= new BillService();

    //显示主菜单
    public void mainMenu() {
        while (loop) {
            System.out.println("=========Food Plaza Ordering System=========");
            System.out.println("\t\t 1 Log in to the system");
            System.out.println("\t\t 2 Exit the system");
            System.out.print("Please enter your choice: ");
            key = Utility.readString(1);

            switch (key) {
                case "1":
                    System.out.print("Account Number: ");
                    String empId = Utility.readString(50);
                    System.out.print("Password: ");
                    String pwd = Utility.readString(50);
                    //到数据库去判断
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId,pwd);
                    if (employee != null) { // 说明存在该用户
                        System.out.println("=========Log in successfully[" + employee.getName() + "]=========\n");
                        //显示二级菜单
                        while (loop) {
                            System.out.println("\n=========Food Plaza Ordering System(Secondary menu)=========");
                            System.out.println("\t\t 1 Show table status");
                            System.out.println("\t\t 2 Order a table");
                            System.out.println("\t\t 3 Show all dishes");
                            System.out.println("\t\t 4 Order service");
                            System.out.println("\t\t 5 View bill");
                            System.out.println("\t\t 6 Settle accounts");
                            System.out.println("\t\t 7 Exit");
                            System.out.print("Please enter your choice: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    listDiningTable(); //显示餐桌状态
                                    break;
                                case "2":
                                    orderDiningTable(); //预定餐桌
                                    break;
                                case "3":
                                    listMenu(); //显示所有菜品
                                    break;
                                case "4":
                                    orderMenu(); //点餐
                                    break;
                                case "5":
                                    listBill();
                                    break;
                                case "6":
                                    System.out.println("Settle accounts");
                                    break;
                                case "7":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("Your input is incorrect, please retype.");
                                    break;
                            }

                        }
                    } else {
                        System.out.println("=========Log in failed=========");
                    }
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("Your input is incorrect, please retype.");

            }
        }
        System.out.println("You have exited the system...");
    }
}
