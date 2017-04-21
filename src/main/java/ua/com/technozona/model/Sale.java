package ua.com.technozona.model;

import javax.persistence.*;


@Entity
@Table(name = "sales")
public final class Sale extends Model {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Product product;

    @Column(
            name = "number",
            nullable = false
    )
    private int number;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Order order;

    public Sale() {
        this(null, 0);
    }

    public Sale(
            final Product product,
            final int number
    ) {
        super();
        this.product = product;
        this.number = number;
    }

    @Override
    public String toString() {
        return "SalePosition #" + getId()
                + ":\n" + this.product.getTitle()
                + "\n№ " + this.product.getId()
                + ", " + this.product.getPrice() + " UAH"
                + "\nNumber = " + this.number
                + "\nPrice = " + getPrice();
    }

    @Override
    public String toEquals() {
        String line = this.product.toEquals();
        if (getId() != null) {
            line += getId();
        }
        return line;
    }

    public double getPrice() {
        return this.number * this.product.getPrice();
    }

    public void numberIncrement() {
        this.number++;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(final Product product) {
        this.product = product;
        this.number = product != null ? 1 : 0;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(final int number) {
        this.number = number > 0 ? number : 0;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }
}
