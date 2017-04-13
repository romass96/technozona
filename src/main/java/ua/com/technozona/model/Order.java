package ua.com.technozona.model;

import ua.com.technozona.enums.StatusEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "orders")
public final class Order extends Model {

    private static final long serialVersionUID = 1L;


    @Column(
            name = "date",
            nullable = false
    )
    private String date;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_details")
    private String shippingDetails;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id"
    )
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "manager_id",
            referencedColumnName = "id"
    )
    private Employee manager;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private List<Sale> sales = new ArrayList<>();

    public Order() {
        super();
        this.shippingAddress = "";
        this.shippingDetails = "";
        this.description = "";
        this.date = dateToString(new Date());
    }

    public Order(
            final Client client,
            final List<Sale> sales
    ) {
        this();
        this.client = client;
        addSales(sales);
    }

    @Override
    public String toString() {
       return null;
    }

    @Override
    public String toEquals() {
        return String.valueOf(getId());
    }


    public void addSale(final Sale sale) {
        this.sales.add(sale);
        if (sale.getOrder() != this) {
            sale.setOrder(this);
        }
    }

    public void addSales(final List<Sale> sales) {
        this.sales.addAll(sales);
        sales.stream()
                .filter(salePosition -> salePosition.getOrder() != this)
                .forEach(salePosition -> salePosition.setOrder(this));
    }

    public void removeSale(final Sale sale) {
        this.sales.remove(sale);
    }

    public void removeSales(final List<Sale> sales) {
        this.sales.removeAll(sales);
    }

    public void clearSales() {
        this.sales.clear();
    }

    public List<Sale> getSales() {
        return getUnmodifiableList(this.sales);
    }

    public void setSales(final List<Sale> sales) {
        this.sales = sales;
        this.sales.stream()
                .filter(salePosition -> salePosition.getOrder() != this)
                .forEach(salePosition -> salePosition.setOrder(this));
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date != null ? dateToString(date) : "";
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(final StatusEnum status) {
        this.status = status;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(final Employee manager) {
        this.manager = manager;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(final String shippingAddress) {
        this.shippingAddress = isNotBlank(shippingAddress) ? shippingAddress : "";
    }

    public String getShippingDetails() {
        return this.shippingDetails;
    }

    public void setShippingDetails(final String shippingDetails) {
        this.shippingDetails = isNotBlank(shippingDetails) ? shippingDetails : "";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : "";
    }

    public double getPrice() {
        double price = 0;
        for (Sale sale : this.sales) {
            price += sale.getPrice();
        }
        return price;
    }
}
