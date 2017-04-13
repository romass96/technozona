package ua.com.technozona.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "photos")
public final class Photo extends Model {

    private static final long serialVersionUID = 1L;

    public static final String PATH =  "D:/TechnoZona/images/products/";

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "photoLink")
    private String photoLink;

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "photo"
    )
    @JsonManagedReference
    private Product product;

    public Photo() {
        this("", "");
    }

    public Photo(
            final String title,
            final String photoLink
    ) {
        super();
        this.title = title;
        this.photoLink = photoLink;
    }


    @Override
    public String toString() {
        return "Title: " + this.title
                + "\nphoto link: " + this.photoLink;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : "";
    }

    public String getPhotoLink() {
        return this.photoLink;
    }

    public void setPhotoLink(final String photoLink) {
        this.photoLink = isNotBlank(photoLink) ? photoLink : "";
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
