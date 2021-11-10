package com.example.demo.payload;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendPhotoOwn {
    @JsonProperty("chat_id")
    private String chatId;
    private String caption;
    private String fileName;

    public SendPhotoOwn(String chatId, String caption, String fileName) {
        this.chatId = chatId;
        this.caption = caption;
        this.fileName = fileName;
    }
}
