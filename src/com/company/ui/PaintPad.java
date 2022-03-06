package com.company.ui;
import javax.swing.*;
import java.awt.*;


public class PaintPad extends JPanel {//画板
    private Thread t;

    private int lineLength;
    boolean isDrawLine ;
    private JTextArea[][] textArea;
    private int blockWidth ;
    private int blockHeight ;
    int mRow ;//行
    int mCol ;//列
    int [][] PathX;//X的坐标组
    int [][] PathY;//Y的坐标组
//    public PathPad pathPad;//画板上的遮罩


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
        this.lineLength = length;
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
        this.isDrawLine = false;
    }//停止绘画的函数


    public void drawPath(int x){//画路线的函数
        this.currentPathId = x;
        repaint();
        paintPath(currentPathId);
    }

    public void paintPath(int x){
        isDrawLine = true;
        int[] time = {50};
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isDrawLine) {
                    for (int j = 0; j < mRow * mCol - 1 && isDrawLine; j++) {
                        try {
                            t.sleep(time[0]);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Graphics2D graphics = (Graphics2D) getGraphics();//获取画笔
                        BasicStroke stroke = new BasicStroke(4.0f);//调整线条的粗细
                        graphics.setStroke(stroke);
                        graphics.setColor(new Color(252, 194, 219));
                        graphics.drawLine(
                                PathY[x][j] * lineLength + lineLength / 2,
                                PathX[x][j] * lineLength + lineLength / 2,
                                PathY[x][j + 1] * lineLength + lineLength / 2,
                                PathX[x][j + 1] * lineLength + lineLength / 2);//画路线
                    }
                    time[0] = 1;
                }
            }
        });
        t.start();
    }

}

