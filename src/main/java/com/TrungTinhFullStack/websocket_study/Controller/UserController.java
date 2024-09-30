package com.TrungTinhFullStack.websocket_study.Controller;

import com.TrungTinhFullStack.websocket_study.Entity.User;
import com.TrungTinhFullStack.websocket_study.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload  User user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disConnectUser")
    @SendTo("/user/topic")
    public User disConnect(@Payload User user) {
        userService.disConnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
