#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<math.h>
#include<windows.h>
//第一个运算符计算
float calculate(float x,char op,float y)
{
	float result;
	switch(op)
	{
	case'+':result = x + y;break;
	case'-':result = x - y;break;
	case'*':result = x * y;break;
	case'/':result = x / y;break;
	}
	return result;
}
//第二个运算符计算
float calculate2(float x,char op2,float y)
{
	float result;
	switch(op2)
	{
	case'+':result = x + y;break;
	case'-':result = x - y;break;
	case'*':result = x * y;break;
	case'/':result = x / y;break;
	}
	return result;
}
//100以内四则运算获取随机数
int createnumber()
{
	return rand() % 100+1;
}
//20以内真分数四则运算获取随机数
int createnumber2()
{
	return rand() % 20+1;
}
//随机产生运算符
char createnumberop()
{
	int op;
	op=rand() % 4+1;
	switch(op)
	{
	case 1:return'+';
	case 2:return'-';
	case 3:return'*';
	case 4:return'/';
	}
	return 0;
}
//100以内四则运算
void exercises1(int n)
{
	int i,rightnumber=0,wrongnumber=0;
	float a,b,c,answer,result1,result2;
	char op,op2;
	srand(time(NULL));
	for(i=0;i<n;i++)
	{
		a=(float)createnumber();//获取随机数
		b=(float)createnumber();
		c=(float)createnumber();
		op=createnumberop();//获取随机运算符
		op2=createnumberop();
		result1=calculate2(calculate(a,op,b),op2,c);//根据运算符优先级得出结果
		result2=calculate(a,op,calculate2(b,op2,c));
		if(result1 < 0 || result2 <0)//如果结果为非正数，则重新获取题目
		{
			i--;
			continue;
		}
		else{
			printf("%.f %c %.f %c %.f = ",a,op,b,op2,c);
			scanf("%f",&answer);
			if((op=='+' || op=='-') && (op2=='*' || op2=='/'))//若第二个运算符优先级更大
			{
				if((int)(100.0*answer+0.5)/100.0==(int)(100.0*result2+0.5)/100.0 && result2 >=0)//四舍五入精确到小数点后两位
				{
					printf("【回答正确!】\n\n");
					rightnumber++;
				}
				else
				{
					printf("【回答错误!答案是：%.2f】\n\n",(int)(100.0*result2+0.5)/100.0);
					wrongnumber++;
				}
			}
			else
			{
				if((int)(100.0*answer+0.5)/100.0==(int)(100.0*result1+0.5)/100.0 && result1 >=0)
				{
					printf("【回答正确!】\n\n");
					rightnumber++;
				}
				else
				{
					printf("【回答错误!答案是：%.2f】\n\n",(int)(100.0*result1+0.5)/100.0);
					wrongnumber++;
				}
			}
		}
	}
	printf("%d题中你总共答对了%d题，准确率为%.2f％!\n",n,rightnumber,(float)rightnumber/(float)n*100);
	system("pause");
	system("CLS");
	printf("\n\n");
}
//获取公倍数
double gongbeishu(double b,double d)
{
	double i,max;
	max=b>d?b:d;//比较分母大小
	for(i=max;;i++)
		if((int)(i)%(int)(b)==0 && (int)(i)%(int)(d)==0)//计算最小公倍数
			break;
		return i;
}
//获取公约数
double gongyueshu(double a,double c)
{
	double i,min;
	min=a<c?a:c;//比较分子大小
	for(i=2;i<=min;i++)
		if((int)(a)%(int)(i)==0 && (int)(c)%(int)(i)==0)//计算最小公约数
			break;
		if(i>min)
			i=1;
		return i;
}
//20以内真分数四则运算获取随机数
void exercises2(int n)
{
	int i,answer[2],rightnumber=0,wrongnumber=0;
	double a,b,c,d;
	double beishu1,beishu2,fenzihe0,fenmu,fenzihe1,fenzicha0,fenzicha1,fenzichen1,fenzichu1;
	char op,op2;
	srand(time(NULL));
	for(i=0;i<n;i++)
	{
		a=(double)createnumber2();//获取随机数
		b=(double)createnumber2();
		c=(double)createnumber2();
		d=(double)createnumber2();
		op=createnumberop();//获取随机运算符
		//op2=createnumberop();//若真分数需要两个运算符则启用
		beishu1=gongbeishu(b,d)/b;//根据分母大小计算倍数
		beishu2=gongbeishu(b,d)/d;
		if(op=='+')
		{
			fenzihe0=beishu1*a + beishu2*c;
			fenmu=gongbeishu(b,d); 
			fenzihe1=fenzihe0/gongyueshu(fenzihe0,fenmu);
			fenmu=fenmu/gongyueshu(fenzihe0,fenmu);
			if(fenzihe1<0 || fenmu<=0)
			{
				i--;
				continue;
			}
			else
			{
				//printf("%.f/%.f\n",fenzihe1,fenmu);//检查答案
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzihe1/(int)fenmu && op2=='/')
				{
					printf("【回答正确!】\n\n");
					rightnumber++;
				}
				else
				{
					printf("【回答错误!答案是：%.f/%.f】\n\n",fenzihe1,fenmu);
					wrongnumber++;
				}
			}
		}
		else if(op=='-')
		{
			fenzicha0=beishu1*a - beishu2*c;
			fenmu=gongbeishu(b,d); 
			fenzicha1=fenzicha0/gongyueshu(fenzicha0,fenmu);
			fenmu=fenmu/gongyueshu(fenzicha0,fenmu);
			if(fenzicha1<0 || fenmu<=0)
			{
				i--;
				continue;
			}
			else
			{
				//printf("%.f/%.f\n",fenzicha1,fenmu);//检查答案
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzicha1/(int)fenmu && op2=='/')
				{
					printf("【回答正确!】\n\n");
					rightnumber++;
				}
				else
				{
					printf("【回答错误!答案是：%.f/%.f】\n\n",fenzicha1,fenmu);
					wrongnumber++;
				}
			}
		}
		else if(op=='*')
		{
			fenzichen1=a*c;
			fenmu=b*d;
			if(fenzichen1<0 || fenmu<=0)
			{
				i--;
				continue;
			}
			else
			{
				//printf("%d/%d\n",(int)fenzichen1,(int)fenmu);//检查答案
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzichen1/(int)fenmu && op2=='/')
				{
					printf("【回答正确!】\n\n");
					rightnumber++;
				}
				else
				{
					printf("【回答错误!答案是：%.f/%.f】\n\n",fenzichen1,fenmu);
					wrongnumber++;
				}
			}
		}
		else if(op=='/')
		{
			fenzichu1=a*d;
			fenmu=b*c;
			if(fenzichu1<0 || fenmu<=0)
			{
				i--;
				continue;
			}
			else
			{
				//printf("%d/%d\n",(int)fenzichu1,(int)fenmu);//检查答案
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzichu1/(int)fenmu && op2=='/')
				{
					printf("【回答正确!】\n\n");
					rightnumber++;
				}
				else
				{
					printf("【回答错误!答案是：%.f/%.f】\n\n",fenzichu1,fenmu);
					wrongnumber++;
				}
			}
		}
	}
	printf("在%d题当中你总共答对了%d题，准确率为%.2f％!\n\n",n,rightnumber,(float)rightnumber/(float)n*100);
	system("pause");
	system("CLS");
	printf("\n\n");
}

int main()
{
	int m,n;
	printf("\n\n\n\t\t\t【Copyright 2018-2019 郭晓莹.All Rights Reserved.】\n\n");
	while(1)
	{
		printf("\t请输入你要选择的习题：\n");
		printf("\n\t1.100以内四则运算 (四舍五入精确到小数点后两位)\n\n\t2.20以内真分数的四则运算\n\n");
		do{
			printf("选择为：");
			scanf("%d",&m);
			if(m!=1 && m!=2)
				printf("【输入有误，请重新输入!】\n");
		}while(m!=1 && m!=2);
		printf("\n请输入四则运算的数目：");
		do{
			scanf("%d",&n);
			if(n<=0)
				printf("【数目有误，请重新输入!】\n");
		}while(n<=0);
		if(m==1)
		{
			exercises1(n);
		}
		else
			exercises2(n);
	}
	return 0;
}


