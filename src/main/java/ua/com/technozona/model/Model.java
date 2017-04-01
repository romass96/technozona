package ua.com.technozona.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@MappedSuperclass
public abstract class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final char[] CODE_PATTERN =
            "ALEXCOFFEE1234567890".toCharArray();

    private static final int CODE_LENGTH = 6;

    private static final String DATE_PATTERN =
            "EEE, d MMM yyyy, HH:mm:ss";

    private static final String TIME_ZONE = "GMT+3";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Model)) {
            return false;
        }
        final Model other = (Model) obj;
        return (this.toEquals().equals(other.toEquals()));
    }

    @Override
    public int hashCode() {
        return this.id != null ? this.id.hashCode() : toString().hashCode();
    }

    /**
     * Генерирует строку для конечного сравнения объектов в методе equals().
     * Что бы в дочернем классе не переопределять весь метод equals(),
     * можно переопределить тьлько этот метод.
     *
     * @return Значение типа {@link String} -
     * результат работы метода toString().
     */
    public String toEquals() {
        return toString();
    }

    /**
     * Возвращает рандомную строку из набор символов и длинны по-умолчанию.
     *
     * @return Значение типа {@link String} - рандомная строка из набора
     * символов CODE_PATTERN длиной {@value CODE_LENGTH}.
     */
    static String createRandomString() {
        return createRandomString(CODE_PATTERN, CODE_LENGTH);
    }

    /**
     * Возвращает рандомную строку используя набор символов pattern длиной length.
     *
     * @param pattern Набор вожможных для использованния символов.
     * @param length  Длина возвращаемой строки.
     * @return Значение типа {@link String} - рандомная строка из набора символов
     * pattern длиной length.
     */
    static String createRandomString(
            final char[] pattern,
            final int length
    ) {
        final StringBuilder sb = new StringBuilder();
        final Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(pattern[random.nextInt(pattern.length)]);
        }
        return sb.toString();
    }

    /**
     * Конвертирует дату типа Date в строку используя для работы входящими параметрами
     * формат даты {@value DATE_PATTERN} и часовой пояс (@value TIME_ZONE} по-умолчанию.
     *
     * @param date Значение даты типа Date для обработки.
     * @return Значение типа {@link String} - дата в виде строки.
     */
    static String dateToString(final Date date) {
        return dateToStringWithFormat(date,
                new SimpleDateFormat(DATE_PATTERN),
                TimeZone.getTimeZone(TIME_ZONE)
        );
    }

    private static String dateToStringWithFormat(
            final Date date,
            final DateFormat dateFormat,
            final TimeZone timeZone
    ) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public static <T extends Model> List<T> getUnmodifiableList(
            final List<T> list
    ) {
        return ((list != null) && !list.isEmpty()) ?
                Collections.unmodifiableList(list) :
                Collections.EMPTY_LIST;
    }
}
