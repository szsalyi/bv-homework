package com.github.szsalyi.bvhomework.user;

public interface UserService {
    void saveIfNotRegistered(User user);
    void deleteUser(User user);
}
