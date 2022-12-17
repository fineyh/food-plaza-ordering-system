package com.fyh.ordering_system.view;

import com.fyh.ordering_system.service.BillService;
import com.fyh.ordering_system.service.DiningTableService;

import javax.swing.*;
import java.awt.*;

public class PayBill extends JFrame {
    Container contentPane = this.getContentPane();//创建视图
    JLabel jcLabel = new JLabel("Choose table:             Table");
    JComboBox jc=new JComboBox();//创建餐桌下拉框
    JLabel payMethodLabel = new JLabel("Pay method:");
    JRadioButton jRadioButton1 = new JRadioButton("Cash");
    JRadioButton jRadioButton2 = new JRadioButton("Alipay");
    JRadioButton jRadioButton3 = new JRadioButton("Wechat");
    ButtonGroup group =new ButtonGroup();
    JButton jButton = new JButton("Pay");

    public PayBill() {
        //定义 DiningTableService 的属性
        DiningTableService diningTableService = new DiningTableService();
        //定义 BillService 的属性
        BillService billService= new BillService();

        //初始化窗体

        // 设置标题
        setTitle("Pay Bill");
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
            if (diningTableService.getDiningTableById(i).getState().equals("Eating")) {
                jc.addItem(diningTableService.getDiningTableById(i).getId());
            }
        }

        //支付方式
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        group.add(jRadioButton3);

        //设置各组件位置&大小
        jcLabel.setBounds(80,50,150,30);
        jc.setBounds(240,50,60,30);
        payMethodLabel.setBounds(80,90,100,30);
        jRadioButton1.setBounds(170,90,60,30);
        jRadioButton2.setBounds(230,90,60,30);
        jRadioButton3.setBounds(290,90,70,30);
        jButton.setBounds(150,200,100,30);

        showPanel.add(jcLabel);
        showPanel.add(jc);//把下拉框添加到面板中
        showPanel.add(payMethodLabel);
        showPanel.add(jRadioButton1);
        showPanel.add(jRadioButton2);
        showPanel.add(jRadioButton3);
        showPanel.add(jButton);

        contentPane.add(showPanel);//添加到容器里

        setVisible(true);

        jButton.addActionListener(e -> {
            int diningTableId = (int)jc.getSelectedItem();
            String payMode = "";
            if (jRadioButton1.isSelected()) {
                payMode = "Cash";
            } else if (jRadioButton2.isSelected()) {
                payMode = "Alipay";
            } else if (jRadioButton3.isSelected()) {
                payMode = "Wechat";
            }
            if (billService.payBill(diningTableId, payMode)) {
                JOptionPane.showMessageDialog(null, "Pay successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Pay failed!", "Error", JOptionPane. ERROR_MESSAGE);
            }
        });
    }
}
