package ua.com.technozona.repository;

import ua.com.technozona.model.Category;

/**
 * Репозиторий для объектов класса {@link Category}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainRepository
 * @see Category
 */
public interface CategoryRepository extends MainRepository<Category, Long> {
    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     *
     * @param url URL категории для возврата.
     * @return Объект класса {@link Category} - категория с уникальным url полем.
     */
    Category findByUrl(String url);

    /**
     * Удаляет категрию из базы даных, у которой совпадает поле url.
     *
     * @param url URL категории для удаления.
     */
    void deleteByUrl(String url);
}
