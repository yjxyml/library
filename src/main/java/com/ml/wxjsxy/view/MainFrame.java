package com.ml.wxjsxy.view;

import com.ml.wxjsxy.model.Privilege;
import com.ml.wxjsxy.model.User;
import com.ml.wxjsxy.server.BookServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private JMenuBar jMenuBar=new JMenuBar();
    private JMenuItem item1 ;
    private static Map<String,String> dialogMap=new HashMap<String,String>();
    private User user ;
    private BookServer bookServer ;

    public MainFrame(String title , User user,BookServer bookServer) throws HeadlessException {
        super(title);
        init(user);
        this.user = user;
        this.bookServer = bookServer;
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-this.getSize().width)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-this.getSize().height)/2);
        this.setVisible(true);
    }

    static {
        dialogMap.put("预约","com.ml.wxjsxy.view.ManagerInterface");
    }

    public void showDialog(String title)
    {
        String name= dialogMap.get(title);
        System.out.println(name);

        try {

//            System.out.println(name);
            Class c=Class.forName(name);
            Constructor declaredConstructor = c.getDeclaredConstructor(com.ml.wxjsxy.model.User.class, com.ml.wxjsxy.server.BookServer.class);
            Object o = declaredConstructor.newInstance(user, bookServer);
            JFrame j= (JFrame)o;
//            j.setSize(350,350);
            j.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-j.getSize().width)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-j.getSize().height)/2);
//            j.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(User user) {

        for (Privilege privilege : user.getList()) {
            //一级菜单
            JMenu jMenu = new JMenu(privilege.getName());
//            System.out.println(privilege.getName());
            jMenuBar.add(jMenu);
            for (Privilege privilege1 : privilege.getList()) {
                //二级菜单
                JMenuItem jMenuItem = new JMenuItem(privilege1.getName());
//                System.out.println(privilege1.getName());
//                dialogMap.put(privilege1.getName(), "com.ml.wxjsxy.util.allData");
                item1 = jMenuItem;
                jMenu.add(jMenuItem);
                item1.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        System.out.println(e.getActionCommand());
                        showDialog(e.getActionCommand());
                    }
                });
            }
        }
        this.setJMenuBar(jMenuBar);
    }
}
