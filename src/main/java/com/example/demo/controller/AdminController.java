package com.example.demo.controller;// Author - Orifjon Yunusjonov 
// t.me/coderr24


import com.example.demo.RestConstants;
import com.example.demo.feign.TelegramFeign;
import com.example.demo.payload.ResultTelegram;
import com.example.demo.payload.SendPhotoOwn;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

//    private final RestTemplate restTemplate;

    private Set<String> ids = new HashSet<>(Arrays.asList("634847822", "293582336"));
    private final TelegramFeign telegramFeign;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String text) {
        for (String id : ids) {
            SendMessage sendMessage = new SendMessage(id, text);
//            ResultTelegram result = restTemplate.postForObject(
//                    RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage",
//                    sendMessage, ResultTelegram.class);
            ResultTelegram resultTelegram = telegramFeign.sendMessageToUser("bot" + RestConstants.BOT_TOKEN, sendMessage);
            System.out.println(resultTelegram);
        }


        return "test";
    }

    @GetMapping("/sendPhoto")
    public void sendPhoto(@RequestParam String fileName, @RequestParam String text) {
        for (String id : ids) {
            SendPhotoOwn sendPhoto = new SendPhotoOwn(id, text, fileName);

//            ResultTelegram result = restTemplate.postForObject(
//                    RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage",
//                    sendMessage, ResultTelegram.class);
            ResultTelegram resultTelegram = telegramFeign.sendPhotoToUser("bot" + RestConstants.BOT_TOKEN, sendPhoto);
            System.out.println(resultTelegram);
        }
    }
}
