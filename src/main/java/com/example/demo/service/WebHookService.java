package com.example.demo.service;// Author - Orifjon Yunusjonov 
// t.me/coderr24

import com.example.demo.constants.RestConstants;
import com.example.demo.bot.Command;
import com.example.demo.constants.URLConstants;
import com.example.demo.entity.User;
import com.example.demo.feign.TelegramFeign;
import com.example.demo.payload.ResultTelegram;
import com.example.demo.payload.SendPhotoOwn;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WebHookService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final TelegramFeign telegramFeign;

    private String path = "bot" + RestConstants.BOT_TOKEN;
    private String phone;

    public void whenStart(Update update) {
        Long id = update.getMessage().getChatId();
        userRepository.save(new User(id, Command.START));

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setRequestContact(true);
        keyboardButton.setText("Telefon raqamimni yuborish");

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton);
        List<KeyboardRow> row = new ArrayList<KeyboardRow>(Arrays.asList(keyboardRow));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(row);
        replyKeyboardMarkup.setSelective(true);

        SendMessage sendMessage = new SendMessage(id, "Ro'yxatga olish uchun telefon raqamingizni kiriting! \nMasalan +998xx xxx xx xx").setReplyMarkup(replyKeyboardMarkup);
        ResultTelegram result1 = telegramFeign.sendMessageToUser(path, sendMessage);

        userService.saveCommand(id,Command.CONTACT);

    }

    public void getContact(Update update) {
        Long id = update.getMessage().getChatId();
        String contact = update.getMessage().getContact().getPhoneNumber();
        User user = userRepository.findByChatId(id).orElseThrow(() -> new RuntimeException("user not found"));
        SendMessage sendMessage = new SendMessage(id, contact + " shu no'mer siznikimi tasdiqlang!");
        ResultTelegram result1 = telegramFeign.sendMessageToUser(path, sendMessage);
        userService.edit(id,contact);
        userService.saveCommand(id,Command.CHECK_CONTACT);
    }

    public void checkCode(Update update) {
        Long id = update.getMessage().getChatId();
        String code = update.getMessage().getText();
        User user = userRepository.findByChatId(id).orElseThrow(() -> new RuntimeException("user not found"));
        if (code.equals("1234")){

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Qoidalarni o'qish");
            button.setUrl(URLConstants.FAQ_URL);
            button.setCallbackData("test");

            InlineKeyboardButton button1 = new InlineKeyboardButton();
            button.setText("Roziman!");
            button.setCallbackData("read");

            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>(Arrays.asList(button));
            List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>(Arrays.asList(button1));
            keyboardButtonsRow1.add(button);
            keyboardButtonsRow2.add(button1);

            InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
            inlineKeyboardMarkup.setKeyboard(new ArrayList<>(Arrays.asList(keyboardButtonsRow1,keyboardButtonsRow2)));

            SendPhoto sendPhoto = new SendPhoto().setChatId(id).setPhoto(new File(URLConstants.FAQ_URL)).setCaption("Qo'llanmalar bilan tanishib chiqing!").setReplyMarkup(inlineKeyboardMarkup);
            ResultTelegram result1 = telegramFeign.sendPhotoToUsers(path, sendPhoto);
            System.out.println(result1);
            userService.saveCommand(id,Command.FAQ);
        } else {

        }
    }
    public void faq(Update update){
        Long id = update.getMessage().getChatId();
        String code = update.getMessage().getText();
        User user = userRepository.findByChatId(id).orElseThrow(() -> new RuntimeException("user not found"));

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setRequestContact(true);
        keyboardButton.setText("\uD83D\uDED2 Buyurtma qilish");

        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton.setRequestContact(true);
        keyboardButton.setText("\uD83D\uDECD Buyurtmalarim");

        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton.setRequestContact(true);
        keyboardButton.setText("\uD83D\uDC6A EVOS Oilasi");

        KeyboardButton keyboardButton3 = new KeyboardButton();
        keyboardButton.setRequestContact(true);
        keyboardButton.setText("✍️ Fikr bildirish");

        KeyboardButton keyboardButton4 = new KeyboardButton();
        keyboardButton.setRequestContact(true);
        keyboardButton.setText("⚙️ Sozlamalar");

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton);

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow.add(keyboardButton1);
        keyboardRow.add(keyboardButton2);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow.add(keyboardButton3);
        keyboardRow.add(keyboardButton4);

        List<KeyboardRow> row = new ArrayList<KeyboardRow>(Arrays.asList(keyboardRow,keyboardRow1,keyboardRow2));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(row);
        replyKeyboardMarkup.setSelective(true);

        SendMessage sendMessage = new SendMessage(id, "Quyidagilardan birini tanlang").setReplyMarkup(replyKeyboardMarkup);
        ResultTelegram result1 = telegramFeign.sendMessageToUser(path, sendMessage);
    }




}
