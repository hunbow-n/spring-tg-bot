package hunbow.big.springbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class TelegramBotService implements LongPollingSingleThreadUpdateConsumer {



    private final TelegramClient telegramClient;

    public TelegramBotService(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }


    @Override
    public void consume(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text(userMessage)
                    .build();

            try {
                telegramClient.execute(message);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
