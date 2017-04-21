package ua.com.technozona.service.interfaces;



import ua.com.technozona.model.Sale;
import ua.com.technozona.model.ShoppingCart;

import java.util.List;


public interface ShoppingCartService {

    ShoppingCart getShoppingCart();

    void add(Sale sale);

    List<Sale> getSales();

    void remove(Sale sale);

    void clear();

    double getPrice();

    int getSize();
}
