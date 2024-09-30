package com.TrungTinhFullStack.websocket_study.Entity;

import com.TrungTinhFullStack.websocket_study.Enum.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private Status status;
}
