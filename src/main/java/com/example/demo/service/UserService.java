package com.example.demo.service;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.bot.Command;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveCommand(Long chatId, Command command){
        User user = userRepository.findByChatId(chatId).orElseThrow(()-> new RuntimeException("user not found"));
        user.setCommand(command);
        userRepository.save(user);
    }
   public void edit(Long chatId, String phone){
        User user = userRepository.findByChatId(chatId).orElseThrow(()-> new RuntimeException("user not found"));
        user.setPhone(phone);
        userRepository.save(user);
    }
}
