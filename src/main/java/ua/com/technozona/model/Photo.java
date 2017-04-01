package ua.com.technozona.model;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


@Entity
@Table(name = "photos")
public final class Photo extends Model {

    private static final long serialVersionUID = 1L;


    public static final String PATH = System.getenv("CATALINA_HOME") +
            "/webapps/ROOT/resources/img/";


    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Строка-ссылка на малое изображения.
     * Значение поля сохраняется в колонке "photo_link_short".
     */
    @Column(name = "photo_link_short")
    private String photoLinkShort;

    /**
     * Строка-ссылка на большое изображения.
     * Значение поля сохраняется в колонке "photo_link_long".
     */
    @Column(name = "photo_link_long")
    private String photoLinkLong;

    /**
     * Товар, к которому относится данное изображение.
     * К даному объекту можно добраться
     * через поле "photo" в объекте класса {@link Product}.
     * Выборка объекта product при первом доступе к нему.
     */
    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "photo"
    )
    private Product product;


    /**
     * Конструктр без параметров.
     */
    public Photo() {
        this("", "", "");
    }

    /**
     * Конструктор для инициализации основных переменных изображения.
     *
     * @param title          Название изображения.
     * @param photoLinkShort Строка-ссылка на малое изображения.
     * @param photoLinkLong  Строка-ссылка на большое изображения.
     */
    public Photo(
            final String title,
            final String photoLinkShort,
            final String photoLinkLong
    ) {
        super();
        this.title = title;
        this.photoLinkShort = photoLinkShort;
        this.photoLinkLong = photoLinkLong;
    }

    /**
     * Конструктор для инициализации переменных изображения.
     *
     * @param title          Название изображения.
     * @param photoLinkShort Строка-ссылка на малое изображения.
     */
    public Photo(
            final String title,
            final String photoLinkShort
    ) {
        this(title, photoLinkShort, "");
    }

    /**
     * Возвращает описание изображения.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа {@link String} -
     * строка описание изображения (название, URL,
     * строки-ссылки на малое и большое изображения).
     */
    @Override
    public String toString() {
        return "Title: " + this.title
                + "\nphoto short link: " + this.photoLinkShort
                + "\nphoto long link: " + this.photoLinkLong;
    }

    /**
     * Инициализация полей изображения.
     *
     * @param title          Название изображения.
     * @param photoLinkShort Строка-ссылка на малое изображения.
     * @param photoLinkLong  Строка-ссылка на большое изображения.
     */
    public void initialize(
            final String title,
            final String photoLinkShort,
            final String photoLinkLong
    ) {
        setTitle(title);
        setPhotoLinkShort(photoLinkShort);
        setPhotoLinkLong(photoLinkLong);
    }

    /**
     * Возвращает название изображения.
     *
     * @return Значение типа {@link String} - название изображения.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Устанавливает название изображения.
     *
     * @param title Название изображения.
     */
    public void setTitle(final String title) {
        this.title = isNotBlank(title) ? title : "";
    }

    /**
     * Возвращает строку-ссылка на малое изображения.
     *
     * @return Значение типа {@link String} - строка-ссылка на малое изображения.
     */
    public String getPhotoLinkShort() {
        return this.photoLinkShort;
    }

    /**
     * Устанавливает строку-ссылка на малое изображения.
     *
     * @param photoLinkShort Строка-ссылка на малое изображения.
     */
    public void setPhotoLinkShort(final String photoLinkShort) {
        this.photoLinkShort = isNotBlank(photoLinkShort) ? photoLinkShort : "";
    }

    /**
     * Возвращает строку-ссылка на большое изображения.
     *
     * @return Значение типа {@link String} - строка-ссылка на большое изображения.
     */
    public String getPhotoLinkLong() {
        return this.photoLinkLong;
    }

    /**
     * Устанавливает строку-ссылка на большое изображения.
     *
     * @param photoLinkLong Строка-ссылка на большое изображения.
     */
    public void setPhotoLinkLong(final String photoLinkLong) {
        this.photoLinkLong = isNotBlank(photoLinkLong) ? photoLinkLong : "";
    }

    /**
     * Возвращает товар, к которому относится данное изображение.
     *
     * @return Объект класса {@link Photo} - товар, к которому относится
     * данное изображение.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Устанавлевает товар, к которому будет относиться данное изображение.
     *
     * @param product Товар, к которому будет относиться данное изображение.
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

}
