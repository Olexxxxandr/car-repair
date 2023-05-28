package com.olexxxxandr.carrepair.domain.service;

import com.olexxxxandr.carrepair.domain.impl.Employee;
import com.olexxxxandr.carrepair.domain.impl.User;
import com.olexxxxandr.carrepair.domain.impl.Workroom;

public interface AuthService {

    User register(Employee employee, User user);

    User login(String login, String password);

    void logout();

    User getCurrentUser();

    Workroom getCurentWorkroom();
}
