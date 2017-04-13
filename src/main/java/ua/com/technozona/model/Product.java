package ua.com.technozona.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


@Entity
@Table(name = "products")
public final class Product extends Model {

    private static final long serialVersionUID = 1L;

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "url",
            nullable = false
    )
    private String url;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    @Column(
            name = "price",
            nullable = false
    )
    private double price;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "photo_id",
            referencedColumnName = "id"
    )
    @JsonBackReference
    private Photo photo;



    public Product() {
        this("", "", null,null, 0.0);
    }

    public Product(
            final String title,
            final String url,
            final Category category,
            final Photo photo,
            final double price
    ) {
        super();
        this.title = title;
        this.url = url;
        this.category = category;
        this.photo = photo;
        this.price = price;
        this.parameters = "";
        this.description = "";
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(this.title)
                .append("\nParameters: ")
                .append(this.parameters)
                .append("\nDescription: ")
                .append(this.description)
                .append("\nPrice = ")
                .append(this.price)
                .append(" UAH");
        if (this.category != null) {
            sb.append("\nCategory: ")
                    .append(this.category.getTitle());
        }
        return sb.toString();
    }

    @Override
    public String toEquals(){return getTitle() + getUrl() + getPrice();}


    public void initialize(
            final String title,
            final String url,
            final String parameters,
            final String description,
            final Category category,
            final Photo photo,
            final double price
    ) {
        setTitle(title);
        setUrl(url);
        setParameters(parameters);
        setDescription(description);
        setCategory(category);
        setPhoto(photo);
        setPrice(price);
    }

    public void initialize(
            final String title,
            final String url,
            final String parameters,
            final String description,
            final Category category,
            final double price
    ) {
        setTitle(title);
        setUrl(url);
        setParameters(parameters);
        setDescription(description);
        setCategory(category);
        setPrice(price);
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

    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(final String parameters) {
        this.parameters = isNotBlank(parameters) ? parameters : "";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : "";
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double price) {
        this.price = price > 0 ? price : 0;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}