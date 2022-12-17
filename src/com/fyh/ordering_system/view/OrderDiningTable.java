package com.fyh.ordering_system.view;

import com.fyh.ordering_system.service.DiningTableService;

import javax.swing.*;
import java.awt.*;

public class OrderDiningTable extends JFrame {
    Container contentPane = this.getContentPane();//创建视图
    JLabel jcLabel = new JLabel("Choose table:             Table");
    JComboBox jc=new JComboBox();//创建下拉框
    JLabel orderNameLabel = new JLabel("Order Name:");
    JLabel orderTelLabel = new JLabel("Order Tel:");
    JTextField orderNameText = new JTextField();
    JTextField orderTelText = new JTextField();
    JButton jButton = new JButton("Order");

    public OrderDiningTable() {
        //定义 DiningTableService 的属性
        DiningTableService diningTableService = new DiningTableService();

        //初始化窗体

        // 设置标题
        setTitle("Order Dining Table");
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

        //创建子项
//        jc.addItem("--Please choose--");
        for (int i = 1; i <= diningTableService.getCount(); i++) {
            if (diningTableService.getDiningTableById(i).getState().equals("empty")) {
                jc.addItem(diningTableService.getDiningTableById(i).getId());
            }
        }

        //设置各组件位置&大小
        jcLabel.setBounds(80,50,150,30);
        jc.setBounds(240,50,60,30);
        orderNameLabel.setBounds(80,90,100,30);
        orderNameText.setBounds(170,90,130,30);
        orderTelLabel.setBounds(80,130,100,30);
        orderTelText.setBounds(170,130,130,30);
        jButton.setBounds(150,200,100,30);

        showPanel.add(jcLabel);
        showPanel.add(jc);//把下拉框添加到面板中
        showPanel.add(orderNameLabel);
        showPanel.add(orderNameText);
        showPanel.add(orderTelLabel);
        showPanel.add(orderTelText);
        showPanel.add(jButton);

        contentPane.add(showPanel);//添加到容器里

        setVisible(true);

        jButton.addActionListener(e -> {
            int orderId = (int)jc.getSelectedItem();
            String orderName = orderNameText.getText();
            String orderTel = orderTelText.getText();
            if (diningTableService.orderDiningTable(orderId, orderName, orderTel)) {
                JOptionPane.showMessageDialog(null, "Order successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Order failed!", "Error", JOptionPane. ERROR_MESSAGE);
            }
        });
    }
}
