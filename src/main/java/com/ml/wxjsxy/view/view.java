package com.ml.wxjsxy.view;

import com.ml.wxjsxy.model.User;
import com.ml.wxjsxy.server.BookServer;
import com.ml.wxjsxy.server.UserServer;
import com.ml.wxjsxy.util.BackGroundPanel;
import com.ml.wxjsxy.util.PathUtils;
import com.ml.wxjsxy.util.ScreenUtils;
import com.ml.wxjsxy.util.StringUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

@ComponentScan("com.ml.wxjsxy")
public class view {
    static AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(view.class);
    static UserServer userserver = (UserServer) acac.getBean("userserver");
    static BookServer bookserver = (BookServer) acac.getBean("bookserver");
    public static void main(String[] args) {
        into_login();
    }
    public static void into_login()
    {
        final JFrame jf = new JFrame("登录");

        final  int Width = 500;
        final  int Height = 300;

        try {
            //设置窗口
            jf.setBounds(( ScreenUtils.getScreenWidth() - Width ) / 2 , ( ScreenUtils.getScreenHeight() - Height ) / 2 ,
                    Width , Height);

            //不能修改
            jf.setResizable(false);

            //设置窗口图标
            jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("p1.png"))));

            //设置窗口背景
            BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(PathUtils.getRealPath("p2.jpg"))));

            //组装登录相关垂直
            Box vBox = Box.createVerticalBox();

            //组装用户
            Box user_Box = Box.createHorizontalBox();
            //用户名
            JLabel user_label = new JLabel("用户名：");
            user_label.setForeground(new Color(0xFF6565));
            //文本框
            final JTextField user_textfield = new JTextField(15);
            user_Box.add(user_label);
            //设置中间间距20
            user_Box.add(Box.createHorizontalStrut(20));
            user_Box.add(user_textfield);

            //组装密码
            Box password_Box = Box.createHorizontalBox();
            //密码
            JLabel password_label = new JLabel("密     码：");
            password_label.setForeground(new Color(0xFF6565));
            //文本框
            final JPasswordField password_textfield = new JPasswordField(15);
            password_textfield.setEchoChar('*');
            password_Box.add(password_label);
            //设置中间间距20
            password_Box.add(Box.createHorizontalStrut(20));
            password_Box.add(password_textfield);

            //组装用户权限
            Box permission_Box = Box.createHorizontalBox();
            final JComboBox cmb=new JComboBox();    //创建JComboBox
            JLabel user_permission = new JLabel("用户权限：");
            user_permission.setForeground(new Color(0xFF6565));
            cmb.addItem("用户");
            cmb.addItem("管理员");
            cmb.addItem("超级管理员");
            permission_Box.add(user_permission);
            //设置中间间距20
            permission_Box.add(Box.createHorizontalStrut(20));
            permission_Box.add(cmb);

            //组装登录注册
            Box login_register = Box.createHorizontalBox();
            //登录
            final JButton btn_login = new JButton("登录");
            //注册
            JButton btn_register = new JButton("注册");

            //登录事件
            btn_login.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    String username = user_textfield.getText().trim();
                    String password = password_textfield.getText().trim();
                    String role = cmb.getSelectedItem().toString();

                    if(StringUtil.isEmpty(username)){
                        JOptionPane.showMessageDialog(null, "用户名不能为空");
                        user_textfield.requestFocus();
                        return;
                    }
                    if(StringUtil.isEmpty(password)){
                        JOptionPane.showMessageDialog(null, "密码不能为空");
                        user_textfield.requestFocus();
                        return;
                    }

                    User user = new User();
                    user.setName(username);
                    user.setPassword(password);
                    user.setRole(role);
                    List<User> users = userserver.selectUsers(user);

                    if(users!=null)
                    {
                        User user1 = new User();
                        user1.setName(users.get(0).getName());
                        user1.setPassword(users.get(0).getPassword());
                        user1.setRole(users.get(0).getRole());
//                        System.out.println(users.get(0).getName());
//                        System.out.println(users.get(0).getPassword());
//                        System.out.println(users.get(0).getRole());
                        if(users.get(0).getName().equals(username) && users.get(0).getPassword().equals(password) && users.get(0).getRole().equals(role))
                        {
                            User user2 = userserver.Privilege(user1);
                            MainFrame mainFrame = null;
                            new MainFrame("main",user2,bookserver);

//                            //进入主界面
//                            new ManagerInterface().init(users,bookserver);
//                            //登录窗口关闭
//                            jf.dispose();
                        }
                        else
                        {
                            //消息弹框 错误信息
                            int result = JOptionPane.showConfirmDialog(jf,"你的权限不是"+cmb.getSelectedItem().toString()+"是否保留数据","登陆失败", JOptionPane.ERROR_MESSAGE);
                            if (result != JOptionPane.YES_OPTION)
                            {
                                user_textfield.setText("");
                                password_textfield.setText("");
                                user_textfield.requestFocus();
                                return;
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"账号密码错误","登陆失败",JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            //注册事件
            btn_register.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    //窗口关闭
                    jf.dispose();
                    //进入注册界面
                    new RegisterInterface().init(userserver);
                }
            });

            //添加登录
            login_register.add(btn_login);
            //设置中间间距120
            login_register.add(Box.createHorizontalStrut(120));
            //添加注册
            login_register.add(btn_register);

            //窗口间距50
            vBox.add(Box.createVerticalStrut(50));
            vBox.add(user_Box);
            //与上一个box间距20
            vBox.add(Box.createVerticalStrut(20));
            vBox.add(password_Box);
            //与上一个box间距20
            vBox.add(Box.createVerticalStrut(20));
            vBox.add(permission_Box);
            //与上一个box间距20
            vBox.add(Box.createVerticalStrut(20));
            vBox.add(login_register);

            //面板装入主box
            bgPanel.add(vBox);

            //添加背景到窗口
            jf.add(bgPanel);

            //设置窗口可见
            jf.setVisible(true);

            //设置窗口关闭
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
