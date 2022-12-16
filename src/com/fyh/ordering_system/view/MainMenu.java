package com.fyh.ordering_system.view;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    JFrame frame = new JFrame("主菜单");
    Container c = frame.getContentPane();//创建视图

    public MainMenu() {
        frame.setSize(1080, 720);//设置窗体位置&大小
        frame.setLocationRelativeTo(null);//设置窗口位于屏幕正中
        c.setLayout(new BorderLayout());//设置视图的布局
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭功能
        init();//初始化，把控件放在布局里
        frame.setVisible(true);//设置窗体可见
    }

    public void init() {

    }
}
