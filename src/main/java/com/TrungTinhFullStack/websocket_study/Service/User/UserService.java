package com.TrungTinhFullStack.websocket_study.Service.User;

import com.TrungTinhFullStack.websocket_study.Entity.User;
import com.TrungTinhFullStack.websocket_study.Enum.Status;
import com.TrungTinhFullStack.websocket_study.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disConnect(User user) {
        var storedUser = userRepository.findById(user.getId()).orElse(null);
        if(storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
