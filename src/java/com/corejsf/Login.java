/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corejsf;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import java.util.List;
import javax.faces.bean.SessionScoped;
// or 
import javax.xml.ws.WebServiceRef;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import java.sql.*; //.DriverManager;
//import java.sql.ResultSet;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.ResultSetImpl;
/**
 *
 * @author Natalia Zarubin
 */
@ManagedBean(name="login")
@RequestScoped
public class Login implements Serializable {
    
        // https://www.youtube.com/watch?v=0EZlo8hForo
    private String name = "BillGates";
    private String password = "password";
    public String getname(){ return this.name; }
    public void setname(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String newValue) { this.password = newValue; }
    
    public static Connection getMySQLConnection() throws Exception{
        String user = "root";
        String pass = "1470";
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("1. Login getMySQLConnection OK");
        return (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bank", "root", "1470");
    }
    
    public String useractivate (){
            
            try {
                Connection conn = getMySQLConnection();
                System.out.println("2.UserSetActive Connection getMySQLConnection OK");
                //===========
                try {
                    System.out.println("3.UserSetActive  Connection getMySQLConnection OK");
                    //=== Reset all flags to inactive
                    Statement pst = (Statement) conn.prepareStatement("UPDATE bank.customer SET customer.routingNumber = 234567");
                    pst.execute("UPDATE bank.customer SET customer.routingNumber = 234567");
                    pst.close();
                    //Statement stm = (Statement) conn.createStatement(java.sql.ResultSet.CONCUR_READ_ONLY, java.sql.ResultSet.TYPE_FORWARD_ONLY);
                    System.out.println("4.UserSetActive  Connection getMySQLConnection OK");
                    //=== set flag to active for the logged in user
                    Statement psta = (Statement) conn.prepareStatement("UPDATE bank.customer SET customer.routingNumber = 1 WHERE customer.userName = '" + this.name + "'");
                    psta.execute("UPDATE bank.customer SET customer.routingNumber = 1 WHERE customer.userName = '" + this.name +"'");
                    psta.close();

                    //ResultSet result = stm.executeQuery("Select * from bank.account where account.idaccount = 1001");
                    //while (result.next()){
                        //
                    //}
                    System.out.println("5. UserSetActive Connection getMySQLConnection OK");
                    //return "welcome"; // return PAGE XHTML
                } finally {
                    conn.close(); 
                    System.out.println("6.UserSetActive Connection finally = getResultSet");
                    //return "account";// welcome"; // return PAGE XHTML
                }          
                //===========
            } catch (Exception ex) {
                Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(" UserSetActive Catch Exception = getResultSet");
                return "planetarium"; //return PAGE XHTML in case of failure
            }
            return "welcome"; // return PAGE XHTML
    }   
}
