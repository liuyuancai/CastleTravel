package com.company.algorithm;


public class Calculate {
    int n,m;//n:行数 m:列数
    int[][] map ;//地图
    int[][] pathX ;//x坐标组
    int[][] pathY ;//y坐标组
    int[] path1 ;//x坐标暂存区
    int[] path2 ;//y坐标暂存区
    int num ;//记录第几组坐标
    public Calculate(int n, int m){
        this.n = n;
        this.m = m;
        map = new int[n+1][m+1];
        pathX = new int[100][n*m];
        pathY = new int[100][n*m];
        path1 = new int[n*m];
        path2 = new int[n*m];
        num = 0;
        initX();
    }
    void initX(){//初始化值
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j]=0;
            }
        }
        map[0][0] = 1;
        path1[0] = 0;
        path2[0] = 0;
        return;
    }
    //利用dfs计算路线
    public void calculates(int x,int y,int step){//计算路线
        if(step>n + m)for(int i = 0; i < n-2;i++){
            int isClose = 0;
            if(i > 5)break;
            for(int j = n - 2 - i,t = 0;t < i + 2;j++,t++){
                if(map[j][t] == 1)isClose++;
            }
            if(isClose == 2 + i && y > (x + n - i - 1)){
                return;
            }
        }
        if(num == 100)return;
        if(x == n - 1 && y == 0){

            if(step == n * m){
                for(int i = 0;i < step; i++){
                    pathX[num][i] = path1[i];
                    pathY[num][i] = path2[i];
                }
                num++;
            }
            return;
        }
        //向右试探
        if(y != m)if(map[x][y+1] == 0 && y + 1 < m){
            map[x][y+1] = 1;
            path1[step] = x;
            path2[step] = y+1;
            calculates(x,y+1,step+1);
            map[x][y+1] = 0;
        }
        //向左试探
        if(y!=0)if(map[x][y-1] == 0 && y > 0){
            map[x][y-1] = 1;
            path1[step] = x;
            path2[step] = y-1;
            calculates(x,y-1,step+1);
            map[x][y-1] = 0;
        }
        //向下试探
        if(x != n)if(map[x + 1][y] == 0 && x + 1 < n){
            map[x+1][y] = 1;
            path1[step] = x+1;
            path2[step] = y;
            calculates(x+1,y,step+1);
            map[x+1][y] = 0;
        }
        //向上试探
        if(x!=0)if(map[x-1][y] == 0 && x > 0){
            map[x-1][y] = 1;
            path1[step] = x-1;
            path2[step] = y;
            calculates(x-1,y,step+1);
            map[x-1][y] = 0;
        }
        return;
    }

    //利用dfs计算路线
    public void calculatesX(int x,int y,int step){//计算路线
        if(step>n + m)for(int i = 0; i < n-2;i++){
            int isClose = 0;
            if(i > 5)break;
            for(int j = n - 2 - i,t = 0;t < i + 2;j++,t++){
                if(map[j][t] == 1)isClose++;
            }
            if(isClose == 2 + i && y > (x + n - i - 1)){
                return;
            }
        }
        if(num == 100)return;
        if(x == n - 1 && y == 0){

            if(step == n * m){
                for(int i = 0;i < step; i++){
                    pathX[num][i] = path1[i];
                    pathY[num][i] = path2[i];
                }
                num++;
            }
            return;
        }
        //向右试探
        if(map[x][y+1] == 0 && y + 1 < m){
            map[x][y+1] = 1;
            path1[step] = x;
            path2[step] = y+1;
            calculatesX(x,y+1,step+1);
            map[x][y+1] = 0;
        }
        //向下试探
        if(map[x + 1][y] == 0 && x + 1 < n){
            map[x+1][y] = 1;
            path1[step] = x+1;
            path2[step] = y;
            calculatesX(x+1,y,step+1);
            map[x+1][y] = 0;
        }
        //向上试探
        if(x!=0)if(map[x-1][y] == 0 && x > 0){
            map[x-1][y] = 1;
            path1[step] = x-1;
            path2[step] = y;
            calculatesX(x-1,y,step+1);
            map[x-1][y] = 0;
        }
        //向左试探
        if(y!=0)if(map[x][y-1] == 0 && y > 0){
            map[x][y-1] = 1;
            path1[step] = x;
            path2[step] = y-1;
            calculatesX(x,y-1,step+1);
            map[x][y-1] = 0;
        }
        return;
    }
    public int[][] getPathX() {//获取x坐标组
        return pathX;
    }//获取X的坐标组
    public int[][] getPathY() {//获取y坐标组
        return pathY;
    }//获取Y的坐标组
    public int getNumPath(){//获取路径的总数量
        return num;
    }//获取总共有几条路线
}