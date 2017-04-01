package ua.com.technozona.exception;

/**
 * Исключение генерируется, если данные не найдены в базе данных.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 */
public final class BadRequestException extends RuntimeException {
    /**
     * Конструктр без параметров.
     */
    public BadRequestException() {
        super();
    }

    /**
     * Конструкто с параметром.
     *
     * @param message Сообщение исключения.
     */
    public BadRequestException(final String message) {
        super(message);
    }
}
