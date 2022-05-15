package com.ml.wxjsxy.view;

import com.ml.wxjsxy.model.User;
import com.ml.wxjsxy.server.UserServer;
import com.ml.wxjsxy.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class RegisterInterface {

    JFrame jf = new JFrame("注册");

    final int WIDTH = 500;
    final int HEIGHT = 400;

    public void init(final UserServer userServer)
    {
        try {
            jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH)/2,(ScreenUtils.getScreenHeight() - HEIGHT)/2,WIDTH,HEIGHT);
            jf.setResizable(false);
            jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("p1.png"))));
            BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("p2.jpg"))));
            bgPanel.setBounds(0,0,WIDTH,HEIGHT);

            Box vBox = Box.createVerticalBox();

            Box uBox = Box.createHorizontalBox();
            JLabel uLabel = new JLabel("用户名：");
            uLabel.setForeground(new Color(0xFF6565));
            final JTextField uFile = new JTextField(15);

            uBox.add(uLabel);
            uBox.add(Box.createHorizontalStrut(20));
            uBox.add(uFile);

            Box pBox = Box.createHorizontalBox();
            JLabel pLabel = new JLabel("密    码：");
            pLabel.setForeground(new Color(0xFF6565));
            final JPasswordField pFile = new JPasswordField(15);
            pFile.setEchoChar('*');

            pBox.add(pLabel);
            pBox.add(Box.createHorizontalStrut(20));
            pBox.add(pFile);

            //手机
            Box telephoneBox = Box.createHorizontalBox();
            JLabel telephoneLabel = new JLabel("手机号：");
            telephoneLabel.setForeground(new Color(0xFF6565));
            final JTextField telephoneFile = new JTextField(15);
            telephoneFile.setDocument(new NumberTextField(15));

            telephoneBox.add(telephoneLabel);
            telephoneBox.add(Box.createHorizontalStrut(20));
            telephoneBox.add(telephoneFile);

            Box xBox = Box.createHorizontalBox();
            JLabel xLabel = new JLabel("性    别：");
            xLabel.setForeground(new Color(0xFF6565));
            final JRadioButton maleBtn = new JRadioButton("男",true);
            final JRadioButton femaleBtn = new JRadioButton("女",false);

            final ButtonGroup bg = new ButtonGroup();
            bg.add(maleBtn);
            bg.add(femaleBtn);

            xBox.add(xLabel);
            xBox.add(Box.createHorizontalStrut(20));
            xBox.add(maleBtn);
            xBox.add(femaleBtn);
            xBox.add(Box.createHorizontalStrut(120));

            Box btnBox = Box.createHorizontalBox();
            JButton regisBtn = new JButton("注册");
            JButton backBtn = new JButton("返回登录页面");

            regisBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = uFile.getText().trim();
                    String password = pFile.getText().trim();
                    String gender = bg.isSelected(maleBtn.getModel()) ? maleBtn.getText() : femaleBtn.getText();
                    boolean phone = PhoneNumberUtil.isPhone(telephoneFile.getText());
                    User user = null;
                    if(phone == true)
                    {
                        user = new User(name, password,gender,telephoneFile.getText());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jf,"手机号有误！！！");
                        return;
                    }
                    int insert = userServer.insert(user);
                    if(insert==1)
                    {
                        JOptionPane.showMessageDialog(jf,"注册成功");
                        uFile.setText("");
                        pFile.setText("");
                        telephoneFile.setText("");
                        uFile.requestFocus();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(jf,"注册失败");
                        uFile.setText("");
                        pFile.setText("");
                        telephoneFile.setText("");
                        uFile.requestFocus();
                    }
                }
            });

            backBtn.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new view().into_login();
                    jf.dispose();
                }
            });

            btnBox.add(regisBtn);
            btnBox.add(Box.createHorizontalStrut(80));
            btnBox.add(backBtn);

            vBox.add(Box.createVerticalStrut(50));
            vBox.add(uBox);
            vBox.add(Box.createVerticalStrut(20));
            vBox.add(pBox);
            vBox.add(Box.createVerticalStrut(20));
            vBox.add(telephoneBox);
            vBox.add(Box.createVerticalStrut(20));
            vBox.add(xBox);
            vBox.add(Box.createVerticalStrut(20));

            vBox.add(btnBox);

            bgPanel.add(vBox);

            jf.add(bgPanel);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
