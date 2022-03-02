package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class RoundDialog extends JFrame {
    JPanel centerJPanel;//设置中心的JPanel
    JPanel bottomJPanel;//设置底部的JPanel

    JLabel textJLabel;
    RoundBtn confirmJBt;//确定按钮

    public RoundDialog(String text){
        this.setUndecorated(true);//把边框都去掉
        this.setLayout(new BorderLayout(15,5));
        this.getContentPane().setBackground(new Color(220,176,249));

        //初始化中心和底部的JPanel
        centerJPanel = new JPanel();
        bottomJPanel = new JPanel();

        //为中心和底部的JPanel添加背景颜色
        centerJPanel.setBackground(new Color(220,176,249));
        bottomJPanel.setBackground(new Color(220,176,249));

        //设置文本内容和字体
        textJLabel = new JLabel(text);
        textJLabel.setFont(new Font("微软雅黑",Font.PLAIN,15));

        //初始化确定按钮
        confirmJBt = new RoundBtn(15,15,70,30,new JLabel("确定"));

        //为确定按钮设置监听
        confirmJBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //把组件添加到JPanel里面
        centerJPanel.add(textJLabel);
        bottomJPanel.add(confirmJBt);

        //为顶部设置按钮,使文本可以居中
        JPanel topJPanel = new JPanel();
        JLabel jLabel = new JLabel("    ");
        topJPanel.add(jLabel);
        topJPanel.setBackground(new Color(220,176,249));
//        JButton jButton = new JButton();
//        jButton.setBackground(new Color(220,176,249));
//        jButton.setBorderPainted(false);
//        jButton.setFocusPainted(false);

        //为窗口添加各个组件
        this.add(topJPanel,BorderLayout.NORTH);
        this.add(centerJPanel,BorderLayout.CENTER);
        this.add(bottomJPanel,BorderLayout.SOUTH);

        this.setBounds(900,400,220,110);
        this.setVisible(true);

        //设置边框的的形状
        setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 38, 38));
    }
}
