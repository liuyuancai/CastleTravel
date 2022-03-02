package com.company.ui;
import javax.swing.*;
import java.awt.*;


public class PaintPad extends JPanel {//画板
    private JTextArea[][] textArea;
    private int blockWidth ;
    private int blockHeight ;
    int mRow ;//行
    int mCol ;//列
    int [][] PathX;//X的坐标组
    int [][] PathY;//Y的坐标组
    public PathPad pathPad;//画板上的遮罩


    public int getCurrentPathId() {//获得第几页id
        return currentPathId;
    }//获取第几种方法的id

    private int currentPathId = 0;//第几种方法id


    public PaintPad(int row, int col, int[][] PathX, int[][] PathY, int length){
        blockWidth = 60;
        blockHeight = 60;
        mRow = row;
        mCol = col;
        this.PathX = PathX;
        this.PathY = PathY;
        this.blockHeight = length;
        this.blockWidth = length;
        this.setSize(col*blockWidth,row*blockHeight);
        this.setLayout(new GridLayout(row,col,0,0));
        textArea = new JTextArea[row][col];
        for (int i = 0; i < row; i++) {//创建背景
            for (int j = 0; j < col; j++) {
                textArea[i][j] = new JTextArea(20,20);
                if((i+j)%2 == 0){
                    textArea[i][j].setBackground(new Color(218,253,254));
                }else {
                    textArea[i][j].setBackground(new Color(180,177,243));
                }
                textArea[i][j].setEditable(false);
                this.add(textArea[i][j]);
            }
        }
    }
    public void stopPaint(){
        pathPad.b = false;
    }//停止绘画的函数
    public void drawPath(int x){//画路线的函数
        this.currentPathId = x;
        pathPad = new PathPad(mRow,mCol,PathX,PathY,blockWidth);
        pathPad.setBounds(0,0,getWidth(),getHeight());
        pathPad.paintPath(x);
        this.add(pathPad);
        repaint();
    }

}

