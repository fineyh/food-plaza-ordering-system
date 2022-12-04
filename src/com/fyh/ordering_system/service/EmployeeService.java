package com.fyh.ordering_system.service;

import com.fyh.ordering_system.dao.EmployeeDAO;
import com.fyh.ordering_system.domain.Employee;

//该类完成对 employee 表的各种操作（通过调用EmployeeDAO对象完成）
public class EmployeeService {
    //定义一个 EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //方法，根据 empId 和 pwd 返回一个Employee对象
    //如果查询不到，就返回null
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
        return employeeDAO.querySingle("select * from employee where empId = ? and pwd = ?", Employee.class, empId, pwd);

    }
}
