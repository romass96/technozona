package ua.com.technozona.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Sale> sales = new ArrayList<>();

    public ShoppingCart() {
    }

    public ShoppingCart(
            final List<Sale> sales
    ) {
        this();
        this.sales = sales;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shopping Cart: ");
        if (this.sales != null && !this.sales.isEmpty()) {
            int count = 1;
            for (Sale sale : this.sales) {
                sb.append("\n")
                        .append(count++)
                        .append(") ").append(sale.getProduct().getTitle())
                        .append("\n№ ")
                        .append(sale.getProduct().getId())
                        .append(", ")
                        .append(sale.getPrice())
                        .append(" UAH");
            }
            sb.append("\nPrice: ")
                    .append(getPrice())
                    .append(" UAH");
        } else {
            sb.append("is empty!");
        }
        return sb.toString();
    }

    public void addSale(final Sale sale) {
        if (sale != null) {
            if (!this.sales.contains(sale)) {
                this.sales.add(sale);
            } else {
                this.sales.get(
                        this.sales.indexOf(sale)
                ).numberIncrement();
            }
        }
    }

    public void addSale(
            final List<Sale> sales
    ) {
        sales.forEach(this::addSale);
    }

    public void removeSale(final Sale sale) {
        this.sales.remove(sale);
    }

    public void removeSales(
            final List<Sale> sales
    ) {
        this.sales.removeAll(sales);
    }

    public void clearSales() {
        this.sales.clear();
    }

    public List<Sale> getSales() {
        return (this.sales == null) || (this.sales.isEmpty())
                ? Collections.EMPTY_LIST
                : Collections.unmodifiableList(this.sales);
    }

    public void setSales(final List<Sale> sales) {
        this.sales = sales;
    }

    public double getPrice() {
        double sum = 0;
        for (Sale sale : this.sales) {
            sum += sale.getPrice();
        }
        return sum;
    }

    public int getSize() {
        int size = 0;
        for (Sale sale : this.sales) {
            size += sale.getNumber();
        }
        return size;
    }
}
