package ua.com.technozona.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "categories")
public final class Category extends Model {

    private static final long serialVersionUID = 1L;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "description")
    private String description;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private List<Product> products = new ArrayList<>();

    public Category() {
        this("", "", "");
    }

    public Category(
            final String title,
            final String url,
            final String description
    ) {
        super();
        this.title = title;
        this.url = url;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Title: " + this.title
                + "\nUrl: " + this.url
                + "\nDescription: " + this.description;
    }

    @Override
    public String toEquals() {
        if (isBlank(this.title) || isBlank(this.url)) {
            return super.toString();
        } else {
            return getTitle() + getUrl();
        }
    }

    public void initialize(
            final String title,
            final String url,
            final String description
    ) {
        setTitle(title);
        setUrl(url);
        setDescription(description);
    }

    public void addProduct(final Product product) {
        this.products.add(product);
    }

    public void addProducts(final List<Product> products) {
        this.products.addAll(products);
    }

    public void removeProduct(final Product product) {
        this.products.remove(product);
    }

    public void removeProducts(final List<Product> products) {
        this.products.removeAll(products);
    }

    public void clearProducts() {
        this.products.clear();
    }

    public List<Product> getProducts() {
        return getUnmodifiableList(this.products);
    }

    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : "";
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : "";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : "";
    }

}
