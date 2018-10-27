package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FactorialCalculations {
    public List<String> correctAnswerList;

    public FactorialCalculations(){
        correctAnswerList = new ArrayList<>();
    }

    //随机产生的阶乘数字
    public int generateFormula() {
        Random random = new Random();
        int num = random.nextInt(19)+1;
        //计算阶乘结果
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result*= i;
        }
        correctAnswerList.add(String.valueOf(result));
        return num;
    }
}


