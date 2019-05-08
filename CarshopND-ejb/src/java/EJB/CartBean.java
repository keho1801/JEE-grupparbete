package EJB;

import Entities.Cart;
import Entities.CartProduct;
import Entities.Customer;
import Entities.Product;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CartBean {

    @PersistenceContext(unitName = "CarshopND-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void saveCart(Map<Product, Integer> products, Customer customer) {
        Cart cart = new Cart(new Date(System.currentTimeMillis()), customer);
        customer.getCarts().add(cart);
        products.entrySet().stream().map((entry) -> {
            CartProduct cp = new CartProduct(entry.getKey(), cart, entry.getValue());
            entry.getKey().getCartProduct().add(cp);
            return cp;
        }).forEachOrdered((cp) -> {
            cart.getCartProducts().add(cp);
        });
        em.merge(cart);
        updateCustomerPremiumState(customer);
    }

    public boolean updateCustomerPremiumState(Customer customer) {
        double totalPayment = customer.getCarts()
                .stream()
                .map(Cart::getCartProducts)
                .flatMap(Collection::stream)
                .mapToDouble(e -> e.getProduct().getPrice() * e.getQuantity())
                .sum();

        if (totalPayment > 500000) {
            customer.setPremium(true);
            em.merge(customer);
            return true;
        } else {
            return false;
        }

    }
}
