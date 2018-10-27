package main;

import java.util.Random;
import java.util.Scanner;

public class CalculationEngine {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();   //获取答题开始时间
        System.out.print("请选择你要进行的操作：1.简单四则运算   2.阶乘运算   3.退出系统");
        Scanner select = new Scanner(System.in);  //记录用户的选择
        int Select = select.nextInt();
        if (Select==1){
        // 进行简单四则运算
            FourCalculations simpleFourOperation = new FourCalculations();
            simpleFourOperation.init();
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入题目个数：");
            int titleNum = handleUserInput(scanner);// 题目数量
            if (titleNum<=5){
                //随机生成正整数和分数的题目数量，不多于5道题
                Random random = new Random();//设随机数种子随机生成题目
                int simpleFourOperationNum = random.nextInt(titleNum) + 1;
                int FractionOperationNum = titleNum - simpleFourOperationNum;//剩余的题目数
                //记录用户做对相应题目的正确数
//               // int simpleFourOpTrueNum = simpleFourOperation.generateSimpleFourOpExp(simpleFourOperationNum, scanner);
//               // int fractionOpTrueNum = fractionOperation.generateFractionOpExp(FractionOperationNum, scanner);
//                //计算用户答题总分数
//               // int trueNum = simpleFourOpTrueNum + fractionOpTrueNum;//记录用户正确答题数
//                int falseNum = titleNum - trueNum;//记录用户错误答题数
//               // int score = 100 * trueNum / titleNum;
                //System.out.println("恭喜你完成本次练习!共做对" + trueNum + "道题," + "做错" + falseNum + "道题," + "总分数为：" + score);
                long endTime = System.currentTimeMillis(); //获取结束时间
                System.out.println("程序运行时间为：" + (endTime - startTime) + "ms"); //输出程序运行总时间，即用户答题总时间
            }
            else{
                System.out.print("最多不能超过5道题!");
            }
        }
        // 进行阶乘运算
        else if (Select==2){
            int trueNum = 0;
            System.out.print("请输入题目个数：");
            Scanner Timu = new Scanner(System.in);
            int timu = Timu.nextInt();
            for (int a=0;a<timu;a++){
                Random random = new Random();
                int num = random.nextInt(10);
                int result = 1;
                if(timu==0){
                    result=1;
                }
                else {
                    for (int i = 1; i <= num; i++) {
                        result *= i;
                    }
                }
                System.out.println(num + "请输入你的答案：");
                Scanner Answer = new Scanner(System.in);// 获取用户输入
                int answer = Answer.nextInt();
                // 倒计时代码
                // for(int i=10;i>0;i--){
                //try {
                // Thread.sleep(1000);
                // }
                // catch (InterruptedException e) {
                // e.printStackTrace();
                // }
               // }
                /*判断是否超时*/
                // if (){
                if (answer==result) {
                    System.out.println("恭喜你计算正确!");
                    trueNum++;
                    }
                else {
                    System.out.println("计算错误!正确答案为：" + result + "\n");
                    }
                    // }
                    // else{//超时，跳出该循环
                    // System.err.print("超时，停止回答\n");
                    //continue;
                   // }
            }
            System.out.println("恭喜你完成本次练习!共做对"+trueNum+"道题,做错"+(timu-trueNum)+"题");
            long endTime = System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间为：" + (endTime - startTime) + "ms"); //输出程序运行时间
        }//若用户输入的选择为3，则退出系统
        else{
            System.out.print("退出系统!");
        }
    }
    //对用户的非法输入进行处理
    public static int handleUserInput(Scanner scanner) {
        int titleNum;
        do {
            titleNum = scanner.nextInt();
            if (titleNum > 0)
                break;
            else {
                System.out.println("输入有误!不能输入非法字符以及小于0，请重新输入!");
                System.out.println("请输入题目个数：");
            }
        } while (!(titleNum > 0));
        return titleNum;
    }
}