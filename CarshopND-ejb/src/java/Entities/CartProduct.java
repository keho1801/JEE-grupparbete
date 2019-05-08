/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import static java.lang.reflect.Modifier.FINAL;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({
@NamedQuery(name="CartProduct.getAll", query = "select o from Cart o"),
@NamedQuery(name="CartProduct.getCartProductForCart", query = "select o from CartProduct o "
        + "inner join o.cart c where c.id=:cartId")
})
@Entity
public class CartProduct implements Serializable {

    @EmbeddedId
    private CartProductKey id;

    @ManyToOne(cascade = PERSIST)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = PERSIST)
    @MapsId("cart_id")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private int quantity;
    private final int SUMMARYINT = 1;

    public CartProduct() {
    }

    public CartProduct(Product product, Cart cart, int quantity) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }

    public CartProductKey getId() {
        return id;
    }

    public void setId(CartProductKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSUMMARYINT() {
        return SUMMARYINT;
    }
    
    
    
    
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartProduct)) {
            return false;
        }
        CartProduct other = (CartProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.CartProduct[ id=" + id + " ]";
    }

}
