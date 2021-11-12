package com.example.demo.service;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.bot.Command;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class TgService {

    private final WebHookService webHookService;
    private final UserRepository userRepository;

    public void updateWait(Update update) {

        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            Long id = update.getMessage().getChatId();
            User user = null;
//            try {
                user = userRepository.findByChatId(id).orElse(null);
//            } catch (Exception e){
//                System.out.println(e);
//            }
            System.out.println(user);
            if (text != null && text.equals("/start") && user == null) {
                webHookService.whenStart(update);
            } else if (user.getCommand().equals(Command.CONTACT)) {
                webHookService.getContact(update);
            } else if (user.getCommand().equals(Command.CHECK_CONTACT)) {
               webHookService.checkCode(update);
            }else if (user.getCommand().equals(Command.FAQ)) {
               webHookService.faq(update);
            }
        }

    }

}
