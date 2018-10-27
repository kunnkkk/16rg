package main;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame implements Runnable{
    private JPanel outPanel;  //  输出面板
    private JLabel timeLabel;  //  时间显示
    private JLabel scoreLabel;  //  答题状态标签
    private JButton model;      //  当前题目格式
    private JLabel textInput;   //  题目输入框提示
    private JTextField input;   //  题目输入框
    private JButton startButton;    //  开始按钮
    private JLabel[] titleLabel;    //  题目标签
    private JTextField[] answerArea;  //  答题区
    private JudgeLabel[] judgeLabel; //判断用户答题是否准确
    private boolean fourCalculation = true; //  是否有四则运算
    private boolean factorial = false;     //  是否有阶乘
    private Thread time;                   //   计时线程
    private int numT;//计算用户正确答题数
    private int numF;//计算用户错误答题数
    FourCalculations fourCalculations;
    FactorialCalculations factorialCalculations;

    public Window() {
        this.init();
    }
    //  初始化
    private void init() {
//JudgeLabel test = new JudgeLabel();
//test.setBorder(BorderFactory.createLineBorder(Color.gray));
//test.setBounds(30,39,200,200);
//test.setImageUrl("./FourOperations/src/main/img/error.png");
//this.add(test);
        this.setTitle("自动出题系统");
        this.setBounds(0,0,800,600);
        this.initCommand(this);   //  初始化控制界面
        this.initOutPanel();
        this.setLayout(null);
        this.setVisible(true);  //  显示
        this.setDefaultCloseOperation(EXIT_ON_CLOSE ); //  关闭窗口时结束
    }

    //  初始化输出面板
    private void initOutPanel() {
        outPanel = new JPanel(null);
        outPanel.setBounds(50,30,500,500);
        outPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.add(outPanel);
    }

    //  初始化控制台
    private void initCommand(Window window) {
        int startX = 600, startY = 50;
        int width = 150, height = 50;
        int step = 80;

        //  时间显示
        timeLabel = new JLabel("用时 00:00",JLabel.CENTER);
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.black));  //  边框和边框颜色
        timeLabel.setFont(new Font("宋体", Font.BOLD, 20)); //  字体
        timeLabel.setBounds(startX, startY, width, height); //  区域大小
        this.add(timeLabel);

        //  用户输入提示框
        textInput = new JLabel("题目个数",JLabel.CENTER);
        textInput.setBorder(BorderFactory.createLineBorder(Color.black));  //  边框和边框颜色
        textInput.setFont(new Font("宋体", Font.BOLD, 20));  //  字体
        textInput.setBounds(startX, startY+step, width, height); //  区域大小
        this.add(textInput);

        //  用户输入框
        input = new JTextField();
        input.setHorizontalAlignment(JTextField.CENTER);   //  水平居中
        input.setBounds(startX, startY+step*2, width, height);
        input.setVisible(true);
        this.add(input);

        //  四则运算按钮
        model = new JButton("当前:四则运算");
        model.setBounds(startX, startY+step*3, width, height);
        model.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (model.getText() == "当前:四则运算") {
                    model.setText("当前:阶乘");
                    fourCalculation = false;
                    factorial = true;
                } else {
                    model.setText("当前:四则运算");
                    fourCalculation = true;
                    factorial = false;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(model);

        //  答题状态中
        scoreLabel = new JLabel("答题状态中：",JLabel.CENTER);
        scoreLabel.setFont(new Font("宋体", Font.BOLD, 20));  //  字体
        scoreLabel.setForeground(Color.red);  //  颜色
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.red));
        scoreLabel.setBounds(startX, startY+step*3, width, height);
        scoreLabel.setVisible(false);    //  设置初始不可见
        this.add(scoreLabel);

        //  开始按钮
        startButton = new JButton("开始");
        startButton.setBounds(startX, startY+step*4, width, height);
        startButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {   //  如果鼠标释放
                if (startButton.getText() == "开始") {    //  如果是开始做题
                    if (!checkTitleNum()) {              //  如果输入不符合要求
                        input.setText("题目数应该在 1 -- 5 之内");
                        input.addMouseListener(new MouseInputListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                input.setText("");
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                            }

                            @Override
                            public void mouseDragged(MouseEvent e) {

                            }

                            @Override
                            public void mouseMoved(MouseEvent e) {

                            }
                        });
                        return;
                    }
                    startButton.setText("交卷");
                    scoreLabel.setVisible(true);
                    model.setVisible(false);
                    initTitle();         //  初始化题库
                    setJudgeLabelVisible(false);
                    time = new Thread(window);
                    time.start();

                } else {
                    submit();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(startButton);

        //  背景色按钮
        JButton changeButton = new JButton("背景色");
        changeButton.setBounds(startX, startY+step*5, width, height);
        changeButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color color = JColorChooser.showDialog(outPanel, "背景色", outPanel.getBackground());    // 弹出颜色界面供用户自由选择背景色
                if (color != null) {
                   outPanel.setBackground(color);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(changeButton);
    }

    //  检查题数目是否合法
    private boolean checkTitleNum() {
        boolean ret = true;
        String str = input.getText();
        int num;

        for(int i = 0; i < str.length(); i++) {
            int chr = str.charAt(i);
            if (chr<48 || chr>57) {
                return false;
            }
        }
        num = Integer.parseInt(str);
        if (num <= 0 || num > 5)
            ret = false;
        return ret;
    }

    //  初始化题库
    private void initTitle() {
        int startX = 20, startY = 15;
        int width = 350 ,height = 20;
        int stepX = 10, stepY = 50;
        outPanel.removeAll();
        numT=0;
        numF=0;
        fourCalculations = new FourCalculations();
        factorialCalculations=new FactorialCalculations();
        int num = Integer.parseInt(input.getText());  //  题目数
        titleLabel = new JLabel[num];
        answerArea = new JTextField[num];
        judgeLabel=new JudgeLabel[num];
        if(fourCalculation == true) {
            for (int i = 0; i < num; i++) {                // 题目区
                titleLabel[i] = new JLabel("第" + (i + 1) + "题:" + fourCalculations.generateFormula());
                titleLabel[i].setFont(new Font("宋体", Font.BOLD, 15));
                titleLabel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
                titleLabel[i].setBounds(startX, startY + stepY * i, width, height);

                answerArea[i] = new JTextField(); // 答题区
                answerArea[i].setBounds(startX+stepX+350, startY+stepY*i, 50, height);

                judgeLabel[i]=new JudgeLabel();  // 显示√、×的图标区
                judgeLabel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
                judgeLabel[i].setBounds(startX+stepX*2+400,startY+stepY*i,30,height);
                judgeLabel[i].setVisible(false);

                outPanel.add(titleLabel[i]);
                outPanel.add(answerArea[i]);
                outPanel.add(judgeLabel[i]);
            }
    }
        else{
            for (int i = 0; i < num; i++) {
                titleLabel[i] = new JLabel("第" + (i + 1) + "题:" + factorialCalculations.generateFormula()+"!"+"=");
                titleLabel[i].setFont(new Font("宋体", Font.BOLD, 15));
                titleLabel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
                titleLabel[i].setBounds(startX, startY + stepY * i, width, height);

                answerArea[i] = new JTextField(); //答题区
                answerArea[i].setBounds(startX+stepX+350, startY+stepY*i, 50, height);

                judgeLabel[i]=new JudgeLabel();
                judgeLabel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
                judgeLabel[i].setBounds(startX+stepX*2+400,startY+stepY*i,30,height);
                judgeLabel[i].setVisible(false);

                outPanel.add(titleLabel[i]);
                outPanel.add(answerArea[i]);
                outPanel.add(judgeLabel[i]);
            }
        }
        outPanel.updateUI();
    }

    //  答案判断
    private void markingPapers() {
        if(fourCalculation == true) {
            for (int i = 0; i < answerArea.length; i++) {
                //System.out.println("answer:"+fourCalculations.correctAnswerList.get(i));
                //System.out.println("input:"+answerArea[i].getText());
                if (answerArea[i].getText().equals(fourCalculations.correctAnswerList.get(i))) {
                    judgeLabel[i].setImageUrl("./FourOperations/src/main/img/right.png");   // 正确，显示“√”图标
                    numT++;
                }
                else {
                    //answerArea[i].setText("错");
                    judgeLabel[i].setImageUrl("./FourOperations/src/main/img/error.png");   // 错误，显示“×”图标
                    answerArea[i].setText(fourCalculations.correctAnswerList.get(i));        //将用户输入的错误答案修改为正确答案
                    numF++;
                }
            }
        }
        else{
            for (int i = 0; i < answerArea.length; i++) {
                //System.out.println("answer:"+fourCalculations.correctAnswerList.get(i));
                //System.out.println("input:"+answerArea[i].getText());
                if (answerArea[i].getText().equals(factorialCalculations.correctAnswerList.get(i))) {
                    judgeLabel[i].setImageUrl("./FourOperations/src/main/img/right.png");   // 正确，显示“√”图标
                    numT++;
                }
                else {
                    //answerArea[i].setText("错");
                    judgeLabel[i].setImageUrl("./FourOperations/src/main/img/error.png");   // 错误，显示“×”图标
                    answerArea[i].setText(factorialCalculations.correctAnswerList.get(i));    //将用户输入的错误答案修改为正确答案
                    numF++;
                }
            }
        }
    }

    //  设置答案判断标签是否可见
    private void setJudgeLabelVisible(boolean visible){
        for(int i = 0; i < judgeLabel.length; i++){
            judgeLabel[i].setVisible(visible);
        }
    }

    // 时间线程显示用户答题时间
    @Override
    public void run() {      //  完成时间计时
        int second = 0;
        int minute = 0;
        String time;
        timeLabel.setText("用时 00:00");    // 初始化用时
        while (true) {
            try {
                Thread.sleep(1000);   //  按时间设置
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            second++;
            if (second == 60) {
                second = 0;
                minute++;
            }
            if (minute < 10) {
                time = "用时 " + "0" + minute + ":";
            } else {
                time = "用时 " + minute + ":";
            }
            if (second < 10) {
                time = time + "0" + second;
            } else {
                time = time + second;
            }
            if (minute == 2) {      //  如果时间到120s，用户还未答完题，提示信息
                JOptionPane.showMessageDialog(this, "时间已到，不能答题！","提示",JOptionPane.WARNING_MESSAGE);
                submit();
                return;
            }
            timeLabel.setText(time);
        }
    }

    private void submit() { //  交卷
        markingPapers();   //  判卷
        startButton.setText("开始");
        scoreLabel.setVisible(false);
        model.setVisible(true);
        setJudgeLabelVisible(true);
        time.stop();
        JOptionPane.showMessageDialog(this, "答题结束！"+ "\n"+"你共做对"+numT+"道题，做错"+numF+"道题！","得分",JOptionPane.QUESTION_MESSAGE);
        // 弹框显示用户正确答题数和错误答题数
    }

    // 主函数
    public static void main(String[] args) {
        Window window = new Window();
    }
}
