package ua.com.technozona.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Model;
import ua.com.technozona.repository.MainRepository;
import ua.com.technozona.service.interfaces.MainService;


import java.util.List;

public abstract class MainServiceImpl<T extends Model> implements MainService<T> {

    private static final int PAGE_SIZE=10;

    private final MainRepository<T,Long> mainRepository;


    public MainServiceImpl(final MainRepository<T,Long> mainRepository) {
        super();
        this.mainRepository = mainRepository;
    }

    /**
     * Добавление модели в базу данных.
     *
     * @param model Модель для добавления.
     */
    @Override
    @Transactional
    public void add(final T model) {
        if (model != null) {
            this.mainRepository.save(model);
        }
    }

    /**
     * Добавление коллекции моделей в базу данных.
     *
     * @param models Коллекция моделей для добавления.
     */
    @Override
    @Transactional
    public void add(final List<T> models) {
        if (models != null && !models.isEmpty()) {
            this.mainRepository.save(models);
        }
    }

    /**
     * Обновление существующей модели в базе данных.
     *
     * @param model Обновленная модель.
     */
    @Override
    @Transactional
    public void update(final T model) {
        if (model != null) {
            this.mainRepository.save(model);
        }
    }

    /**
     * Получение модели по уникальному коду id в базе данных.
     * Режим только для чтения.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link Model} -  модель с кодом id.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр id.
     * @throws BadRequestException       Бросает исключение,
     *                                   если не найдена модель с входящим параметром id.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(final Long id) throws WrongInformationException, BadRequestException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        final T model = this.mainRepository.findOne(id);
        if (model == null) {
            throw new BadRequestException("Can't find model by id " + id + "!");
        }
        return this.mainRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return this.mainRepository.findAll();
    }


    @Override
    @Transactional
    public void remove(final T model) {
        if (model != null) {
            this.mainRepository.delete(model);
        }
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     *
     * @param id Уникальный код модели.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр id.
     */
    @Override
    @Transactional
    public void remove(final Long id) throws WrongInformationException {
        if (id == null) {
            throw new WrongInformationException("No model id!");
        }
        this.mainRepository.delete(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     *
     * @param models Коллекция моделей для удаления.
     */
    @Override
    @Transactional
    public void remove(final List<T> models) {
        if (models != null && !models.isEmpty()) {
            this.mainRepository.delete(models);
        }
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    @Transactional
    public void removeAll() {
        this.mainRepository.deleteAll();
    }
}
