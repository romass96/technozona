package ua.com.technozona.repository;

import ua.com.technozona.model.Role;
import ua.com.technozona.model.User;

import java.util.List;

/**
 * Репозиторий для объектов класса {@link User}, предоставляющий
 * набор методов JPA для работы с БД.
 * Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainRepository
 * @see User
 */
public interface UserRepository
        extends MainRepository<User, Long> {
    /**
     * Возвращает пользователя из базы даных, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с именем name.
     */
    User findByName(String name);


    User findByEmail(String email);

    /**
     * Возвращает пользователя из базы даных, у которого совпадает роль с значением
     * входящего параметра.
     *
     * @param role Роль пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с ролью role.
     */
    User findByRole(Role role);

    /**
     * Возвращает всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     *
     * @param role Роль пользователей для возврата.
     * @return Объект типа {@link List} - список пользователей,
     * которые имеют роль role.
     */
    List<User> findAllByRole(Role role);

    /**
     * Удаляет всех пользователей из базы данных, у которых
     * совпадают роли с значением входящего параметра.
     *
     * @param role Роль пользователей для удаления.
     */
    void deleteAllByRole(Role role);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает имя с значением
     * входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    void deleteByName(String name);
}
