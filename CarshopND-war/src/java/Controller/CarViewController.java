package Controller;

import EJB.ProductBean;
import Entities.Product;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "carViewController")
@ViewScoped
public class CarViewController implements Serializable {

    @EJB
    private ProductBean productBean;
    @Inject
    private LoginController loginController;
    private List<Product> cars;
    private List<Product> filteredCars;
    private Product selectedProduct;
    private String filterValue;

    @PostConstruct
    public void init() {
        cars = productBean.getAllProducts();
        filteredCars=cars;
    }

    public CarViewController() {

    }
    public Double calcDiscount(Double price){
        if(loginController.getCustomer().isPremium()){
            return price*0.9;
        }else {
            return price;
        }
    }
    

    public void filterList() {
        List<Product> filteredList = cars.stream().filter(e -> e.getName().toLowerCase().contains(filterValue)).collect(Collectors.toList());
        filteredCars = filteredList;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public List<Product> getCars() {
        return cars;
    }

    public void setCars(List<Product> cars) {
        this.cars = cars;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<Product> filteredCars) {
        this.filteredCars = filteredCars;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

}
