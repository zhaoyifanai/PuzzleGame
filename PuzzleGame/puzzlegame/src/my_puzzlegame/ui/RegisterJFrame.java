package my_puzzlegame.ui;

/*
 * 注册界面*/

import javax.swing.*;

public class RegisterJFrame extends JFrame {

    //空参构造  初始化值
    public RegisterJFrame(){
        //调用setSize方法设置界面大小
        this.setSize(488,500);
        //设置界面标题
        this.setTitle("注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //显示界面
        this.setVisible(true);

    }
}

