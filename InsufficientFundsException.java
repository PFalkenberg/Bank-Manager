/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.manager;

/**
 *
 * @author Paul
 */
public class InsufficientFundsException extends Exception{
    
    public InsufficientFundsException(){
        super("Insufficient funds to perform that operation");
    }
    
    public InsufficientFundsException(String message){
        super(message);
    }
}