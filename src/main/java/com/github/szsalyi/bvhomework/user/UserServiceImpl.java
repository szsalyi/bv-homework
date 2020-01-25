package com.github.szsalyi.bvhomework.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveIfNotRegistered(User user) {
        if(null == userRepository.findByName(user.getName())) {
            logger.debug("Saving " + user.getName());
            userRepository.save(user);
            logger.debug("Saved " + user.getName());
        }
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
