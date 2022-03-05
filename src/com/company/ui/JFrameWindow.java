package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class JFrameWindow extends JFrame {
    //窗口拖动参数
    private int xOld = 0;
    private int yOld = 0;
    private boolean isPressed = false;

    //顶部标题
    JPanel topJPanel;

    //缩小和退出按钮
    RoundBtn exit;
    RoundBtn hideWindow;

    //标题
    public String text;
    public JLabel title ;

    //
    public int width;
    public int height;

    public JFrameWindow(){
        //初始窗口大小
        width = 600;
        height = 400;
        //设置边框
        this.setLayout(null);
        this.setUndecorated(true);//把边框都去掉
        this.setBounds(700,200,width,height);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(254, 220, 247));
        this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 38, 38));

        //顶部的JPanel（标题栏）
        topJPanel = new JPanel();
        topJPanel.setBackground(new Color(228,254,253));
        topJPanel.setLayout(null);
        topJPanel.setBounds(0,0,this.getWidth(),40);

        //缩小和退出按钮设置
        int size = 5;
        exit = new RoundBtn(60,60,size,size,new JLabel(""));
        exit.setBounds(this.getWidth()-40,8,size,size);
        exit.color = new Color(253,151,166);
        hideWindow = new RoundBtn(60,60,size,size,new JLabel(""));
        hideWindow.setBounds(this.getWidth()-80,8,size,size);
        hideWindow.color = new Color(192,213,254);


        //标题
        text = "title";
        title = new JLabel(text);
        title.setBounds(30,11,70,20);

        //顶部标题栏添加退出按钮
        topJPanel.add(exit);
        topJPanel.add(hideWindow);
        topJPanel.add(title);


        //退出按钮点击事件
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //缩小按钮点击事件
        hideWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFrame.(JFrame.ICONIFIED)
                setExtendedState(JFrame.ICONIFIED);
            }
        });

        //鼠标拖动点击事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();//记录鼠标按下时的坐标
                yOld = e.getY();

                //按住的地方是标题栏才可以拖动窗口
                if(xOld > 0&& xOld < width&& yOld > 0 && yOld < height){
                    isPressed = true;
                    //System.out.println(isPressed);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
            }

        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if(isPressed){
                    int xOnScreen = e.getXOnScreen();
                    int yOnScreen = e.getYOnScreen();
                    int xx = xOnScreen - xOld;
                    int yy = yOnScreen - yOld;
                    JFrameWindow.this.setLocation(xx, yy);//设置拖拽后，窗口的位置
                }
            }
        });

        //添加顶部标题栏
        this.add(topJPanel);

    }

    //
    public void setWindowTitle(String text){
        this.text = text;
        this.title.setText(text);
    }

    public void setWindowSize(int width,int height){
        this.width = width;
        this.height = height;
//        this.getLocation()
        this.setSize(width,height);
        exit.setBounds(this.getWidth()-40,8,25,25);
        hideWindow.setBounds(this.getWidth()-80,8,25,25);
        topJPanel.setBounds(0,0,this.getWidth(),40);
        this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 38, 38));
    }

}
