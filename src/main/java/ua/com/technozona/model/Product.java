package ua.com.technozona.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "products")
public final class Product extends Model {

    private static final long serialVersionUID = 1L;

    private static final char[] CODE_PATTERN = "1234567890".toCharArray();

    private static final int CODE_LENGTH = 5;

    /**
     * Артикль товара.
     * Значение поля сохраняется в колонке "article".
     * Не может быть null.
     */
    @Column(
            name = "article",
            nullable = false
    )
    private int article;

    /**
     * Название товара.
     * Значение поля сохраняется в колонке "title".
     * Не может быть null.
     */
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

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "photo_id",
            referencedColumnName = "id"
    )
    private Photo photo;

    @Column(
            name = "price",
            nullable = false
    )
    private double price;

    /**
     * Изображение товара.
     * К текущему товару можно добраться через поле "product" в объекте
     * класса {@link SalePosition}. Выборка объекта salePosition при первом
     * доступе к нему. Сущность salePosition автоматически удаляется
     * при удалении текущей.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "product",
            cascade = CascadeType.REMOVE
    )
    private List<SalePosition> salePositions = new ArrayList<>();

    public Product() {
        this("", "", null, null, 0.0);
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
        newArticle();
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

    /**
     * Генерирует строку для конечного сравнения товаров в методе equals()
     * родительского класса. Переопределенный метод родительского
     * класса {@link Model}.
     *
     * @return Значение типа {@link String} - название + URL + цена товара.
     */
    @Override
    public String toEquals() {
        return getArticle() + getTitle() + getUrl() + getPrice();
    }

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

    /**
     * Генерирует новый артикль товара.
     */
    public void newArticle() {
        this.article = Integer.parseInt(
                createRandomString(CODE_PATTERN, CODE_LENGTH)
        );
    }

    /**
     * Возвращает артикль товара.
     *
     * @return Значение типа int - артикль товара.
     */
    public int getArticle() {
        return this.article;
    }

    /**
     * Устанавливает артикль товара.
     *
     * @param article Артикль товара.
     */
    public void setArticle(final int article) {
        this.article = article;
    }

    /**
     * Возвращает название товара.
     *
     * @return Значение типа {@link String} - название товара.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Устанавливает название товара.
     *
     * @param title Название товара.
     */
    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : "";
    }

    /**
     * Возвращает URL товара.
     *
     * @return Значение типа {@link String} - URL товара.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Устанавливает URL товара.
     *
     * @param url URL товара.
     */
    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : "";
    }

    /**
     * Возвращает параметры товара.
     *
     * @return Значение типа {@link String} - параметры товара.
     */
    public String getParameters() {
        return this.parameters;
    }

    /**
     * Устанавливает параметры товара.
     *
     * @param parameters Параметры товара.
     */
    public void setParameters(final String parameters) {
        this.parameters = isNotBlank(parameters) ? parameters : "";
    }

    /**
     * Возвращает описание товара.
     *
     * @return Значение типа {@link String} - описание товара.
     */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : "";
    }

    public Photo getPhoto() {
        return this.photo;
    }

    public void setPhoto(final Photo photo) {
        this.photo = photo;
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

    /**
     * Возвращает список торговых позиций, для которых пренадлежит текущий товара.
     *
     * @return Объект класса {@link SalePosition} - торговая позиция.
     */
    public List<SalePosition> getSalePositions() {
        return this.salePositions;
    }

    /**
     * Устанавливает список торговых позиций, для которых пренадлежит текущий товара.
     *
     * @param salePositions Торговая позиция.
     */
    public void setSalePositions(final List<SalePosition> salePositions) {
        this.salePositions = salePositions;
    }
}