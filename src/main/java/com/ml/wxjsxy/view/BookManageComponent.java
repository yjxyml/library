package com.ml.wxjsxy.view;

import com.ml.wxjsxy.model.Book;
import com.ml.wxjsxy.server.BookServer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class BookManageComponent extends Box {

    final int WIDTH=850;
    final int HEIGHT=600;

    JFrame jf = null;
    public JTable table;
    public Object[][] objects;
    public DefaultTableModel tableModel;
    public JScrollPane scrollPane;

    public BookManageComponent(final JFrame jf , final BookServer bookServer) {
        super(BoxLayout.Y_AXIS);
        //组装视图
        this.jf = jf;
        JPanel btnPanel = new JPanel();
        Color color = new Color(0x00FFFF);
        btnPanel.setBackground(color);
        btnPanel.setMaximumSize(new Dimension(WIDTH,80));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addBtn = new JButton("添加");
        JButton updateBtn = new JButton("修改");
        JButton deleteBtn = new JButton("删除");

        addBtn.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new AddBookDialog(jf , "图书添加",bookServer,tableModel).setVisible(true);
            }
        });

        updateBtn.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if(selectedRow == -1)
                {
                    JOptionPane.showMessageDialog(jf,"请选择要修改的条目！");
                    return;
                }

                String id = tableModel.getValueAt(selectedRow, 0).toString();

                new UpdateBookDialog(jf,"修改图书",true,id,bookServer,tableModel,table).setVisible(true);
            }
        });

        deleteBtn.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //获取选中的条目
                int selectedRow = table.getSelectedRow();//如果有选中的条目，则返回条目的行号，如果没有选中，那么返回-1

                if (selectedRow==-1){
                    JOptionPane.showMessageDialog(jf,"请选择要删除的条目！");
                    return;
                }

                //防止误操作
                int result = JOptionPane.showConfirmDialog(jf, "确认要删除选中的条目吗？", "确认删除", JOptionPane.YES_NO_OPTION);
                if (result != JOptionPane.YES_OPTION){
                    return;
                }

                String id = table.getValueAt(selectedRow, 0).toString();

                Book book = new Book();
                book.setId(Integer.parseInt(id));

                int delete = bookServer.delete(book);
                if(delete == 1)
                {
                    JOptionPane.showMessageDialog(jf,"删除成功");
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if(Integer.parseInt(table.getValueAt(i,0).toString()) == Integer.parseInt(id))
                        {
                            int sellndex = i;
                            if(sellndex<0 || sellndex >= table.getRowCount())
                            {
                                return;
                            }
                            tableModel.removeRow(sellndex);
                            tableModel.fireTableDataChanged();
                            break;
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(jf,"删除失败");
                }
            }
        });

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);

        this.add(btnPanel);

        requestData(bookServer);

    }

    public void requestData(BookServer bookServer) {
        List<Book> books = bookServer.selectBooks();
        //组装表格
        Object[] ts = {"编号", "书名", "作者", "价格", "库存", "简介"};

        objects = new Object[books.size()][6];
        for (int i = 0; i < books.size(); i++) {
            objects[i][0] = books.get(i).getId();
            objects[i][1] = books.get(i).getName();
            objects[i][2] = books.get(i).getAuthor();
            objects[i][3] = books.get(i).getPrice();
            objects[i][4] = books.get(i).getStock();
            objects[i][5] = books.get(i).getDesc();
        }

        tableModel = new DefaultTableModel(objects, ts) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);

        //设置只能选中一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(table);

        this.add(scrollPane);
    }
}
