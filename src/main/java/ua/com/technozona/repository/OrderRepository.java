package ua.com.technozona.repository;

import ua.com.technozona.model.Order;


public interface OrderRepository extends MainRepository<Order, Long> {

    Order findByNumber(String number);

    void deleteByNumber(String number);
}
