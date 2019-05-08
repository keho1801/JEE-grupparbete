/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Entities.Admin;
import Entities.Cart;
import Entities.CartProduct;
import Entities.Customer;
import Entities.Product;
import Security.Security;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
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
public class PopulateDB {

    @EJB
    private Security security;

    @PersistenceContext(unitName = "CarshopND-ejbPU")
    private EntityManager em;

    public void cartAndProducts() {

        
        Cart cart = new Cart(new Date(System.currentTimeMillis()));
                
        Product product1 = new Product("Touareg",
                "Kraftfull med längre motorhuv, sidolinjer med skarpa konturer "
                        + "och slående detaljer som till exempel bakljusen. "
                        + "Touareg signalerar att här är bilen som tar sig "
                        + "fram på alla vägar med elegans.", 100000.0, 
                "img1.png");
        
        CartProduct cp1=new CartProduct(product1, cart, 1);
        product1.getCartProduct().add(cp1);
        cart.getCartProducts().add(cp1);
        
        Product product2 = new Product("Passat Sportscombi", "Passat Sportscombi är en helt"
                + " ny bil med toppmoderna innovationer, ypperlig komfort och "
                + "överraskande mångsidighet. Passat Sportscombi är skapad "
                + "för dig som gillar att sitta bakom ratten, som uppskattar "
                + "komfort och körglädje.", 200000.0, 
            "img2.png");
        
        CartProduct cp2=new CartProduct(product2, cart, 12);
        product1.getCartProduct().add(cp2);
        cart.getCartProducts().add(cp2);
        
        Product product3 = new Product("Tiguan Allspace.", "Oavsett om du "
                + "måste ringa ett samtal när du kör, behöver den absolut "
                + "senaste trafikinformationen, vill veta den snabbaste vägen "
                + "till en adress eller helt enkelt lyssna på din favoritmusik "
                + "eller favoritpodcast står din Tiguan Allspace till tjänst.", 400000.0,
            "img3.png");
        
        CartProduct cp3=new CartProduct(product3, cart, 2);
        product1.getCartProduct().add(cp3);
        cart.getCartProducts().add(cp3);
        
        Product product4 = new Product("Passat Alltrack", "Den är riktigt läcker"
                + ", utstrålar aktiv fritid och visar tydligt sina tillgångar. "
                + "Med effektiva motorer, 4MOTION fyrhjulsdrift, DSG "
                + "automatväxellåda med dubbelkoppling samt högre markfrigång.", 800000.0, 
            "img4.png");
        CartProduct cp4=new CartProduct(product4, cart, 1);
        product1.getCartProduct().add(cp4);
        cart.getCartProducts().add(cp4);
        
        Product product5 = new Product("Passat Sportscombi", "Passat Sportscombi"
                + " är en helt ny bil med toppmoderna innovationer, ypperlig "
                + "komfort och överraskande mångsidighet. Passat Sportscombi "
                + "är skapad för dig som gillar att sitta bakom ratten.", 800000.0, 
            "img5.png");
        CartProduct cp5=new CartProduct(product5, cart, 6);
        product1.getCartProduct().add(cp5);
        cart.getCartProducts().add(cp5);
        
        Product product6 = new Product("Tiguan Allspace", "I Tiguan Allspace kommer du "
                + "att känna dig som hemma i många olika världar. Från berg "
                + "till kuster till skogar till städer får äventyren ta plats, "
                + "om du vill i sällskap med ytterligare sex passagerare.", 
                800000.0, "img6.png");
        CartProduct cp6=new CartProduct(product6, cart, 2);
        product1.getCartProduct().add(cp6);
        cart.getCartProducts().add(cp6);
        
        Customer customer4 = new Customer("Greger Ninjasson", "greger.ninjasson", "12345", "Hittavägen 10", false);
        customer4.getCarts().add(cart);
        cart.setCustomer(customer4);

      
        em.persist(cart);
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void insertAdmins() {
        Admin admin1 = new Admin("Kalle Johansson", "kalle.johansson", new Security().createHash("12345"));
        Admin admin2 = new Admin("Steven Filipsson", "steven.filipsson", new Security().createHash("12345"));
        Admin admin3 = new Admin("Sofia Henriksson", "sofia.henriksson", new Security().createHash("12345"));
        em.persist(admin1);
        em.persist(admin2);
        em.persist(admin3);
    }

    public boolean addUser(String name, String userName, String password, String address) {
        boolean returnBool = false;
        Customer customer = new Customer(name, userName, password, address, false);
        Query query = em.createNamedQuery("Customer.checkCustomer");
        query.setParameter("username", userName);
        try{
            query.getSingleResult();
        }catch(NoResultException e){
            em.persist(customer);
            returnBool = true;
        }
        
        return returnBool;
    }

    public void insertCustomers() {
        Customer customer1 = new Customer("Jörgen johansson", "jorgen.johansson", "12345", "Rankhusvägen 42", false);
        Customer customer2 = new Customer("David Södergren", "david.sodergren", "12345", "Femstenavägen 7", false);
        Customer customer3 = new Customer("Sofia Svensson", "sofia.svensson", "12345", "Smålandsväg 30", true);
        
        em.persist(customer1);
        em.persist(customer2);
        em.persist(customer3);
    }
    
    
        
        
    

    public void addCartForGreger() {
        
        Query query = em.createNamedQuery("Product.getAll", Product.class);
        Query query1 = em.createNamedQuery("Customer.checkCustomer", Customer.class);
        query1.setParameter("username", "greger.ninja");
        Customer customer =(Customer) query1.getSingleResult();
        Cart cart = new Cart(new Date(System.currentTimeMillis()), customer);
        customer.getCarts().add(cart);

        
    }
    
    
    
    
    public Customer getCustomer(){
        Customer customer = security.checkUserLogin("greger.ninjasson", "12345");
        return customer;
    }

    public void createCartForCustomer(Customer customer) {
        
        if(customer != null && customer.getName().equals("fylldb")){
            Query query = em.createNamedQuery("Product.getAll", Product.class);      

            Cart cart = new Cart(new Date(System.currentTimeMillis()));
            List<Product> products = query.getResultList();


            products.forEach(e ->{
                CartProduct cp = new CartProduct(e, cart, 1);
                e.getCartProduct().add(cp);
                cart.getCartProducts().add(cp);
            });

            customer.getCarts().add(cart); 
            cart.setCustomer(customer);

            System.out.println(customer.getAddress());


            em.persist(cart);
        }
    }

    public void testQuery() {
        
        Query query = em.createNamedQuery("Cart.getCarts", Cart.class);
        query.setParameter("customerId", 2);
        List<Cart> carts = query.getResultList();
        List<CartProduct> cartProducts = carts.get(0).getCartProducts();
        cartProducts.forEach(e -> {
            System.out.println(e.getProduct());
        });
        
        
    }

    public List<Cart> getCartForCustomer(Customer customer) {
        Query query = em.createNamedQuery("Cart.getCarts", Cart.class);
        query.setParameter("customerId", customer.getId());
        List<Cart> carts = query.getResultList();
        return carts;
    }

    public void insertProducts() {
                Product product1 = new Product("Touareg",
                "Kraftfull med längre motorhuv, sidolinjer med skarpa konturer "
                        + "och slående detaljer som till exempel bakljusen. "
                        + "Touareg signalerar att här är bilen som tar sig "
                        + "fram på alla vägar med elegans.", 100000.0, 
                "img1.png");

        Product product2 = new Product("Passat Sportscombi", "Passat Sportscombi är en helt"
                + " ny bil med toppmoderna innovationer, ypperlig komfort och "
                + "överraskande mångsidighet. Passat Sportscombi är skapad "
                + "för dig som gillar att sitta bakom ratten, som uppskattar "
                + "komfort och körglädje.", 20000.0, 
            "img2.png");
        
        Product product3 = new Product("Tiguan Allspace.", "Oavsett om du "
                + "måste ringa ett samtal när du kör, behöver den absolut "
                + "senaste trafikinformationen, vill veta den snabbaste vägen "
                + "till en adress eller helt enkelt lyssna på din favoritmusik "
                + "eller favoritpodcast står din Tiguan Allspace till tjänst.", 400000.0,
            "img3.png");

        Product product4 = new Product("Passat Alltrack", "Den är riktigt läcker"
                + ", utstrålar aktiv fritid och visar tydligt sina tillgångar. "
                + "Med effektiva motorer, 4MOTION fyrhjulsdrift, DSG "
                + "automatväxellåda med dubbelkoppling samt högre markfrigång.", 800000.0, 
            "img4.png");

        Product product5 = new Product("Passat Sportscombi", "Passat Sportscombi"
                + " är en helt ny bil med toppmoderna innovationer, ypperlig "
                + "komfort och överraskande mångsidighet. Passat Sportscombi "
                + "är skapad för dig som gillar att sitta bakom ratten.", 800000.0, 
            "img5.png");

        Product product6 = new Product("Tiguan Allspace", "I Tiguan Allspace kommer du "
                + "att känna dig som hemma i många olika världar. Från berg "
                + "till kuster till skogar till städer får äventyren ta plats, "
                + "om du vill i sällskap med ytterligare sex passagerare.", 
                800000.0, "img6.png");
        
        persist(product1);
        persist(product2);
        persist(product3);
        persist(product4);
        persist(product5);
        persist(product6);
        
    }
    
    
    

    public void addEverythingToDB() {
        Query query = em.createNamedQuery("Product.getAll", Product.class);
        Query query1 = em.createNamedQuery("Admin.getAll", Admin.class);
        Query query2 = em.createNamedQuery("Customer.getAll", Customer.class);
            
        List<Object> testList = query.getResultList();
        if(testList.isEmpty()){
            insertProducts();
        }
        
        List<Object> testList1 = query1.getResultList();
        if(testList1.isEmpty()){
            insertAdmins();
        }
        
        List<Object> testList2 = query2.getResultList();
        if(testList2.isEmpty()){
            insertCustomers();
        }            
        
    }

}
