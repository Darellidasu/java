package com.mco.bankApp;

import java.util.Random;
import java.util.Scanner;

public class Valoidation1 {
	int amt,bal;

	Scanner sc=new Scanner(System.in);
	
	public boolean chekName(String nm)
	{
		char[] ch=nm.toCharArray();
		int i = 0;
		for (; i < ch.length; i++) 
		{

			if((ch[i] >= 'a' && ch[i] <= 'z') || (ch[i] >= 'A' && ch[i] <= 'Z') || (ch[i]==32))
				continue;
			else

				break;
		}
		return i==ch.length;

	}



	public boolean checkMno(long mno)
	{
		int count = 0;

		while(mno != 0)
		{
			mno = mno/10;

			count++;
		}
		if(count==10)
			return true;
		else
			return false;
	}

	boolean checkAge(int age)
	{
		if(age>=18&age<=90)
			return true;
		else
			return false;
	}
	/*public int deposit() 
	{
		System.out.print("Enter amount to deposit:");
		amt = sc.nextInt();
		if (amt < 0) 
		{
			System.out.println("Invalid Amount");
			return 1;
		}
		bal = bal + amt;
		return amt;
	}*/
	int genRandom(int i)
	{	Random r= new Random();
		if(i==1)
		{
		
			int res=r.nextInt(1000)+1;	
			return res;
		}
		else
		{
			int res=r.nextInt(5000)+1;	
			return res;
		}
	}
	
	public static boolean checkMulOfHund(int i)
	{
			return (i%100==0);
	}

}
