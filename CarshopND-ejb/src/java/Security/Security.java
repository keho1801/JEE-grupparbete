/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import Entities.Admin;
import Entities.Customer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author powkn
 */
@Stateless
@LocalBean
public class Security {

    @PersistenceContext(unitName = "CarshopND-ejbPU")
    private EntityManager em;
    
    

    public String createHash(String passwordToHash) {
        String generatedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Customer checkUserLogin(String username, String pass) {
        Customer customer = null;

            
        Query query = em.createNamedQuery("Customer.getCustomer");
        query.setParameter("username", username);
        query.setParameter("password", createHash(pass));
        try{
        customer = (Customer) query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
        System.out.println(customer);
        return customer;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public Admin checkAdminLogin(String username, String pass) {
        Admin returnAdmin = null;
        
        Query query = em.createNamedQuery("Admin.getAdmin");
        query.setParameter("username", username);
        query.setParameter("password", createHash(pass));            
        try{
        returnAdmin = (Admin) query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
        
        return returnAdmin;
    }

    public void compileTestMethod() {
        System.out.println("");
    }
    
    
    
    
    
    
    
    
}
