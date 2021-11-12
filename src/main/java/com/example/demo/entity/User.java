package com.example.demo.entity;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.bot.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long chatId;

    @Enumerated(EnumType.STRING)
    private Command command;

    private String phone;

    @CreationTimestamp
    private Date createAt;

    @UpdateTimestamp
    private Date updateAt;

    public User(Long chatId, Command command) {
        this.chatId = chatId;
        this.command = command;
    }
}
