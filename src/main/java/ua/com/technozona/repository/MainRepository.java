package ua.com.technozona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.technozona.model.Model;

/**
 * Репозиторий для объектов классов наследников класса {@link Model}.
 * Наследует интерфейс {@link JpaRepository} - это интерфейс
 * фреймворка Spring Data предоставляющий набор стандартных методов JPA
 * для работы с БД. Первый Generic T должен быть тип (класс) объекта нашей
 * сущности для которой мы создали Repository, это указывает на то, что
 * Spring Data должен предоставить реализацию методов для работы с этой
 * сущностью (обязательно). Второй Generic E должен быть оберточным типом
 * того типа которым есть id нашей сущности (обязательно).
 *
 * @param <T> Тип (класс) сущности.
 * @param <E> Тип id сущности.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see CategoryRepository
 * @see OrderRepository
 * @see PhotoRepository
 * @see ProductRepository
 * @see Model
 */
public interface MainRepository<T extends Model, E extends Number> extends JpaRepository<T, E> {
}
