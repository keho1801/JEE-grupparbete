/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.PopulateDB;
import Entities.Admin;
import Entities.Customer;
import Security.Security;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author powkn
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable{

    @EJB
    private Security security;

    @EJB
    private PopulateDB populateDB;
    
    private String username;
    private String password;
    private Customer customer = null;
    private Admin admin = null;
    

    /**
     * Creates a new instance of Test
     */
    public LoginController() {
        
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public PopulateDB getPopulateDB() {
        return populateDB;
    }

    
    
    
    public void runDB(){
        populateDB.cartAndProducts();
        populateDB.insertAdmins();
        populateDB.insertCustomers();
    }
    
    public void increaseProductsInDb(){
        Customer customer = populateDB.getCustomer();
        populateDB.createCartForCustomer(customer);
       
    }
    
    public String logIn(){
        
        customer = security.checkUserLogin(username, password);
        admin = security.checkAdminLogin(username, password);
        populateDB.addEverythingToDB();
        populateDB.createCartForCustomer(customer);
        
      
        if(customer != null){
            return "cars?faces-redirect=true";
        }
        else if(admin != null){
            return "admin?faces-redirect=true";
        }
        else{
            FacesContext.getCurrentInstance()
                    .addMessage("msgs", new 
        FacesMessage(FacesMessage.SEVERITY_ERROR, "Felaktigt användarnamn eller lösenord", ""));
            return "";
        }
    }

 
}
