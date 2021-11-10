package com.example.demo.payload;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultTelegram {

    private String ok;
    private Message result;

}
