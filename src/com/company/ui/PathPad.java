package com.company.ui;
import javax.swing.*;
import java.awt.*;

public class PathPad extends JPanel {
    private Thread t;
    int n,m;
    int [][] PathX;
    int [][] PathY;
    private int lineLength;
    boolean b = true;
    public PathPad(int n, int m, int[][] PathX, int[][] PathY, int length){
        this.n = n;
        this.m = m;
        this.PathX = PathX;
        this.PathY = PathY;
        this.lineLength = length;
    }

    public void paintPath(int x){
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int j = 0; j < n * m - 1 && b; j++){
                    try {
                        t.sleep(75);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Graphics2D graphics = (Graphics2D)getGraphics();//获取画笔
                    BasicStroke stroke = new BasicStroke(4.0f);//调整线条的粗细
                    graphics.setStroke(stroke);
                    graphics.setColor(new Color(252,194,219));
                    graphics.drawLine(
                            PathY[x][j]*lineLength+lineLength/2,
                            PathX[x][j]*lineLength+lineLength/2,
                            PathY[x][j+1]*lineLength+lineLength/2,
                            PathX[x][j+1]*lineLength+lineLength/2);//画路线
                }
            }
        });
        t.start();
    }
}
