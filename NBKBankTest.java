package com.mco.bankApp;
import java.util.InputMismatchException;
import java.util.Scanner;
public class NBKBankTest 
{
	static String s="https://www.icicibank.com/contact-us/contact-us.page";

	public static void main(String[] args) throws InterruptedException
	{
		Scanner sc = new Scanner(System.in);
		Bank bk = new Bank();
		BankAccount ba;
		Valoidation1 m=new Valoidation1();
		String nm1=null;
		long mobno1=0;
		int age1=0;
		int	j=0;
		int sAno=1000;
		int cAno=5000;

		int choice;
		System.out.println("           Welcome To Online Banking");
		System.out.println("                   Please Wait");
		Thread.sleep(2000,200);
		System.out.println("    Please enter any option to opt your Service");
		Thread.sleep(2000,200);
		try{
			do{

				System.out.println();
				System.out.println("         Main Menu");

				System.out.println("1. Create New Account");
				System.out.println("2. Deposite Ammount");
				System.out.println("3. Withdraw ammount ");
				System.out.println("4) Print short account information");
				System.out.println("5) Print the detailed account information including last transactions");
				System.out.println("6) Exit");
				System.out.print("Please enter your choice: ");
				choice = sc.nextInt();
				if(choice<0||choice>6){
					System.err.println("Invalid choice,try again");
					return;
				}
				switch (choice) 
				{

				case 1: System.out.println("Enter your name");
				String nm = sc.next();
				boolean bo = m.chekName(nm);
				if(bo)
					nm1=nm;
				else
				{
					System.err.println(" Enter valid Name, The name should not Contain Special Characters & Numbers");
					System.exit(1);
				}

				sc.nextLine();

				System.out.println("Enter mobile number ");
				long mobno=sc.nextLong();
				boolean b=m.checkMno(mobno);
				if(b)
					mobno1=mobno;
				else
				{
					System.err.println("Enter valid mobile number, mobile number must be exactly 10 digits");
					System.exit(1);
				}

				System.out.println("Enter your age,that must be 18 years above, then only we can create account for you");
				int age=sc.nextInt();
				boolean a= m.checkAge(age);
				if(a)
					age1=age;
				else
				{
					if(age<=18)
					{
						j=18-age;
						System.err.println("we are sorry con't create an account for you please try after "+j+" years");
						System.exit(2);
					}
					else
						System.err.println("Respected Sir We are Extremely Sorry, your too late to create an account");
					System.exit(1);
					throw new InvalidPassException("UnMatched input");
				}


				System.out.println("Please enter account type"
						+ "\n1.savings"
						+ "\n2.current");
				int sv=sc.nextInt();

				if(sv==1)
				{    
					System.out.println("Enter opening balance, minimum balance must be Rs.500/- ");
					int d = sc.nextInt();
					boolean k=m.checkMulOfHund(d);
					if(k)
					{
						if(d>=500)
						{
							String	actype="Savings";
							int acNo=sAno;
							sAno++;

							System.out.println("Congratulations Your Account is created Succusfully And Your Account number is: " 
									+ bk.openNewAccount(nm1, d,mobno1,age,actype,acNo)+"\n  for more information please visit  "+s);


						}
						else
						{
							System.err.println("Sorry we con't create an account,please deposit minimum balance of Rs.500/-");
						}
					}
					else
						System.err.println("Invalid input,Please enter multiple of hundreds only");
				}
				else if(sv==2)
				{

					System.out.println("Enter opening balance, minimum balance must be Rs.5000/- ");
					
					int d = sc.nextInt();
					
					boolean k=m.checkMulOfHund(d);
					if(k)
					{
						if(d>=5000)
						{
							String	actype="Current";
							int acNo=cAno;
							cAno++;

							System.out.println("Congratulations Your Account is created Succusfully And Your Account number is: "
									+ bk.openNewAccount(nm1, d,mobno1,age1,actype,acNo)+"\n for more information please visit  "+s);


						}
						else
						{
							System.err.println("Sorry we con't create an account,please deposit minimum balance of Rs.5000/-");
						}

					}
					else
						System.err.println("Invalid input,Please enter multiple of hundreds only");
				}
				else
				{
					System.err.println("Invalid input, Try again");
				}
				break;

				case 2: 
					System.out.println("Enter your  account number");
					int an = sc.nextInt();
					System.out.println("Enter amount to deposit");
					int da = sc.nextInt();
					boolean k=m.checkMulOfHund(da);
					if(k)
						bk.depositTo(an, da);
					else
						System.err.println("Please enter multiple of hundreds only");
					break;

				case 3: System.out.println("Enter your account number");
				int acn = sc.nextInt();
				System.out.println("Enter amount to withdraw");
				int wa = sc.nextInt();
				boolean x=m.checkMulOfHund(wa);
				if(x)
					bk.withdrawFrom(acn, wa);
				else
					System.err.println("Please enter multiple of hundreds only");
				break;

				case 4:
					
					System.out.println("Enter your account number");
				int anum = sc.nextInt();
				System.out.println("                   Account Information ");
				bk.printAccountInfo(anum);
			
				break;
				case 5: System.out.println("Enter your account number");
				anum = sc.nextInt();
				System.out.println("                   Account with Transaction Inforamtion");
				bk.printAccountInfo(anum);
				bk.printTransactionInfo(anum);
				break;
				case 6:System.out.println("Thank you: please provide your valible feedback which will help us to improve ourselves");
				System.out.println("1. Excelent");
				System.out.println("2. Average");
				System.out.println("3. Ok ");
				System.out.println("4. Not Satisfied");
				int feed=sc.nextInt();
				System.out.println("Thanq you Visit Again");
				System.exit(1);

				}
				
				Thread.sleep(3000);
			}
			while(choice!='6');
			
		}
		catch ( InvalidPassException |InputMismatchException e) 
		{
			if( e instanceof InvalidPassException)
			{
				System.err.println("invalid input");
			}
			
			else if(e instanceof InputMismatchException)
				
			System.err.println("Invalid input your input,Please Try Again");
			
		} 

	}
}
class Bank
{
	private BankAccount[] accounts; 
	private int numOfAccounts; 


	public Bank() 
	{
		accounts = new BankAccount[100];
		numOfAccounts = 0;
	}

	BankAccount b;
	public int openNewAccount(String customerName, int openingBalance,long mobno, int age,String acType, int acNo) 
	{

		b = new BankAccount(customerName, openingBalance,mobno,age,acType,acNo);
		accounts[numOfAccounts] = b;
		numOfAccounts++;
		return b.getAccountNum();
	}


	public void withdrawFrom(int accountNum, int amount) 
	{
		for (int i =0; i<numOfAccounts; i++)
		{
			if(accountNum == accounts[i].getAccountNum() ) 
			{ 

				boolean k=accounts[i].withdraw(amount);
				if(k)
				System.out.println(amount+" Amount withdrawn successfully  from your Account");
				return;

			}
		}
		System.err.println("Account number not found.");
	}


	public void depositTo(int accountNum, int amount) 
	{
		for (int i =0; i<numOfAccounts; i++) 
		{
			if (accountNum == accounts[i].getAccountNum() ) 
			{
		
				accounts[i].deposit(amount);
				/*int intre=(amount*1*5)/100;
				int total=amount+intre;*/
				
				System.out.println(amount+" Amount deposited successfully in your account ");
				return;
			}
		}
		System.err.println("Account number not found.");
	}


	public void printAccountInfo(int accountNum) 
	{
		for (int i =0; i<numOfAccounts; i++) 
		{
			if (accountNum == accounts[i].getAccountNum() ) 
			{
				int nOt=accounts[i].getNumberOfTransactions();
				System.out.println(accounts[i].getAccountInfo()+" \nNo of Transations Made for your Account are : "+nOt);
				System.out.println("Simple Interst for your total Balance as of now is : "+accounts[i].getsimInt());
				System.out.println("Compound Interest for your total Balance as of now is :"+accounts[i].getComInt());
				return;
			}
		}
		System.out.println("Account number not found.");
	}

	public void printTransactionInfo(int accountNum) 
	{
		for (int i =0; i<numOfAccounts; i++)
		{
			if (accountNum == accounts[i].getAccountNum() ) 
			{
				//System.out.println(accounts[i].getAccountInfo());
				System.out.println("Last transaction: " + accounts[i].getTransactionInfo(accounts[i].getNumberOfTransactions()-1));
				//System.out.println("No of Transations Made for your Account are : "+accounts[i].getNumberOfTransactions());
				
				return;
			}
		}
		System.err.println("Account number not found.");
	}



	public void printAccountInfo(int accountNum, int n) 
	{
		for (int i =0; i<numOfAccounts; i++) 
		{
			if (accountNum == accounts[i].getAccountNum() ) 
			{
				System.out.println(accounts[i].getAccountInfo());
				System.out.println(accounts[i].getTransactionInfo(n));
				System.out.println("Simple Interst for your total Balance as of now is : "+accounts[i].getsimInt());
				System.out.println("Compound Interest for your total Balance as of now is :"+accounts[i].getComInt());
				return;
			}
		}
		System.err.println("Account number not found.");
	}

}







class BankAccount
{

	private int accountNum;
	private String customerName;
	private long mNo;
	private int age;
	String acType;
	int balance;
	private double[] transactions;
	private String[] transactionsSummary;
	private int numOfTransactions;
	private static int noOfAccounts=0;
	private int sintRat=5;
	private int cintRat=8;

	public int getsimInt()
	{
		if(acType.equalsIgnoreCase("Savings"))
		{
			int sint=(this.balance*sintRat*1)/100;
			return sint;
		}
		else
		{
			int cint=(this.balance*cintRat*1)/100;
			return cint;
		}
	}
	public int getComInt()
	{
		if(acType.equalsIgnoreCase("Savings"))
		{
			int p=(1+sintRat/100);
			int sint=(int) (this.balance*Math.pow(p, 1));
			System.out.println("interest rate is : "+sintRat);
			return sint;
		}
		else
		{
			int p=(1+cintRat/100);
			int cint=(int) (this.balance*Math.pow(p, 1));
			System.out.println("interest rate is : "+cintRat);
			return cint;
		}
	}
	
	public String getAccountInfo()
	{
		String s=Converter.convert(balance);
		return "Customer Name: " + customerName +"\nAccount number : " + accountNum +
				"\nAge : "+age+"\naccount Type : "+acType+" \nBalance : "
				+ balance+" \nBalance in Words : " +s+" rupees only";
	}

	public String getTransactionInfo(int n)
	{
		String transaction = transactionsSummary[n];
		if (transaction == null) 
		{
			return "No transaction exists with that number.";
		}
		else
		{
			return transaction;
		}
	}

	public BankAccount(String abc, int xyz, long mNo, int age,String acType,int accountNum)
	{
		customerName = abc;
		balance = xyz;
		this.mNo=mNo;
		this.age=age;
		this.acType=acType;
		noOfAccounts ++;
		this.accountNum = accountNum;
		transactions = new double[100];
		transactionsSummary = new String[100];
		transactions[0] = balance;
		transactionsSummary[0] = "A balance of : $" + Double.toString(balance) + " was deposited.";
		numOfTransactions = 1;
	}

	public int getAccountNum()
	{
		return accountNum;

	}

	public int getNumberOfTransactions()
	{
		return numOfTransactions;
	}

	public boolean deposit(int amount)
	{

		if (amount<=0) 
		{
			System.err.println("Amount to be deposited should be positive");
			return false;
		}
		else 
		{
			boolean bol=Valoidation1.checkMulOfHund(amount);
			if(bol)
			{
				balance = balance + amount;
				transactions[numOfTransactions] = amount;
				transactionsSummary[numOfTransactions] = "$" + Double.toString(amount) + " was deposited.";
				numOfTransactions++;
				return true;
			}
			else
			{
				System.err.println("Invalid Ammount");
				return false;
			}	
		}
	}
	public boolean withdraw(int amount)
	{
		boolean b=false;
		long g=balance-amount;
		if (amount<=0)
		{
			System.err.println("Amount to be withdrawn should be positive");
			return b;
		}


		else if (balance < amount) 
		{
			System.err.println("Insufficient balance");return b;
		}
		else if(balance<=amount)
		{
			System.err.println("Sorry you con't withdraw full ammount, should maintain minimum balance of Rs.500/-");
			return b;
		}
		else if(g<500)
		{
			System.out.println("Sorry your trying to withdraw more ammount without maintaining minimum balance ");
			return b;
		}
		else
		{
			boolean bol=Valoidation1.checkMulOfHund(amount);
			if(bol)
			{
				balance = balance - amount;
				transactions[numOfTransactions] = amount;
				transactionsSummary[numOfTransactions] = "$" + Double.toString(amount) + " was withdrawn.";
				numOfTransactions++;
				return true;
			}
			else
			{	System.err.println("Invalid Ammount");
			return b;
			}
		}






	}









}



