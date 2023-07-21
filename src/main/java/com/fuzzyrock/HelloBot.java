package com.fuzzyrock;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class HelloBot extends TelegramLongPollingBot {
    private static final String TAG = HelloBot.class.getSimpleName();
    private static final String BOT_USERNAME = "FuzzyAIBot";

    public HelloBot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        FuzzyLog.log(TAG, "onUpdatesReceived(update)");
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();

        FuzzyLog.log(TAG, user.getFirstName() + " wrote " + msg.getText());

        String reply = ChatGPT.chat(msg.getText());

        sendText(id, reply);
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
        FuzzyLog.log(TAG, "onUpdatesReceived(updates)");
    }

    @Override
    public String getBotUsername() {
        FuzzyLog.log(TAG, "getBotUsername: " + BOT_USERNAME);
        return BOT_USERNAME;
    }

    @Override
    public void onRegister() {
        super.onRegister();
        FuzzyLog.log(TAG, "onRegister");
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
