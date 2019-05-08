
package Controller;

import EJB.CartBean;
import Entities.Product;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named(value = "cartController")
@SessionScoped
public class CartController implements Serializable {

    @Inject
    private LoginController loginController;
    @EJB
    private CartBean cartBean;
    private Map<Product, Integer> products = new HashMap<>();

    public CartController() {
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void addProductToCart(Product product) {
      
        products.merge(product, 1, (a,b)-> a+b);
    }
    public void removeProduct(Product product) {
        if (products.get(product)==1) {
            products.remove(product);
        }else{
            products.put(product, products.get(product)-1);
        }
    }

    public void saveCart() {
        cartBean.saveCart(products, loginController.getCustomer());
        products.clear();
    }

}
