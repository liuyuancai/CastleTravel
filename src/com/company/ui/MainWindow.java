package com.company.ui;
import com.company.algorithm.Calculate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    int [][] PathX;
    int [][] PathY;
    int pathNum = 0;//代表第几条路径
    int Row,Col;
    int length;
    boolean b = false;
    Thread thread;

    public MainWindow(int Row,int Col){//画路线的窗口
        this.Row = Row;
        this.Col = Col;
        if(Row * Col < 100)length = 70;
        else if(Row * Col < 280 && Row < 16)length = 50;
        else length = 40;
        getPathXY();//计算路线,并且获取每条路线的XY值
        this.setSize(Col*length+20,Row*length+87);
        this.setResizable(false);
        setLayout(new BorderLayout());

        PaintPad pad = new PaintPad(Row,Col,PathX,PathY,length);
        add(pad,BorderLayout.CENTER);

        //创建一个按钮
        RoundBtn startBtn = new RoundBtn(15,15,95,30,new JLabel("开始绘制"));
        RoundBtn nextBtn = new RoundBtn(15,15,85,30,new JLabel("下一张"));
        RoundBtn preBtn = new RoundBtn(15,15,85,30,new JLabel("上一张"));
        RoundBtn jumpBtn = new RoundBtn(15,15,70,30,new JLabel("跳转"));

        //创建一个下拉条
        JComboBox<String > pagesSelect = new JComboBox<String>();
        for (int i = 1;i < pathNum; i++)pagesSelect.addItem(""+i);

        //给底部添加一个JPanel
        JPanel bottomJPanel = new JPanel();

        //组装底部按钮
        bottomJPanel.add(startBtn);
        bottomJPanel.add(preBtn);
        bottomJPanel.add(nextBtn);
        bottomJPanel.add(pagesSelect);
        bottomJPanel.add(jumpBtn);
        bottomJPanel.setBackground(new Color(198,251,208));

        //将按钮添加到主窗口
        add(bottomJPanel,BorderLayout.SOUTH);


        //开始绘制的点击事件
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(b){
                    pad.stopPaint();
                    removeItem(pad);
                }
                else b = true;
                pad.drawPath(pad.getCurrentPathId());
                setTitle("第"+(pad.getCurrentPathId()+1)+"种方法");
            }
        });

        //下一张的点击事件
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(b){
                    pad.stopPaint();
                    removeItem(pad);
                }
                else b = true;
                if (pad.getCurrentPathId()+3>pathNum)pad.drawPath(0);
                else pad.drawPath(pad.getCurrentPathId()+1);
                setTitle("第"+(pad.getCurrentPathId()+1)+"种方法");
            }
        });

        //上一张的点击事件
        preBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(b){
                    pad.stopPaint();
                    removeItem(pad);
                }
                else b = true;
                if(pad.getCurrentPathId()-1<0)pad.drawPath(pathNum-2);
                else pad.drawPath(pad.getCurrentPathId()-1);
                setTitle("第"+(pad.getCurrentPathId()+1)+"种方法");
            }
        });

        //跳转的点击事件
        jumpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(b){
                    pad.stopPaint();
                    removeItem(pad);
                }
                else b = true;
                pad.drawPath(pagesSelect.getSelectedIndex());
                setTitle("第"+(pad.getCurrentPathId()+1)+"种方法");
            }
        });
    }
    void removeItem(PaintPad pad){//把组件移除的函数
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                pad.remove(pad.pathPad);
            }
        });
        thread.start();
        try {
            thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    private void getPathXY(){//获取xy，
        Calculate calculate = new Calculate(Row,Col);
        if (Row%2 == 0) {
            calculate.calculates(0,0,1);
        }else {
            calculate.calculatesX(0,0,1);
        }
        this.pathNum = calculate.getNumPath()+1;
        PathX = calculate.getPathX();
        PathY = calculate.getPathY();
    }

}