package com.github.szsalyi.bvhomework.message;

import com.github.szsalyi.bvhomework.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstantMessageServiceImpl implements InstantMessageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstantMessageRepository instantMessageRepository;

    @Override
    public void save(InstantMessage instantMessage) {
        instantMessageRepository.save(instantMessage);
    }
}
