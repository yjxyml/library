package com.ml.wxjsxy.view;

import com.ml.wxjsxy.model.User;
import com.ml.wxjsxy.server.BookServer;
import com.ml.wxjsxy.util.PathUtils;
import com.ml.wxjsxy.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ManagerInterface extends JFrame{
    JFrame jf ;

    final int WIDTH = 1000;
    final int HEIGHT = 600;

    BookServer bookServer;
    User user;

    public ManagerInterface( User user,BookServer bookServer) {
        this.user = user;
        this.bookServer = bookServer;
        this.init(user,bookServer);
    }

    public void init(User user, final BookServer bookServer)
    {
        jf = new JFrame("开心每一天图书馆："+user.getName()+user.getRole()+"欢迎您的光临");
        try {
            jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH)/2,(ScreenUtils.getScreenHeight() - HEIGHT) / 2,WIDTH,HEIGHT);
            jf.setResizable(false);
            jf.setIconImage(ImageIO.read(new File(PathUtils.getRealPath("3.png"))));

            //设置菜单项
            JMenuBar jMenuBar = new JMenuBar();
            final JMenu jMenu = new JMenu("设置");
            JMenuItem jMenuItem1 = new JMenuItem("切换账号");
            JMenuItem jMenuItem2 = new JMenuItem("退出登录");

            jMenu.add(jMenuItem1);
            jMenu.add(jMenuItem2);

            jMenuItem1.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    new view().into_login();
                    jf.dispose();
                }
            });

            jMenuItem2.addActionListener(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


            jMenuBar.add(jMenu);
            jf.setJMenuBar(jMenuBar);

            //设置分割面板
            final JSplitPane sp = new JSplitPane();

            //支持连续布局
            sp.setContinuousLayout(true);

            //初始值
            sp.setDividerLocation(150);

            //宽度
            sp.setDividerSize(7);

            DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
            final DefaultMutableTreeNode userManage = new DefaultMutableTreeNode("用户管理");
            final DefaultMutableTreeNode bookManage = new DefaultMutableTreeNode("图书管理");
            final DefaultMutableTreeNode borrowManage = new DefaultMutableTreeNode("借阅管理");
            final DefaultMutableTreeNode statisticsManage = new DefaultMutableTreeNode("统计分析");

            root.add(userManage);
            root.add(bookManage);
            root.add(borrowManage);
            root.add(statisticsManage);

            Color color = new Color(203, 220, 217);
            JTree tree = new JTree(root);

            tree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                    if(userManage.equals(lastPathComponent))
                    {
                        sp.setRightComponent(new JLabel("这是用户管理..."));
                        sp.setDividerLocation(150);
                    }
                    else if(bookManage.equals(lastPathComponent))
                    {
                        sp.setRightComponent(new BookManageComponent(jf,bookServer));
                        sp.setDividerLocation(150);
                    }
                    else if (borrowManage.equals(lastPathComponent))
                    {
                        sp.setRightComponent(new JLabel("这是借阅管理..."));
                        sp.setDividerLocation(150);
                    }
                    else if(statisticsManage.equals(lastPathComponent))
                    {
                        sp.setRightComponent(new JLabel("这是统计分析..."));
                        sp.setDividerLocation(150);
                    }
                }
            });

            MyRenderer myRenderer = new MyRenderer();
            myRenderer.setBackgroundNonSelectionColor(color);
            myRenderer.setBackgroundSelectionColor(new Color(140,140,140));
            tree.setCellRenderer(myRenderer);
            tree.setBackground(color);

            //设置tree默认图书管理
            tree.setSelectionRow(2);
            sp.setRightComponent(new BookManageComponent(jf,bookServer));
            sp.setLeftComponent(tree);
            jf.add(sp);

            jf.setVisible(true);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private class MyRenderer extends DefaultTreeCellRenderer
    {
        private ImageIcon rootIcon = null;
        private ImageIcon userManageIcon = null;
        private ImageIcon bookManageIcon = null;
        private ImageIcon borrowManageIcon = null;
        private ImageIcon statisticsManageIcon = null;

        public MyRenderer() {
            this.rootIcon = new ImageIcon(PathUtils.getRealPath("systemManage.png"));
            this.userManageIcon = new ImageIcon(PathUtils.getRealPath("userManage.png"));
            this.bookManageIcon = new ImageIcon(PathUtils.getRealPath("bookManage.png"));
            this.borrowManageIcon = new ImageIcon(PathUtils.getRealPath("borrowManage.png"));
            this.statisticsManageIcon = new ImageIcon(PathUtils.getRealPath("statisticsManage.png"));
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            ImageIcon image = null;
            switch (row)
            {
                case 0 :
                    image = rootIcon;
                    break;
                case 1 :
                    image = userManageIcon;
                    break;
                case 2 :
                    image = bookManageIcon;
                    break;
                case 3 :
                    image = borrowManageIcon;
                    break;
                case 4 :
                    image = statisticsManageIcon;
                    break;
            }
            this.setIcon(image);
            return this;
        }
    }
}
