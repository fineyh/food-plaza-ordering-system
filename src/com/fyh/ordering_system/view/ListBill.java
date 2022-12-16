package com.fyh.ordering_system.view;

import com.fyh.ordering_system.service.BillService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListBill extends JFrame {
    private DefaultTableModel dtm;

    private JTable table;

    public ListBill() {
        //定义 BillService 的属性
        BillService billService= new BillService();

        //初始化窗体

        // 设置标题
        setTitle("Bill");
        // 设置大小
        setSize(800, 300);
        setLocationRelativeTo(null);
        // 设置是否可以改变大小
        setResizable(false);
        // 设置退出后进行的操作
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --------------------------------------------------------------------------

        //创建表

        //表头数据
        String[] columnNames = new String[]{"ID", "Bill ID", "Menu ID", "Nums", "Money", "Dining Table ID", "Bill Date", "State"};
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
        addRows(billService.getCount());

        //ScrollPanel 可以自动生成滚动条
        JScrollPane jsp = new JScrollPane(table);
        add(jsp);

        // 设置窗体可见
        setVisible(true);
    }

    private void addRows(long count){
        //定义 BillService 的属性
        BillService billService= new BillService();

        //添加数据
        //准备要添加的数据
        String[] arr = new String[8];
        //循环添加
        for (int i = 1; i <= count; i++) {
            Integer id = billService.getBillById(i).getId();
            String idStr = id.toString();
            arr[0] = "" + idStr;
            arr[1] = billService.getBillById(id).getBillId();
            arr[2] = billService.getBillById(id).getMenuId().toString();
            arr[3] = billService.getBillById(id).getNums().toString();
            arr[4] = billService.getBillById(id).getMoney().toString();
            arr[5] = billService.getBillById(id).getDiningTableId().toString();
            arr[6] = billService.getBillById(id).getBillDate().toString();
            arr[7] = billService.getBillById(id).getState();
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
