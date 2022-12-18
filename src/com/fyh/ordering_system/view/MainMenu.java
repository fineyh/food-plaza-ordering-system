package com.fyh.ordering_system.view;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    JFrame frame = new JFrame("Main Menu");
    Container c = frame.getContentPane();//创建视图
    JLabel title = new JLabel("Food Plaza Ordering System");
    ImageIcon imageIcon = new ImageIcon("src\\com\\fyh\\ordering_system\\view\\images\\FoodPlaza.jpg");
    JLabel image = new JLabel(imageIcon);
    JButton listDiningTableBtn = new JButton("List Dining Table");
    JButton orderDiningTableBtn = new JButton("Order Dining Table");
    JButton listMenuBtn = new JButton("List Menu");
    JButton orderMenuBtn = new JButton("Order Menu");
    JButton listBillBtn = new JButton("List Bill");
    JButton payBillBtn = new JButton("Pay Bill");


    public MainMenu() {
        frame.setSize(1080, 720);//设置窗体位置&大小
        frame.setLocationRelativeTo(null);//设置窗口位于屏幕正中
        c.setLayout(new BorderLayout());//设置视图的布局
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭功能
        init();//初始化，把控件放在布局里
        frame.setVisible(true);//设置窗体可见
    }

    public void init() {
        //标题面板
        JPanel titlePanel = new JPanel();//创建一个放置标题的面板
        titlePanel.setBorder(BorderFactory.createEtchedBorder());
        titlePanel.setLayout(new FlowLayout());
        title.setFont(new Font("Cambria",Font.BOLD,15));
        titlePanel.add(title);
        c.add(titlePanel, "North");//加入视图中


        //显示面板
        JPanel showPanel = new JPanel();
        showPanel.setBorder(BorderFactory.createEtchedBorder());
        showPanel.setLayout(null);
        image.setBounds(0,0,1060,600);
        showPanel.add(image);
        c.add(showPanel, "Center");


        //功能面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(listDiningTableBtn);
        buttonPanel.add(orderDiningTableBtn);
        buttonPanel.add(listMenuBtn);
        buttonPanel.add(orderMenuBtn);
        buttonPanel.add(listBillBtn);
        buttonPanel.add(payBillBtn);
        c.add(buttonPanel, "South");

        //各功能按钮监听器
        listDiningTableBtn.addActionListener(e -> new ListDiningTable());
        orderDiningTableBtn.addActionListener(e -> new OrderDiningTable());
        listMenuBtn.addActionListener(e -> new ListMenu());
        orderMenuBtn.addActionListener(e -> new OrderMenu());
        listBillBtn.addActionListener(e -> new ListBill());
        payBillBtn.addActionListener(e -> new PayBill());
    }
}
