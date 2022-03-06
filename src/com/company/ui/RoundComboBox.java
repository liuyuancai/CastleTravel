package com.company.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class RoundComboBox extends JComboBox {

    public RoundComboBox() {
        setUI(new MyComboBoxUI());
    }

}

class MyComboBoxUI extends BasicComboBoxUI {

    private static ImageIcon DOWN_ICON = new ImageIcon("images/arrow.png");

    private static Color DEFAULT_COLOR = new Color(214, 150, 254);

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);

        JComboBox comboBox = (JComboBox) c;
        comboBox.setFocusable(true);
        comboBox.setBackground(null);
        comboBox.setOpaque(false);
        comboBox.setBorder(null);
        comboBox.setRenderer(new MyListCellRenderer());
    }

    @Override
    protected JButton createArrowButton() {
        // 也可以使用BasicComboBoxUI里的arrowButton对象
        JButton arrow = new JButton();
        // 设置自己定义的UI
        arrow.setUI(new MyButtonUI());
        // 设置图标
        arrow.setIcon(DOWN_ICON);
        // 设置无法获得焦点
        arrow.setFocusable(false);
        // 设置边距，调整图标位置
        arrow.setMargin(new Insets(0, 0, 0, 5));
        //设置arrow大小
        arrow.setPreferredSize(new Dimension(150, 25));
        arrow.setBackground(Color.white);
        return arrow;
    }

    @Override
    public void paint(Graphics g, JComponent c) {

        // 也可以使用BasicComboBoxUI里的combobox对象
        JComboBox comboBox = (JComboBox) c;

        hasFocus = comboBox.hasFocus();

        Rectangle r = rectangleForCurrentValue();

        // JComboBox的textfield的绘制,并不是靠Renderer来控制
        // 它会通过paintCurrentValueBackground来绘制背景
        // 然后通过paintCurrentValue去绘制显示的值
        Graphics2D g2d = (Graphics2D) g;
        if (!comboBox.isEditable()) {
            paintCurrentValueBackground(g2d, r, hasFocus);
            paintCurrentValue(g2d, r, hasFocus);
        } else {
            paintCurrentValueBackground(g2d, r, hasFocus);
        }

        // 获取焦点时，用不同颜色来区分
        if (comboBox.hasFocus()) {
            g2d.setColor(DEFAULT_COLOR);
        } else {
            g2d.setColor(Color.WHITE);
        }

        // 边框透明度
        //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        // 绘制边框，后两个参数控制圆角
        // 边框也有占位，所以宽高都需要减去2，否则会导致边框不全
        g2d.drawRoundRect(0, 0, comboBox.getWidth() - 2, comboBox.getHeight() - 2, 20, 20);
    }

    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup popup = (BasicComboPopup) super.createPopup();
        // 获取到popup，为其设置边框，和combobox的颜色保持同步
        popup.setBorder(BorderFactory.createLineBorder(DEFAULT_COLOR));
        return popup;
    }
}

class MyButtonUI extends BasicButtonUI implements SwingConstants {

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JButton button = (JButton) c;
        button.setContentAreaFilled(false);//父类不用绘制内容
        button.setFocusPainted(false);//父类不用绘制焦点
        button.setBorderPainted(false);//父类不用绘制边框
    }


}

class MyListCellRenderer implements ListCellRenderer {

    private DefaultListCellRenderer defaultCellRenderer = new DefaultListCellRenderer();

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // 每一行，都转换成jlabel来处理
        JLabel renderer = (JLabel) defaultCellRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        //字体靠左
        renderer.setHorizontalAlignment(JLabel.LEFT);
        // 每一行的jlabel的颜色
        if (isSelected) {
            renderer.setBackground(new Color(214, 150, 254));
            renderer.setForeground(Color.WHITE);
        } else {
            renderer.setBackground(null);
        }
        //左侧padding 文本距离框框的距离
        renderer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        renderer.setPreferredSize(new Dimension(40, 25));

        // list背景色，也就是向下的按钮左边儿那一块儿
        list.setSelectionBackground(null);
        list.setBorder(null);

        return renderer;
    }

}