/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    * https://blogs.oracle.com/jrubinoff/entry/portability_and_performance_in_jax
    * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaServerFaces.html
 */
package com.corejsf;

import java.io.Serializable;
import javax.inject.Named;
// fails here
import javax.faces.bean.RequestScoped;
/////////////////////////////
import java.util.List;
import javax.faces.bean.SessionScoped;
// or 
import javax.xml.ws.WebServiceRef;

import com.corejsf.ConversionRate;//.ws.Forecast;
import com.corejsf.ConversionRateResponse;//ws.ForecastReturn;
import com.corejsf.Convert;//.ws.Weather;
import com.corejsf.Currency;
import com.corejsf.CurrencyConvertor;
import com.corejsf.ObjectFactory;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

//====================

import java.sql.*; //.DriverManager;
//import java.sql.ResultSet;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.ResultSetImpl;
//import java.mysql;
////////////////////////////
/**
 *
 * @author 
 * 
 *   public String value() {
        return name();
    }

    public static Currency fromValue(String v) {
        return valueOf(v);
    }
    * * Webservice
    * https://blogs.oracle.com/jrubinoff/entry/portability_and_performance_in_jax
    * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaServerFaces.html
 */
//@Named ("convert") //TO DO: ("convert") use convert for XHTML or @ManagedBean
@ManagedBean(name="convert")
@RequestScoped
public class Convert implements Serializable {
        //@WebServiceRef(wsdlLocation="http://www.webservicex.net/CurrencyConvertor.asmx?WSDL")
        private String stotal = new String ("10.00"); 
        private double total = 1;  // TO balance deposit
        private double amount = 1; // FROM balance deposit
        private double result = 0.00;
        // fCurrency
        private String fCurrency = "USD";
        private double from = 0;
        //tCurrency 
        private String tCurrency = "EUR";
        private double to = 0;
        //===============================
        //private String UserID = "BillGates";
        //String user = "root";
        //String pass = "1470";
        private double USD = 0.00;
        private double EUR = 0.00;
        private double JPY = 0.00;
        private double GBP = 0.00;
        private double INR = 0.00;
        //===============================
        //public String getUserID(){ return this.UserID; }
        public String getfCurrency(){ return this.fCurrency; }
        public String gettCurrency(){ return this.tCurrency; }
        public double getfrom(){ return this.from; }
        public double getto(){ return this.to; }
        public double getamount(){ return this.amount; }
        public double gettotal(){ return this.total; }
        public double getresult(){ return this.result; }
        public double getUSD(){ return this.USD; }
        public double getEUR(){ return this.EUR; }
        public double getJPY(){ return this.JPY; }
        public double getGBP(){ return this.GBP; }
        public double getINR(){ return this.INR; }
        
        // update input text
        //public void setUserID(String UserID) { this.UserID = UserID; }
        public void setfCurrency(String fCurrency) { this.fCurrency = fCurrency; }
        public void settCurrency(String tCurrency) { this.tCurrency = tCurrency; }
        public void setfrom(double from){ this.from = from; }
        public void setto(double to){ this.to = to; }
        public void setamount(double amount){ this.amount = amount; }
        public void setresult(double result){ this.result = result; }
        public void settotal(double total){ this.total = total; } //
        public void setUSD(double USD){ this.USD = USD; }
        public void setEUR(double EUR){ this.EUR = EUR; }
        public void setJPY(double JPY){ this.JPY = JPY; }
        public void setGBP(double GBP){ this.GBP = GBP; }
        public void setINR(double INR){ this.INR = INR; }
        //public String getSTotal () { return stotal; }
        //===============================
       // public String getSTotal () { // OK works
       //     return Rate(com.corejsf.Currency.USD, com.corejsf.Currency.EUR); 
       // }
        //================================
        public String getSTotal () { // OK works
            //return Rate(com.corejsf.Currency.valueOf("USD"), com.corejsf.Currency.valueOf("EUR")); // OK works
            
            com.corejsf.Currency fromCurrency = com.corejsf.Currency.valueOf(fCurrency);
            System.out.println("fromCurrency (stotal) = " + fromCurrency.toString());
            com.corejsf.Currency toCurrency = com.corejsf.Currency.valueOf(tCurrency);
            System.out.println("toCurrency (stotal) = " + toCurrency.toString());
            return Rate(fromCurrency, toCurrency); // null pointer
        }
        //================================
        public Double getTotal () { 
            com.corejsf.Currency fromCurrency = com.corejsf.Currency.valueOf(fCurrency);
            System.out.println("fromCurrency (total) = " + fromCurrency.toString());
            com.corejsf.Currency toCurrency = com.corejsf.Currency.valueOf(tCurrency);
            System.out.println("toCurrency (total) = " + toCurrency.toString());
            return convertCurrency(fromCurrency, toCurrency, amount);
            //return convertCurrency(com.corejsf.Currency.valueOf(fCurrency), com.corejsf.Currency.valueOf(tCurrency), amount);
        
        }

        //================================ 
        
        

         // Convert 
    public String converted() { // static
        try {
            //======= added for forms currencies ======
            //this.setfCurrency(fCurrency);
            //this.settCurrency(tCurrency);
            System.out.println("fCurrency (converted) = " + fCurrency);
            System.out.println("tCurrency (converted) = " + tCurrency);
            System.out.println("Amount of Currency (converted) = " + amount);
            //=========================================

            // rate: Convert 1 USD to 1 EUR - OK
            com.corejsf.Currency fromCurrency = com.corejsf.Currency.valueOf(fCurrency);
            //com.corejsf.Currency fromCurrency = com.corejsf.Currency.USD;
            com.corejsf.Currency toCurrency = com.corejsf.Currency.valueOf(tCurrency);
            //com.corejsf.Currency toCurrency = com.corejsf.Currency.EUR;

            
            // stotal = Double.toString(total); // original 
            //======== test new ====
            stotal = Rate (fromCurrency, toCurrency);
            System.out.println("My Rate (converted) = " + stotal);
            //=======================
            result = conversionRate(fromCurrency, toCurrency);
            System.out.println("My Mass Result(converted) = " + result);
            
            // total: Convert N-number USD to N-number EUR - OK
           
            total = convertCurrency(fromCurrency, toCurrency, amount);
            System.out.println("My Mass Total = " + total);
             //==========================
            //return Double.toString(total) + "?faces-redirect=true"; 
            return "rate"; // outcome XHML page
        } catch (Exception ex) {
            stotal = "Service Unavailable at this time";
            total = 0;
            System.out.println("ERROR planetarium Result = unknown");
            System.out.println("ERROR planetarium Total = unknown");
            return "planetarium"; // TO DO Replace with error page
        }
    }
 
    // OUTPUT OK: 	Unable to find matching navigation case with from-view-id 
    // '/planetarium.xhtml' for action '#{convert.converted}' with outcome '1.8076' (amount =2)
    
    // Convert N-number USD to N-number EUR - OK
    private static double convertCurrency(com.corejsf.Currency fromCurrency, com.corejsf.Currency toCurrency, double amount) {
        com.corejsf.CurrencyConvertor service = new com.corejsf.CurrencyConvertor();
        com.corejsf.CurrencyConvertorSoap port = service.getCurrencyConvertorSoap();
        double rate = port.conversionRate(fromCurrency, toCurrency);
        return amount*rate;
    }
     // Convert 1 USD to 1 EUR - OK
    private static double conversionRate(com.corejsf.Currency fromCurrency, com.corejsf.Currency toCurrency) {
        com.corejsf.CurrencyConvertor service = new com.corejsf.CurrencyConvertor();
        com.corejsf.CurrencyConvertorSoap port = service.getCurrencyConvertorSoap();
        return port.conversionRate(fromCurrency, toCurrency);
    }
         // Convert 1 USD to 1 EUR - OK
    public String Rate(com.corejsf.Currency fromCurrency, com.corejsf.Currency toCurrency) {
        com.corejsf.CurrencyConvertor service = new com.corejsf.CurrencyConvertor();
        com.corejsf.CurrencyConvertorSoap port = service.getCurrencyConvertorSoap();
        return Double.toString(port.conversionRate(fromCurrency, toCurrency));
    }
    //=========================================================
    // https://www.youtube.com/watch?v=0EZlo8hForo
    
    public static Connection getMySQLConnection() throws Exception{
        String user = "root";
        String pass = "1470";
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("1. getMySQLConnection OK");
        return (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bank", "root", "1470");
    }
    //=========================================================
    //public static ResultSet getResultSet (String sql){
    public String balance (){
            try {
                Connection conn = getMySQLConnection();
                System.out.println("2. Balance Connection getMySQLConnection OK");
                //===========
                try {
                    //Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("3.  Balance Connection getMySQLConnection OK");
                     
                    Statement stm = (Statement) conn.createStatement(java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.TYPE_FORWARD_ONLY);
                    System.out.println("4.  Balance Connection getMySQLConnection OK");
                    //ResultSet result = stm.executeQuery("Select * from bank.account where account.idaccount = 1001");
                    ResultSet result = stm.executeQuery("Select * from bank.account where account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    while (result.next()){
                        setUSD(result.getDouble("USD"));//this.USD = result.getDouble("USD");
                        setEUR(result.getDouble("EUR")); //this.EUR = result.getDouble("EUR");
                        setJPY(result.getDouble("JPY"));//this.JPY = result.getDouble("JPY");
                        setGBP(result.getDouble("GBP")); // this.GBP = result.getDouble("GBP");
                        setINR(result.getDouble("INR"));//this.INR = result.getDouble("INR");
                        System.out.println ("USD = " + this.USD);
                        System.out.println ("EUR = " + this.EUR);
                        System.out.println ("JPY = " + this.JPY);
                        System.out.println ("GBP = " + this.GBP);
                        System.out.println ("INR = " + this.INR);  
                    }
                    System.out.println("5.Balance Connection getMySQLConnection OK");
                    //return "welcome"; // return PAGE XHTML
                } finally {
                    conn.close(); 
                    System.out.println("6.Balance Connection finally = getResultSet");
                    //return "account";// welcome"; // return PAGE XHTML
                }          
                //===========
            } catch (Exception ex) {
                Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Balance Catch Exception = getResultSet");
                return "login"; //return PAGE XHTML in case of failure
            }
            return "account";// return PAGE XHTML
    }

   //==================================================================== 
     // TO: parameterise buy (double amount, double total)
    // TO DO: move to converted()
    // TO DO: ADD "button-buy" to the PLANETARIUM.XHTML page (keep the button "next")
    //  update the DB from the planetarium page and take to the "success" page
    // TO DO: remove the button-buy from the RATE.XHTML
    // TO DO: add function #{convert.converted2} w/o DB update for the "next" button
    
public String convertedbuy() { 
        try {
            //======= added for forms currencies ======
            //this.setfCurrency(fCurrency);
            //this.settCurrency(tCurrency);
            System.out.println("fCurrency (convertedbuy) = " + fCurrency);
            System.out.println("tCurrency (convertedbuy) = " + tCurrency);
            System.out.println("Amount of Currency (convertedbuy) = " + amount);
            //=========================================

            // rate: Convert 1 USD to 1 EUR - OK
            com.corejsf.Currency fromCurrency = com.corejsf.Currency.valueOf(fCurrency);
            //com.corejsf.Currency fromCurrency = com.corejsf.Currency.USD;
            com.corejsf.Currency toCurrency = com.corejsf.Currency.valueOf(tCurrency);
            //com.corejsf.Currency toCurrency = com.corejsf.Currency.EUR;

            stotal = Rate (fromCurrency, toCurrency);
            System.out.println("My Rate (converted) = " + stotal);

            result = conversionRate(fromCurrency, toCurrency);
            System.out.println("My Mass Result(converted) = " + result);
            
            // total: Convert N-number USD to N-number EUR - OK
           
            total = convertCurrency(fromCurrency, toCurrency, amount);
            System.out.println("My Mass Total = " + total);
             //==========================
            String page = new String (buyme (this.amount, this.total));
            //============================
            //return Double.toString(total) + "?faces-redirect=true"; 
            return page; // outcome XHML page
        } catch (Exception ex) {
            stotal = "Service Unavailable at this time";
            total = 0;
            System.out.println("Result = unknown");
            System.out.println("Total = unknown");
            return "plan"; // TO DO Replace with error page
        }
    }
    public String buyme (double amount, double total){
            
            try {
                Connection conn = getMySQLConnection();
                System.out.println("2.Buy Connection getMySQLConnection OK");
                //===========
                try {
                    System.out.println("3.Buy  Connection getMySQLConnection OK");
                    Statement stm = (Statement) conn.createStatement();//createStatement(java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.TYPE_FORWARD_ONLY);
                    ResultSet result2 = stm.executeQuery("Select * from bank.account where account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    while (result2.next()){
                       setfrom(result2.getDouble(this.getfCurrency())); // TO DO
                       setto(result2.getDouble(this.gettCurrency()));
                    }
                    // TO DO: make this (tempf,tempt)MOVE UP  global for this Class
                    double tempf = (this.getfrom() - amount); // deduct from account balanace
                    double tempt = (this.getto() + total); // add to account balanace
                    System.out.println ("FROM AFTER = " + tempf); // account balanace
                    System.out.println ("TO AFTER = " + tempt); // account balanace
                    System.out.println("FROM AMOUNT = " + amount); // deduct from from
                    System.out.println("TO TOTAL = " + total);
                    
                    
                    //=== Reset all flags to inactive
                    Statement psti = (Statement) conn.prepareStatement("UPDATE bank.account SET bank.account." + fCurrency + " = " + tempf + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    psti.execute("UPDATE bank.account SET bank.account." + fCurrency + " = " + tempf + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    psti.close();
                    //Statement stm = (Statement) conn.createStatement(java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.TYPE_FORWARD_ONLY);
                    //System.out.println("5.Buy  Connection getMySQLConnection OK");
                    //=== set flag to active for the logged in user
                    Statement pstu = (Statement) conn.prepareStatement("UPDATE bank.account SET bank.account." + tCurrency + " = " + tempf + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    //Statement psta = (Statement) conn.prepareStatement("UPDATE bank.customer SET customer.routingNumber = 1 WHERE customer.userName = '" + this.name + "'");
                    pstu.execute("UPDATE bank.account SET bank.account." + tCurrency + " = " + tempt + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    pstu.close();

                    //ResultSet result = stm.executeQuery("Select * from bank.account where account.idaccount = 1001");
                    //while (result.next()){
                        //
                    //}
                    System.out.println("5. Buy Connection getMySQLConnection OK");
                    //return "welcome"; // return PAGE XHTML
                } finally {
                    conn.close(); 
                    System.out.println("6.Buy Connection finally = getResultSet");
                    //return "account";// welcome"; // return PAGE XHTML
                }          
                //===========
            } catch (Exception ex) {
                Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(" Buy Catch Exception = getResultSet");
                return "plan"; //return PAGE XHTML in case of failure
            }
            return "success"; // return PAGE XHTML
    }
    //=======================================================================
       public String buy (){
            
            try {
                Connection conn = getMySQLConnection();
                System.out.println("2.Buy Connection getMySQLConnection OK");
                //===========
                try {
                    System.out.println("3.Buy  Connection getMySQLConnection OK");
                    Statement stm = (Statement) conn.createStatement();//createStatement(java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.TYPE_FORWARD_ONLY);
                    ResultSet result2 = stm.executeQuery("Select * from bank.account where account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    while (result2.next()){
                       setfrom(result2.getDouble(this.getfCurrency())); // TO DO
                       setto(result2.getDouble(this.gettCurrency()));
                    }
                    // TO DO: make this (tempf,tempt)MOVE UP  global for this Class
                    double tempf = (this.getfrom() - this.amount); // deduct from account balanace
                    double tempt = (this.getto() + this.total); // add to account balanace
                    System.out.println ("FROM AFTER = " + tempf); // account balanace
                    System.out.println ("TO AFTER = " + tempt); // account balanace
                    System.out.println("FROM AMOUNT = " + this. amount); // deduct from from
                    System.out.println("TO TOTAL = " + this.total);
                    
                    
                    //=== Reset all flags to inactive
                    Statement psti = (Statement) conn.prepareStatement("UPDATE bank.account SET bank.account." + fCurrency + " = " + tempf + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    psti.execute("UPDATE bank.account SET bank.account." + fCurrency + " = " + tempf + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    psti.close();
                    //Statement stm = (Statement) conn.createStatement(java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.TYPE_FORWARD_ONLY);
                    //System.out.println("5.Buy  Connection getMySQLConnection OK");
                    //=== set flag to active for the logged in user
                    Statement pstu = (Statement) conn.prepareStatement("UPDATE bank.account SET bank.account." + tCurrency + " = " + tempf + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    //Statement psta = (Statement) conn.prepareStatement("UPDATE bank.customer SET customer.routingNumber = 1 WHERE customer.userName = '" + this.name + "'");
                    pstu.execute("UPDATE bank.account SET bank.account." + tCurrency + " = " + tempt + " WHERE account.idaccount = (select bank.customer.accountNumber from bank.customer where bank.customer.routingNumber = 1)");
                    pstu.close();

                    //ResultSet result = stm.executeQuery("Select * from bank.account where account.idaccount = 1001");
                    //while (result.next()){
                        //
                    //}
                    System.out.println("5. Buy Connection getMySQLConnection OK");
                    //return "welcome"; // return PAGE XHTML
                } finally {
                    conn.close(); 
                    System.out.println("6.Buy Connection finally = getResultSet");
                    //return "account";// welcome"; // return PAGE XHTML
                }          
                //===========
            } catch (Exception ex) {
                Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(" Buy Catch Exception = getResultSet");
                return "planetarium"; //return PAGE XHTML in case of failure
            }
            return "success"; // return PAGE XHTML
    }
    //===============================
    
}
