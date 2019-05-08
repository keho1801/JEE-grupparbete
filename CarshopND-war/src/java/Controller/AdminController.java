
package Controller;

import EJB.PopulateDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import Entities.Cart;
import Entities.CartProduct;
import Entities.Customer;

@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    @EJB
    private PopulateDB populateDB;
    private List<Cart> carts;
    private List<Cart> selectedCarts;
    
    public AdminController() {
    }

    public PopulateDB getPopulateDB() {
        return populateDB;
    }

    public void setPopulateDB(PopulateDB populateDB) {
        this.populateDB = populateDB;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getSelectedCarts() {
        return selectedCarts;
    }

    public void setSelectedCarts(List<Cart> selectedCarts) {
        this.selectedCarts = selectedCarts;
    }
    
    public List<CartProduct> getCartProducts(){
        return this.selectedCarts.get(0).getCartProducts();
    }
    
    public List<Cart> getAllCartsForCustomer(Customer customer){
        List <Cart> cartsForCustomer = populateDB.getCartForCustomer(customer);
        return cartsForCustomer;
    }
    
    public List<CartProduct> getCartProductsForCart(Cart cart){
        return cart.getCartProducts();
    }
    
    public Double getTotalAmount(Cart cart){
        double totalBuy = cart.getCartProducts().stream()
                .map(e -> e.getProduct().getPrice()*e.getQuantity())
                .reduce(.0, (x, y) -> x + y);
        return totalBuy;
    }
    
}
