
package EJB;

import Entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ProductBean {

    @PersistenceContext(unitName = "CarshopND-ejbPU")
    private EntityManager em;

    
    
    public List<Product> getAllProducts() {
        Query query = em.createNamedQuery("Product.getAll", Product.class);
        return query.getResultList(); 
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
}
