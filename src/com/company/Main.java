package com.company;
import com.company.ui.MainWindow;
import com.company.ui.RoundBtn;
import com.company.ui.RoundComboBox;
import com.company.ui.RoundDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main {

    RoundComboBox rowSelect = new RoundComboBox();
    RoundComboBox colSelect = new RoundComboBox();

    JLabel jLabel1 = new JLabel("行");
    JLabel jLabel2 = new JLabel("列");


    RoundBtn button = new RoundBtn(15,15,65,27,new JLabel("生成"));

    public void init(){
        JFrame jFrame = new JFrame("CastleTravel");
        jFrame.setBounds(700,110,350,87);
        jFrame.setResizable(false);

        JPanel selectPanel = new JPanel();
        selectPanel.setBackground(new Color(173,201,241));

        for(int i = 3; i <= 20; i++) rowSelect.addItem(""+i);
        for(int i = 6; i <= 20; i++) colSelect.addItem(""+i);

        jLabel1.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jLabel2.setFont(new Font("微软雅黑",Font.PLAIN,15));

        Rectangle recOfSelectPanel = jFrame.getBounds();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((rowSelect.getSelectedIndex()+3)%2 != 0 && (colSelect.getSelectedIndex()+4)%2 == 0){
                    RoundDialog roundDialog = new RoundDialog("没有找到路径!");
                }
                else {
                    MainWindow mainWindow = new MainWindow(rowSelect.getSelectedIndex()+3,colSelect.getSelectedIndex()+6);
                    mainWindow.setLocation(recOfSelectPanel.x,recOfSelectPanel.y+jFrame.getHeight());
                    mainWindow.setResizable(false);
                    mainWindow.setVisible(true);
                }
            }
        });

        selectPanel.add(rowSelect);
        selectPanel.add(jLabel1);
        selectPanel.add(colSelect);
        selectPanel.add(jLabel2);
        selectPanel.add(button);


        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        jFrame.add(selectPanel);
        jFrame.setVisible(true);
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//更改默认组件样式
        new Main().init();
    }
}
