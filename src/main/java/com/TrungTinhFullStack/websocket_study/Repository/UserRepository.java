package com.TrungTinhFullStack.websocket_study.Repository;

import com.TrungTinhFullStack.websocket_study.Entity.User;
import com.TrungTinhFullStack.websocket_study.Enum.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByStatus(Status status);
}
