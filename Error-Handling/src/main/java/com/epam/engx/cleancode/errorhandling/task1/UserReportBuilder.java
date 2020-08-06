package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.exceptions.EmptyOrdersException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.InvalidOrderTotalAmountException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.InvalidUserDaoException;
import com.epam.engx.cleancode.errorhandling.task1.exceptions.InvalidUserException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Order;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.User;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.UserDao;

import java.util.List;

public class UserReportBuilder {

    private UserDao userDao;

    public Double getUserTotalOrderAmount(String userId){

        validateUserDao();

        User user = userDao.getUser(userId);
        validateUser(user);

        List<Order> orders = user.getAllOrders();

        validateOrders(orders);

        Double sum = calculateOrdersTotalAmount(orders);

        return sum;
    }

    private Double calculateOrdersTotalAmount(List<Order> orders){
        Double sum = 0.0;
        for (Order order : orders) {
            if (order.isSubmitted()) {
                Double total = order.total();
                validateOrderTotalAmount(total);
                sum += total;
            }
        }
        return sum;
    }

    private void validateOrderTotalAmount(Double total) {
        if (total < 0)
            throw new InvalidOrderTotalAmountException();
    }

    private void validateOrders(List<Order> orders) {
        if (orders.isEmpty())
            throw new EmptyOrdersException();
    }

    private void validateUser(User user){
        if (user == null)
            throw new InvalidUserException();
    }

    private void validateUserDao() {
        if (userDao == null)
            throw new InvalidUserDaoException();
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
