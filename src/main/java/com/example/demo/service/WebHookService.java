package com.example.demo.service;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.RestConstants;
import com.example.demo.payload.ResultTelegram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class WebHookService {

    private final RestTemplate restTemplate;

    public void whenStart(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId(), "Xush kelibsiz");
        ResultTelegram result = restTemplate.postForObject(
                RestConstants.TELEGRAM_BASE_URL + RestConstants.BOT_TOKEN + "/sendMessage",
                sendMessage, ResultTelegram.class);

    }

}
