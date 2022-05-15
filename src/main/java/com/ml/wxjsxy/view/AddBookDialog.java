package com.ml.wxjsxy.view;

import com.ml.wxjsxy.model.Book;
import com.ml.wxjsxy.server.BookServer;
import com.ml.wxjsxy.util.ScreenUtils;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

@ComponentScan("com.ml.wxjsxy")
public class AddBookDialog extends JDialog {

    final int WIDTH = 500;
    final int HEIGHT = 400;

    public AddBookDialog(final JFrame jf , String title, final BookServer bookServer , final DefaultTableModel tableModel)
    {

        super(jf,title,true);
        this.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        Box vBox = Box.createVerticalBox();

        Box idBox = Box.createHorizontalBox();
        JLabel idLable = new JLabel("图书编号：");
        final JTextField idField = new JTextField(15);

        idBox.add(idLable);
        idBox.add(Box.createHorizontalStrut(20));
        idBox.add(idField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLable = new JLabel("图书名称：");
        final JTextField nameField = new JTextField(15);

        nameBox.add(nameLable);
        nameBox.add(Box.createHorizontalStrut(20));
        nameBox.add(nameField);

        //组装图书库存
        Box stockBox = Box.createHorizontalBox();
        JLabel stockLable = new JLabel("图书库存：");
        final JTextField stockField = new JTextField(15);

        stockBox.add(stockLable);
        stockBox.add(Box.createHorizontalStrut(20));
        stockBox.add(stockField);

        //组装图书作者
        Box authorBox = Box.createHorizontalBox();
        JLabel authorLable = new JLabel("图书作者：");
        final JTextField authorField = new JTextField(15);

        authorBox.add(authorLable);
        authorBox.add(Box.createHorizontalStrut(20));
        authorBox.add(authorField);

        //组装图书价格
        Box priceBox = Box.createHorizontalBox();
        JLabel priceLable = new JLabel("图书价格：");
        final JTextField priceField = new JTextField(15);

        priceBox.add(priceLable);
        priceBox.add(Box.createHorizontalStrut(20));
        priceBox.add(priceField);

        //组装图书简介
        Box descBox = Box.createHorizontalBox();
        JLabel descLable = new JLabel("图书简介：");
        final JTextArea descArea = new JTextArea(5,15);

        descBox.add(descLable);
        descBox.add(Box.createHorizontalStrut(20));
        descBox.add(descArea);

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton addBtn = new JButton("添加");

        addBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book();
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int stock = Integer.parseInt(stockField.getText().trim());
                String author = authorField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                String desc = descArea.getText().trim();
                book.setId(id);
                book.setName(name);
                book.setStock(stock);
                book.setAuthor(author);
                book.setPrice(price);
                book.setDesc(desc);
                int insert = bookServer.insert(book);
                if(insert == 1)
                {
                    JOptionPane.showMessageDialog(jf,"添加成功");

                    tableModel.addRow(new Object[]{book.getId(),book.getName(),book.getDesc(),book.getAuthor(),book.getPrice(),book.getStock()});
                    idField.setText("");
                    nameField.setText("");
                    stockField.setText("");
                    authorField.setText("");
                    priceField.setText("");
                    descArea.setText("");

                }
                else
                {
                    JOptionPane.showMessageDialog(jf,"添加失败");
                    idField.setText("");
                    nameField.setText("");
                    stockField.setText("");
                    authorField.setText("");
                    priceField.setText("");
                    descArea.setText("");
                }
            }
        });

        btnBox.add(addBtn);

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
