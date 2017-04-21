package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.model.Sale;
import ua.com.technozona.model.ShoppingCart;
import ua.com.technozona.service.interfaces.ShoppingCartService;


import java.util.List;

@Service
public final class ShoppingCartServiceImpl
        implements ShoppingCartService {


    private  ShoppingCart shoppingCart;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ShoppingCartServiceImpl(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    @Override
    @Transactional
    public void add(final Sale sale) {
        if (sale != null) {
            this.shoppingCart.addSale(sale);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sale> getSales() {
        return this.shoppingCart.getSales();
    }

    @Override
    @Transactional
    public void remove(final Sale sale) {
        if (sale != null) {
            this.shoppingCart.removeSale(sale);
        }
    }

    @Override
    @Transactional
    public void clear() {
        this.shoppingCart.clearSales();
    }

    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return this.shoppingCart.getPrice();
    }

    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return this.shoppingCart.getSize();
    }
}
