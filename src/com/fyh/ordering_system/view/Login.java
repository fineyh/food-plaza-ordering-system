package com.fyh.ordering_system.view;

import com.fyh.ordering_system.domain.Employee;
import com.fyh.ordering_system.service.EmployeeService;

import javax.swing.*;
import java.awt.*;

public class Login {
    JFrame frame = new JFrame("Login");
    Container c = frame.getContentPane();//创建视图
    JLabel title = new JLabel("Food Plaza Ordering System");
    JLabel userLabel = new JLabel("Username:");
    JTextField username = new JTextField();
    JLabel passwdLabel = new JLabel("Password:");
    JPasswordField password = new JPasswordField();
    JButton okButton = new JButton("Confirm");
    JButton cancelButton = new JButton("Cancel");

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
        userLabel.setBounds(45, 20, 100, 20);//标签位置
        passwdLabel.setBounds(45, 60, 100, 20);
        inputPanel.add(userLabel);
        inputPanel.add(passwdLabel);
        username.setBounds(115, 20, 120, 20);
        password.setBounds(115, 60, 120, 20);
        inputPanel.add(username);
        inputPanel.add(password);
        c.add(inputPanel, "Center");

//        按钮底部
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        frame.getRootPane().setDefaultButton(okButton);// 确认按钮绑定回车快捷键
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        c.add(buttonPanel, "South");

        //确认按钮
        okButton.addActionListener(e -> {
            String empId = username.getText();
            String pwd = password.getText();
            Employee employee = employeeService.getEmployeeByIdAndPwd(empId,pwd);
            if (employee != null) {
                System.out.println("success");
                frame.setVisible(false);
                new MainMenu();
            } else {
                System.out.println("failed");
                JOptionPane.showMessageDialog(null, "Wrong username or password!", "Error", JOptionPane. ERROR_MESSAGE);
            }
        });

        //取消按钮
        cancelButton.addActionListener(e -> frame.dispose());
    }
}
