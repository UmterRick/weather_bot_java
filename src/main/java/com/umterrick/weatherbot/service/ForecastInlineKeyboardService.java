package com.umterrick.weatherbot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ForecastInlineKeyboardService {
    public InlineKeyboardMarkup getForecastInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton tomorrowButton = new InlineKeyboardButton();
        InlineKeyboardButton threeDaysButton = new InlineKeyboardButton();
        InlineKeyboardButton fiveDaysButton = new InlineKeyboardButton();

        tomorrowButton.setText("Прогноз на завтра");
        threeDaysButton.setText("Прогноз на 3 дні");
        fiveDaysButton.setText("Прогноз на 5 днів");

        tomorrowButton.setCallbackData("1");
        threeDaysButton.setCallbackData("3");
        fiveDaysButton.setCallbackData("5");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(tomorrowButton);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(threeDaysButton);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(fiveDaysButton);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
