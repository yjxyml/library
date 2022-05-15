package com.ml.wxjsxy.view;

import com.ml.wxjsxy.model.Book;
import com.ml.wxjsxy.server.BookServer;
import com.ml.wxjsxy.util.ScreenUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateBookDialog extends JDialog {
    final int WIDTH = 500;
    final int HEIGHT = 400;

    private JTextField nameField;
    private JTextField stockField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextArea descArea;

    public UpdateBookDialog(final JFrame jf, String title, boolean isModel, final String id, final BookServer bookServer, final DefaultTableModel tableModel, final JTable table){
        super(jf,title,isModel);
        //组装视图
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        Box idBox = Box.createHorizontalBox();
        JLabel idLable = new JLabel("图书编号：");
        final JTextField idField = new JTextField(15);

        idBox.add(idLable);
        idBox.add(Box.createHorizontalStrut(20));
        idBox.add(idField);

        //组装图书名称
        Box nameBox = Box.createHorizontalBox();
        JLabel nameLable = new JLabel("图书名称：");
        nameField = new JTextField(15);

        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装图书库存
        Box stockBox = Box.createHorizontalBox();
        JLabel stockLable = new JLabel("图书库存：");
        stockField = new JTextField(15);

        stockBox.add(stockLable);
        stockBox.add(Box.createHorizontalStrut(20));
        stockBox.add(stockField);

        //组装图书作者
        Box authorBox = Box.createHorizontalBox();
        JLabel authorLable = new JLabel("图书作者：");
        authorField = new JTextField(15);

        authorBox.add(authorLable);
        authorBox.add(Box.createHorizontalStrut(20));
        authorBox.add(authorField);

        //组装图书价格
        Box priceBox = Box.createHorizontalBox();
        JLabel priceLable = new JLabel("图书价格：");
        priceField = new JTextField(15);

        priceBox.add(priceLable);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceField);

        //组装图书简介
        Box descBox = Box.createHorizontalBox();
        JLabel descLable = new JLabel("图书简介：");
        descArea = new JTextArea(3,15);

        descBox.add(descLable);
        descBox.add(Box.createHorizontalStrut(20));
        descBox.add(new JScrollPane(descArea));

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton updateBtn = new JButton("修改");
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户修改后在输入框中输入的内容
                Book book = new Book();
                int bookId = Integer.parseInt(idField.getText().trim());
                int updateId = Integer.parseInt(id);
                String name = nameField.getText().trim();
                int stock = Integer.parseInt(stockField.getText().trim());
                String author = authorField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                String desc = descArea.getText().trim();

                book.setId(bookId);
                book.setUpdateId(updateId);
                book.setName(name);
                book.setStock(stock);
                book.setAuthor(author);
                book.setPrice(price);
                book.setDesc(desc);

                int update = bookServer.update(book);
                if(update==1)
                {
                    JOptionPane.showMessageDialog(jf,"修改成功");
                    int selectedRow = table.getSelectedRow();
                    if(selectedRow!=-1)
                    {
                        tableModel.setValueAt(book.getId(),selectedRow,0);
                        tableModel.setValueAt(book.getName(),selectedRow,1);
                        tableModel.setValueAt(book.getStock(),selectedRow,2);
                        tableModel.setValueAt(book.getAuthor(),selectedRow,3);
                        tableModel.setValueAt(book.getPrice(),selectedRow,4);
                        tableModel.setValueAt(book.getDesc(),selectedRow,5);
                    }
                    idField.setText("");
                    nameField.setText("");
                    stockField.setText("");
                    authorField.setText("");
                    priceField.setText("");
                    descArea.setText("");
                }
                else
                {
                    JOptionPane.showMessageDialog(jf,"修改失败");
                    idField.setText("");
                    nameField.setText("");
                    stockField.setText("");
                    authorField.setText("");
                    priceField.setText("");
                    descArea.setText("");
                }
            }
        });
        //TODO 处理修改的行为
        btnBox.add(updateBtn);

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(idBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(stockBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(authorBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(priceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(descBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(btnBox);

        //为了左右有间距，在vBox外层封装一个水平的Box，添加间隔
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));

        this.add(hBox);
    }
}
