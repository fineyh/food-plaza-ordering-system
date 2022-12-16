package com.fyh.ordering_system.view;

import com.fyh.ordering_system.domain.Employee;
import com.fyh.ordering_system.service.EmployeeService;

import javax.swing.*;
import java.awt.*;

public class Login {
    JFrame frame = new JFrame("登录");
    Container c = frame.getContentPane();//创建视图
    JLabel title = new JLabel("Food Plaza Ordering System");
    JLabel userLabel = new JLabel("用户名");
    JTextField username = new JTextField();
    JLabel passwdLabel = new JLabel("密码");
    JPasswordField password = new JPasswordField();
    JButton okbutton = new JButton("确定");
    JButton cancelbttton = new JButton("取消");

    public Login() {
        frame.setSize(300, 220);//设置窗体大小
        frame.setLocationRelativeTo(null);//设置窗口位于屏幕正中
        c.setLayout(new BorderLayout());//设置视图的布局
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭功能
        init();//初始化，把控件放在布局里
        frame.setVisible(true);//设置窗体可见
    }

    public void init() {
        EmployeeService employeeService = new EmployeeService();

//        标题——上方
        JPanel titlePanel = new JPanel();//创建一个放置标题的面板
        titlePanel.setLayout(new FlowLayout());
        title.setFont(new Font("Cambria",Font.BOLD,20));
        titlePanel.add(title);
        c.add(titlePanel, "North");//加入视图中

//        输入框——中间
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        userLabel.setBounds(50, 20, 50, 20);//标签位置
        passwdLabel.setBounds(50, 60, 50, 20);
        inputPanel.add(userLabel);
        inputPanel.add(passwdLabel);
        username.setBounds(110, 20, 120, 20);
        password.setBounds(110, 60, 120, 20);
        inputPanel.add(username);
        inputPanel.add(password);
        c.add(inputPanel, "Center");

//        按钮底部
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbutton);
        buttonPanel.add(cancelbttton);
        c.add(buttonPanel, "South");

        //确认按钮
        okbutton.addActionListener(e -> {
            String empId = username.getText();
            String pwd = password.getText();
            Employee employee = employeeService.getEmployeeByIdAndPwd(empId,pwd);
            if (employee != null) {
                System.out.println("success");
                frame.setVisible(false);
                new MainMenu();
            } else {
                System.out.println("failed");
            }
        });

        //取消按钮
        cancelbttton.addActionListener(e -> {
            frame.dispose();
        });
    }
}
