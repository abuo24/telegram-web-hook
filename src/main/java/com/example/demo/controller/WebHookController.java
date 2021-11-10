package com.example.demo.controller;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.service.TgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
public class WebHookController {

    private final TgService tgService;

    @PostMapping
    public void getChanges(@RequestBody Update update){
        System.out.println(update);
        tgService.updateWait(update);

    }
}
