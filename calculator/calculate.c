#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<math.h>
#include<windows.h>
//��һ�����������
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
//�ڶ������������
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
//100�������������ȡ�����
int createnumber()
{
	return rand() % 100+1;
}
//20������������������ȡ�����
int createnumber2()
{
	return rand() % 20+1;
}
//������������
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
//100������������
void exercises1(int n)
{
	int i,rightnumber=0,wrongnumber=0;
	float a,b,c,answer,result1,result2;
	char op,op2;
	srand(time(NULL));
	for(i=0;i<n;i++)
	{
		a=(float)createnumber();//��ȡ�����
		b=(float)createnumber();
		c=(float)createnumber();
		op=createnumberop();//��ȡ��������
		op2=createnumberop();
		result1=calculate2(calculate(a,op,b),op2,c);//������������ȼ��ó����
		result2=calculate(a,op,calculate2(b,op2,c));
		if(result1 < 0 || result2 <0)//������Ϊ�������������»�ȡ��Ŀ
		{
			i--;
			continue;
		}
		else{
			printf("%.f %c %.f %c %.f = ",a,op,b,op2,c);
			scanf("%f",&answer);
			if((op=='+' || op=='-') && (op2=='*' || op2=='/'))//���ڶ�����������ȼ�����
			{
				if((int)(100.0*answer+0.5)/100.0==(int)(100.0*result2+0.5)/100.0 && result2 >=0)//�������뾫ȷ��С�������λ
				{
					printf("���ش���ȷ!��\n\n");
					rightnumber++;
				}
				else
				{
					printf("���ش����!���ǣ�%.2f��\n\n",(int)(100.0*result2+0.5)/100.0);
					wrongnumber++;
				}
			}
			else
			{
				if((int)(100.0*answer+0.5)/100.0==(int)(100.0*result1+0.5)/100.0 && result1 >=0)
				{
					printf("���ش���ȷ!��\n\n");
					rightnumber++;
				}
				else
				{
					printf("���ش����!���ǣ�%.2f��\n\n",(int)(100.0*result1+0.5)/100.0);
					wrongnumber++;
				}
			}
		}
	}
	printf("%d�������ܹ������%d�⣬׼ȷ��Ϊ%.2f��!\n",n,rightnumber,(float)rightnumber/(float)n*100);
	system("pause");
	system("CLS");
	printf("\n\n");
}
//��ȡ������
double gongbeishu(double b,double d)
{
	double i,max;
	max=b>d?b:d;//�ȽϷ�ĸ��С
	for(i=max;;i++)
		if((int)(i)%(int)(b)==0 && (int)(i)%(int)(d)==0)//������С������
			break;
		return i;
}
//��ȡ��Լ��
double gongyueshu(double a,double c)
{
	double i,min;
	min=a<c?a:c;//�ȽϷ��Ӵ�С
	for(i=2;i<=min;i++)
		if((int)(a)%(int)(i)==0 && (int)(c)%(int)(i)==0)//������С��Լ��
			break;
		if(i>min)
			i=1;
		return i;
}
//20������������������ȡ�����
void exercises2(int n)
{
	int i,answer[2],rightnumber=0,wrongnumber=0;
	double a,b,c,d;
	double beishu1,beishu2,fenzihe0,fenmu,fenzihe1,fenzicha0,fenzicha1,fenzichen1,fenzichu1;
	char op,op2;
	srand(time(NULL));
	for(i=0;i<n;i++)
	{
		a=(double)createnumber2();//��ȡ�����
		b=(double)createnumber2();
		c=(double)createnumber2();
		d=(double)createnumber2();
		op=createnumberop();//��ȡ��������
		//op2=createnumberop();//���������Ҫ���������������
		beishu1=gongbeishu(b,d)/b;//���ݷ�ĸ��С���㱶��
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
				//printf("%.f/%.f\n",fenzihe1,fenmu);//����
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzihe1/(int)fenmu && op2=='/')
				{
					printf("���ش���ȷ!��\n\n");
					rightnumber++;
				}
				else
				{
					printf("���ش����!���ǣ�%.f/%.f��\n\n",fenzihe1,fenmu);
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
				//printf("%.f/%.f\n",fenzicha1,fenmu);//����
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzicha1/(int)fenmu && op2=='/')
				{
					printf("���ش���ȷ!��\n\n");
					rightnumber++;
				}
				else
				{
					printf("���ش����!���ǣ�%.f/%.f��\n\n",fenzicha1,fenmu);
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
				//printf("%d/%d\n",(int)fenzichen1,(int)fenmu);//����
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzichen1/(int)fenmu && op2=='/')
				{
					printf("���ش���ȷ!��\n\n");
					rightnumber++;
				}
				else
				{
					printf("���ش����!���ǣ�%.f/%.f��\n\n",fenzichen1,fenmu);
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
				//printf("%d/%d\n",(int)fenzichu1,(int)fenmu);//����
				printf("(%.f/%.f) %c (%.f/%.f) = ",a,b,op,c,d);
				scanf("%d%c%d",&answer[0],&op2,&answer[1]);
				if(answer[0]/answer[1]==(int)fenzichu1/(int)fenmu && op2=='/')
				{
					printf("���ش���ȷ!��\n\n");
					rightnumber++;
				}
				else
				{
					printf("���ش����!���ǣ�%.f/%.f��\n\n",fenzichu1,fenmu);
					wrongnumber++;
				}
			}
		}
	}
	printf("��%d�⵱�����ܹ������%d�⣬׼ȷ��Ϊ%.2f��!\n\n",n,rightnumber,(float)rightnumber/(float)n*100);
	system("pause");
	system("CLS");
	printf("\n\n");
}

int main()
{
	int m,n;
	printf("\n\n\n\t\t\t��Copyright 2018-2019 ����Ө.All Rights Reserved.��\n\n");
	while(1)
	{
		printf("\t��������Ҫѡ���ϰ�⣺\n");
		printf("\n\t1.100������������ (�������뾫ȷ��С�������λ)\n\n\t2.20�������������������\n\n");
		do{
			printf("ѡ��Ϊ��");
			scanf("%d",&m);
			if(m!=1 && m!=2)
				printf("��������������������!��\n");
		}while(m!=1 && m!=2);
		printf("\n�����������������Ŀ��");
		do{
			scanf("%d",&n);
			if(n<=0)
				printf("����Ŀ��������������!��\n");
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


