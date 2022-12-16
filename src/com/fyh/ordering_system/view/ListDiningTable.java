package com.fyh.ordering_system.view;

import com.fyh.ordering_system.service.DiningTableService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListDiningTable extends JFrame {
    private DefaultTableModel dtm;

    private JTable table;

    public ListDiningTable() {
        //定义 DiningTableService 的属性
        DiningTableService diningTableService = new DiningTableService();

        //初始化窗体

        // 设置标题
        setTitle("Table");
        // 设置大小
        setSize(400, 300);
        setLocationRelativeTo(null);
        // 设置是否可以改变大小
        setResizable(false);
        // 设置退出后进行的操作
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --------------------------------------------------------------------------

        //创建表

        //表头数据
        String[] columnNames = new String[]{"Table Number", "Table State"};
        //表数据
        String[][] data = new String[][]{};
        //初始化DefaultTableModel
        dtm = new DefaultTableModel(data, columnNames) {
            //设置单元格不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //初始化table
        table = new JTable(dtm);
        //添加20条数据
        addRows(diningTableService.getCount());

        //ScrollPanel 可以自动生成滚动条
        JScrollPane jsp = new JScrollPane(table);
        add(jsp);

        // 设置窗体可见
        setVisible(true);
    }

    private void addRows(long count){
        //定义 DiningTableService 的属性
        DiningTableService diningTableService = new DiningTableService();

        //添加数据
        //准备要添加的数据
        String[] arr = new String[2];
        //循环添加
        for (int i = 1; i <= count; i++) {
            Integer id = diningTableService.getDiningTableById(i).getId();
            String idStr = id.toString();
            arr[0] = "" + idStr;
            arr[1] = diningTableService.getDiningTableById(id).getState();
            add(arr);
        }
    }

    /**
     * 添加行数据
     */
    private void add(String[] arr) {
        //添加行
        dtm.addRow(arr);
        //重新设置Model
        table.setModel(dtm);
    }

}
