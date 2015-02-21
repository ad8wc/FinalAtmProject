/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl.is3816;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author ad8wc
 */
public class Main {
    //Main method [project-name].java
ArrayList <Account>  account = new ArrayList();
private Calendar currentdate = new GregorianCalendar();
private Calendar futuredate = new GregorianCalendar();
    //Create Object
    public static void main(String args []) throws ParseException, IOException{
        Main Atm = new Main();
        //ArrayList for Accounts
     int choice; 
     int AccountNumb=99;
     
    //Read Accounts - *Later*
     Atm.readacccount();
   // Atm.ReadAccount
        Atm.create();
    //Menu stating the name of the program
    Scanner sc= new Scanner(System.in);
    //Create Menu (Pick Account)
        System.out.println("Pick Account ");
        System.out.println(" Please enter a choice ");
        System.out.println(" 1 for account1 ");
        System.out.println(" 2 for account2 ");
        System.out.println(" 3 for account3 ");
        System.out.println(" 4 for Exit ");
        choice = sc.nextInt();
    //Take inputs (Switch Case Statement)
       switch (choice){
           case 1:
               AccountNumb = choice - 1;
                break;
           case 2:
               AccountNumb = choice - 1;
               break;
           case 3:
               AccountNumb = choice - 1;
               break;
           case 4:
               System.exit(0);
               break;
           default:
               System.exit(0);
               break;
       }
    //Create Menu (ATM)
        System.out.println(" Please enter a choice ");
        System.out.println(" Deposit (d)");
        System.out.println(" Withdraw (w)");
        System.out.println(" CheckBalance(c)");
        System.out.println(" Exit(e) ");
        char letter = sc.next().charAt(0);
       
    //Take inputs (Switch Case Statement)
        double amount = 0;
    switch (letter){
        case 'd':
            System.out.println( "please deposit amount");
            amount = sc.nextDouble();
            Atm.account.get(AccountNumb).deposit(amount);
            Atm.futureDate();
            Atm.futuredate = Atm.account.get(AccountNumb).calcInterest(Atm.currentdate, Atm.futuredate);
            Atm.printBalance(AccountNumb);
            break;
         case 'w':
             System.out.println( "please withdraw amount");
            amount = sc.nextDouble();
            Atm.account.get(AccountNumb).withdraw(amount);
            Atm.futureDate();
            Atm.futuredate = Atm.account.get(AccountNumb).calcInterest(Atm.currentdate, Atm.futuredate);
            Atm.printBalance(AccountNumb);
            break;
         case 'c':
            System.out.println( "GetBalance");
            Atm.printBalance(AccountNumb);
            break;
         case 'e':
            System.out.println( "Exit");
            break;
         default:
            System.out.println( "Exit");
            break;
        
    }
    
    Atm.saveaccount();
    }
    public void create( ) {
    if(account.isEmpty()){
        System.out.println("No Accounts, Creating Accounts\n");
        Checking ch = new Checking();
        Saving sa = new Saving();
        account.add(sa);
        account.add(ch);
        ch = new Checking();
        account.add(ch);
    }
}
    //Save Accounts
    public void saveaccount() throws IOException{
        try{
            System.out.println("Saving Accounts\n");
            FileOutputStream fout = new FileOutputStream("list.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this.account);
            oos.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    //Read Accounts Method - *Later*
    public void readacccount(){
        try{
            System.out.println("Reading Accounts\n");
            FileInputStream fin = new FileInputStream("list.dat");
            ObjectInputStream ois = new ObjectInputStream(fin);
            this.account = (ArrayList) ois.readObject();
        }catch (Exception e){
            //System.out.println(e);
        }
//        File file = new File ( "Account.txt");
//        FileReader fr = new FileReader(file);
//        fr.read();
    }
    
    public void futureDate() throws ParseException{
         System.out.println(" what is the futureDate");
         Scanner sc= new Scanner(System.in);
         SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/yy");        
         futuredate.setTime(sdf.parse(sc.nextLine()));
    }
    
    public void printBalance(int accountnum){
        double balance = this.account.get(accountnum).getBalance();
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        
        System.out.println(cf.format(balance));
    }
}
