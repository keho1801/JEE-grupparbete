/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartProductKey implements Serializable{
    @Column(name = "product_id")
    private Long product_id;
 
    @Column(name = "cart_id")
    private Long cart_id;

    public CartProductKey() {
    }

    public CartProductKey(Long product_id, Long cart_id) {
        this.product_id = product_id;
        this.cart_id = cart_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.product_id);
        hash = 41 * hash + Objects.hashCode(this.cart_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartProductKey other = (CartProductKey) obj;
        if (!Objects.equals(this.product_id, other.product_id)) {
            return false;
        }
        if (!Objects.equals(this.cart_id, other.cart_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CartProductKey{" + "product_id=" + product_id + ", cart_id=" + cart_id + '}';
    }
    
    
}