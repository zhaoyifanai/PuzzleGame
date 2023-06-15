package my_puzzlegame.ui;

/*
 * 登录界面*/

import javax.swing.*;

public class LoginJFrame extends JFrame{

    //空参构造  初始化值
    public LoginJFrame(){
        //调用setSize方法设置界面大小
        this.setSize(488,430);  //this表示在APP主方法中创建对象的地址值
        //设置界面标题
        this.setTitle("登录");
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


