
package Controller;

import EJB.PopulateDB;
import Security.Security;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@Named(value = "registerController")
@ViewScoped
public class RegisterController implements Serializable {

    @EJB
    private PopulateDB populateDB;

    @EJB
    private Security security;
    private String name = null;
    private String username = null;
    private String password = null;
    private String address = null;
    private FacesMessage facesMessage;

    public PopulateDB getPopulateDB() {
        return populateDB;
    }

    public void setPopulateDB(PopulateDB populateDB) {
        this.populateDB = populateDB;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public FacesMessage getFacesMessage() {
        return facesMessage;
    }

    public void setFacesMessage(FacesMessage facesMessage) {
        this.facesMessage = facesMessage;
    }
    
    
    
    public RegisterController() {
    }
    
    public String register(){
        
        if(!checkRegisterForm()){
            return "";
        }
        else if(!checkIfUserExists()){
            return "";
        }
        
        return "index";
    }
    
    
    public boolean checkRegisterForm(){
        
        boolean returnBool = true;
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage("Fyll i fältet ovan");
       
        if(name.equalsIgnoreCase("")){
            facesContext.addMessage("login-form:jText1", facesMessage);
            returnBool = false;
        }
        if(username.equalsIgnoreCase("")){
            facesContext.addMessage("login-form:jText2", facesMessage);
            returnBool = false;
        }
        if(password.equalsIgnoreCase("")){
            facesContext.addMessage("login-form:jText3", facesMessage);
            returnBool = false;
        }
        if(address.equalsIgnoreCase("")){
            facesContext.addMessage("login-form:jText4", facesMessage);
            returnBool = false;
        }
        
        return returnBool;
   
    }
    
    public boolean checkIfUserExists(){
        
        boolean check = populateDB.addUser(name, username, password, address);

        if(!check){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            setFacesMessage(new FacesMessage("Username already exists"));
            facesContext.addMessage("login-form:gText2", facesMessage);
            return false;
        }
        
        return true;
    }
    
}
