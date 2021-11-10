package com.example.demo.feign;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.RestConstants;
import com.example.demo.payload.ResultTelegram;
import com.example.demo.payload.SendPhotoOwn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@FeignClient(url = RestConstants.TELEGRAM_BASE_URL_WITHOUT_BOT, name = "TelegramFeign")
public interface TelegramFeign {

    @PostMapping("{path}/sendMessage")
    ResultTelegram sendMessageToUser(@PathVariable String path,
                                     @RequestBody SendMessage sendMessage);
    @PostMapping("{path}/sendPhoto")
    ResultTelegram sendPhotoToUser(@PathVariable String path,
                                     @RequestBody SendPhotoOwn sendPhotoOwn);
}
