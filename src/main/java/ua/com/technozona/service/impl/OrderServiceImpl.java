package ua.com.technozona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.technozona.exception.BadRequestException;
import ua.com.technozona.exception.WrongInformationException;
import ua.com.technozona.model.Order;
import ua.com.technozona.repository.OrderRepository;
import ua.com.technozona.service.interfaces.OrderService;


import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@ComponentScan(basePackages = "ua.com.technozona.repository")
public final class OrderServiceImpl
        extends MainServiceImpl<Order>
        implements OrderService {

    private final OrderRepository repository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public OrderServiceImpl(final OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }


}
