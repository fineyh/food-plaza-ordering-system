package com.fyh.ordering_system.view;

import com.fyh.ordering_system.domain.DiningTable;
import com.fyh.ordering_system.domain.Employee;
import com.fyh.ordering_system.service.DiningTableService;
import com.fyh.ordering_system.service.EmployeeService;
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

    //控制是否退出菜单
    private boolean loop = true;
    private String key = ""; // 接收用户的选择
    //定义 EmployeeService 属性
    private EmployeeService employeeService = new EmployeeService();
    //调用 DiningTable 的属性
    private DiningTableService diningTableService = new DiningTableService();

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
                            System.out.println("=========Food Plaza Ordering System(Secondary menu)=========");
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
                                    System.out.println("Show all dishes");
                                    break;
                                case "4":
                                    System.out.println("Order service");
                                    break;
                                case "5":
                                    System.out.println("View bill");
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
