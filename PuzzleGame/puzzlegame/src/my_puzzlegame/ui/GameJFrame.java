package my_puzzlegame.ui;

/*游戏的主界面
 * */

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //创建一个二维数组
    int[][] data = new int[4][4];

    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    //定义一个变量，记录当前展示图片的路径
    String path = "image\\ncwu2\\1\\";

    //定义一个二维数组，存储正确数
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //定义一个变量，计步
    int step = 0;

    //创建选项下面的条目对象
    JMenuItem changeImage = null;
    JMenuItem replayItem = null;
    JMenuItem reloginItem = null;
    JMenuItem closeItem = null;
    JMenuItem nuiItem = null;

    //创建一个Random对象
    Random r = new Random();

    //空参构造  初始化值
    public GameJFrame(){

        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        InitImage();

        //显示界面
        this.setVisible(true);
    }

    private void initData() {

        //1.定义一个一维数组
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //2.打乱数据
        for (int i = 0; i < tempArr.length; i++) {
            int temp = 0;
            //获取一个随机数
            int index = r.nextInt(16);
            //交换
            temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        /*//3.遍历数组
        for (int i : tempArr) {
            System.out.print(tempArr[i] + " ");
        }
        System.out.println();*/

        /*//4.创建一个二维数组
        int[][] data = new int[4][4];*/

        //5.添加数据到二维数组
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }

            data[i / 4][i % 4] = tempArr[i];
        }
    }

    //初始化图片
    private void InitImage() {

        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();


        if (victory()){
            //显示胜利图标
            JLabel win = new JLabel(new ImageIcon("image\\win.png"));
            win.setBounds(203,283,197,73);
            this.getContentPane().add(win);
        }

        JLabel stepCount = new JLabel("步数 : " + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);



        //外循环，执行四次
        for (int i = 0; i < 4; i++) {
            //内循环，执行四次
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                //创建一个JLabel对象  （管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(path+ num +".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * j + 84,105 * i + 134,105,105);
                //给图片添加边框   0:边框凸起  1：边框下凹
                jLabel.setBorder(new BevelBorder(0));
                //将管理容器放到界面中
                this.getContentPane().add(jLabel);   //先获取窗体

            }
        }

        //添加背景图片
        ImageIcon bg = new ImageIcon("image\\background.png");
        JLabel background = new JLabel(bg);

        background.setBounds(40,40,508,560);
        //将背景图添加到界面
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

    }

    private void initJMenuBar() {

        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上的选项对象  （功能）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我");

        //创建选项下面的条目对象
        changeImage = new JMenuItem("更换图片");
        replayItem = new JMenuItem("重新游戏");
        reloginItem = new JMenuItem("重新登录");
        closeItem = new JMenuItem("关闭游戏");
        nuiItem = new JMenuItem("学校");

        //将条目添加到选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(nuiItem);

        //将选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给条目绑定事件
        changeImage.addActionListener(this);
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);

        nuiItem.addActionListener(this);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //调用setSize方法设置界面大小
        this.setSize(603,680);
        //设置界面标题
        this.setTitle("拼图游戏");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置
        this.setLayout(null);

        //添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按住不松时调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        this.getContentPane().removeAll();
        //a 65
        if (keyCode == 65){
            //将界面中所有图片删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path+"\\all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            ImageIcon bg = new ImageIcon("puzzlegame\\my_puzzlegame\\image\\background.png");
            JLabel background = new JLabel(bg);
            background.setBounds(40,40,508,560);
            //将背景图添加到界面
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利
        if (victory()){
            return;
        }

        //对 上下左右 进行判断
        //左 ：37    上 ： 38   右 ： 39   下 ：40
        int keyCode = e.getKeyCode();
        //进行判断 移动图片即交换二维数组数据
        if (keyCode == 37){
            System.out.println("Left");
            //判断
            if (y == 3){
                //表示空白方格在最右方，其右方无图片无法再移动
                return;
            }

            //x  ,y + 1 表示空白方格右方的数字
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            //每移动一次，计数器就自增一次
            step++;
            //调用最新的数据加载图片
            InitImage();

        }else if (keyCode == 38){
            System.out.println("Up");
            //判断
            if (x == 3){
                //表示空白方格在最下方，其下方无图片无法再移动
                return;
            }
            //将空白方格数字往上移动
            //x ,y   表示空白方格
            //x+1  ,y 表示空白方格下方的数字

            //交换数据
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            //每移动一次，计数器就自增一次
            step++;
            //调用最新的数据加载图片
            InitImage();


        }else if (keyCode == 39){
            System.out.println("Right");
            //判断
            if (y == 0){
                //表示空白方格在最左方，其左方无图片无法再移动
                return;
            }

            //x  ,y - 1 表示空白方格左方的数字
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            //每移动一次，计数器就自增一次
            step++;
            //调用最新的数据加载图片
            InitImage();

        }else if (keyCode == 40){
            System.out.println("Down");

            //判断
            if (x == 0){
                //表示空白方格在最上方，其上方无图片无法再移动
                return;
            }
            //x-1  ,y 表示空白方格上方的数字
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            //每移动一次，计数器就自增一次
            step++;
            //调用最新的数据加载图片
            InitImage();
        }else if (keyCode == 65){
            InitImage();
            // w 87
        }else if (keyCode == 87){
            data  =new  int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            InitImage();
        }
    }

    //判断是否胜利
    public boolean victory(){
        //判断data和win数据是否相同
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if(obj == replayItem){
            System.out.println("replay");

            //计步器清零
            step = 0;
            //再次打乱二维数组中的数据
            initData();
            //重新加载图片
            InitImage();


        }else if(obj == reloginItem){
            System.out.println("relogin");

            //关闭当前的游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame();

        }else if(obj == closeItem){
            System.out.println("exit");

            //直接关闭虚拟机即可
            System.exit(0);

        }else if(obj == nuiItem){
            System.out.printf("university");

            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创见容器对象
            JLabel jLabel = new JLabel(new ImageIcon("image\\ncwu2\\ncwu.jpg"));
            //设置位置和宽高
            jLabel.setBounds(0,0,258,258);
            //把图片加到弹框中
            jDialog.getContentPane().add(jLabel);
            //设置弹框大小
            jDialog.setSize(344,344);
            //弹框置顶
            jDialog.setAlwaysOnTop(true);
            //弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭将无法操作下面得界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);

        }else if (obj == changeImage){
            //从1-6中随机获取一个数字
            int number = r.nextInt(6) + 1;
            //重新获取路径
            path = "image\\ncwu2\\"+ number+"\\";
            //计步器清零
            step = 0;
            //打乱二维数组中的数据
            initData();
            //重新加载图片
            InitImage();
        }
    }
}
