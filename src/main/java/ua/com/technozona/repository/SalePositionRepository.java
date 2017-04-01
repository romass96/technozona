package ua.com.technozona.repository;

import ua.com.technozona.model.SalePosition;

/**
 * Репозиторий для объектов класса {@link SalePosition}, предоставляющий
 * набор методов JPA для работы с БД. Наследует интерфейс {@link MainRepository}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainRepository
 * @see SalePosition
 */
public interface SalePositionRepository extends MainRepository<SalePosition, Long> {
}
