
package edu.umsl.is3816;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author ad8wc
 */
public abstract class Account implements Serializable {
    //Double Balance
    private double balance = 0;
    //Double Interest
    protected double interest = 0;
    //Deposit Method
    public void deposit(double deposit){
     balance = balance + deposit;   
        
    } 
    //Withdrawl Method
    public void withdraw(double withdraw){
        balance = balance - withdraw;
    }
    //Set Balance Method
    public void setBalance(double setBalance){
    balance = setBalance;
    }
    
    //Get Balance Method
    public double getBalance(){
    return balance;
    }
    
    public Calendar calcInterest(Calendar currentDate, Calendar futureDate){
        long currentmillis = currentDate.getTimeInMillis();
        long futuremillis = futureDate.getTimeInMillis();
        long millis = futuremillis - currentmillis;
        
        double dailyInterest = (interest/100) * (balance/365);
        
        balance = balance + (dailyInterest * (millis / (1000*60*60*24)));

        return futureDate; 
    }
}
