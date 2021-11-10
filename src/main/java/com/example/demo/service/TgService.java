package com.example.demo.service;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class TgService {

    private final WebHookService webHookService;

    public void updateWait(Update update){

        if (update.hasMessage()){
            String text = update.getMessage().getText();
            switch (text){
                case "/start":
                    webHookService.whenStart(update);
                    break;

            }
        }

    }

}
