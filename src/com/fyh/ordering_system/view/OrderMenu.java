package com.fyh.ordering_system.view;

import com.fyh.ordering_system.service.BillService;
import com.fyh.ordering_system.service.DiningTableService;
import com.fyh.ordering_system.service.MenuService;

import javax.swing.*;
import java.awt.*;

public class OrderMenu extends JFrame {
    Container contentPane = this.getContentPane();//创建视图
    JLabel tableLabel = new JLabel("Reserved table:         Table");
    JComboBox tableComboBox =new JComboBox();//餐桌号
    JLabel orderNameLabel = new JLabel("Dish Name:");
    JLabel orderTelLabel = new JLabel("Dish Number:");
    JComboBox dishName = new JComboBox();//菜品
    JComboBox dishNum = new JComboBox();//菜品数量
    JButton jButton = new JButton("Order");

    public OrderMenu() {
        //定义 DiningTableService 的属性
        DiningTableService diningTableService = new DiningTableService();
        //定义 MenuService 的属性
        MenuService menuService = new MenuService();
        //定义 BillService 的属性
        BillService billService= new BillService();

        //初始化窗体

        // 设置标题
        setTitle("Order Menu");
        // 设置大小
        setSize(400, 300);
        setLocationRelativeTo(null);
        // 设置是否可以改变大小
        setResizable(false);
        // 设置退出后进行的操作
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentPane.setLayout(new BorderLayout());

        JPanel showPanel=new JPanel();//创建面板
        showPanel.setLayout(null);

        //创建餐桌子项
//        jc.addItem("--Please choose--");
        for (int i = 1; i <= diningTableService.getCount(); i++) {
            String tableState = diningTableService.getDiningTableById(i).getState();
            if (tableState.equals("Ordered") || tableState.equals("Eating")) { //餐桌为empty或eating时都可以加菜
                tableComboBox.addItem(diningTableService.getDiningTableById(i).getId());
            }
        }

        //创建菜品子项
        for (int i = 1; i <= menuService.getCount(); i++) {
            dishName.addItem(menuService.getMenuById(i).getName());
        }

        //创建菜品数量子项
        for (int i = 1; i <= 10; i++) {
            dishNum.addItem(i);
        }

        //设置各组件位置&大小
        tableLabel.setBounds(80,50,150,30);
        tableComboBox.setBounds(240,50,60,30);
        orderNameLabel.setBounds(80,90,100,30);
        dishName.setBounds(170,90,130,30);
        orderTelLabel.setBounds(80,130,100,30);
        dishNum.setBounds(170,130,130,30);
        jButton.setBounds(150,200,100,30);

        showPanel.add(tableLabel);
        showPanel.add(tableComboBox);//把下拉框添加到面板中
        showPanel.add(orderNameLabel);
        showPanel.add(dishName);
        showPanel.add(orderTelLabel);
        showPanel.add(dishNum);
        showPanel.add(jButton);

        contentPane.add(showPanel);//添加到容器里

        setVisible(true);

        //按钮监听器
        jButton.addActionListener(e -> {
            int orderDiningTableId = (int) tableComboBox.getSelectedItem();
            int orderMenuId = menuService.getMenuByDish(dishName.getSelectedItem().toString()).getId();
            int orderNums = (int)dishNum.getSelectedItem();
            if (billService.orderMenu(orderMenuId, orderNums, orderDiningTableId)) {
                JOptionPane.showMessageDialog(null, "Order successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Order failed!", "Error", JOptionPane. ERROR_MESSAGE);
            }
        });
    }
}
