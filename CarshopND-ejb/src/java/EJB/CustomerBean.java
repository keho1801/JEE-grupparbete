package EJB;

import Entities.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class CustomerBean {

    @PersistenceContext(unitName = "CarshopND-ejbPU")
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }

    public List<Customer> getAllCustomers() {
        Query query = em.createNamedQuery("Customer.getAll", Customer.class);
        return query.getResultList(); 
    }
}
