package hunbow.big.springbot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;

@Service
public class TelegramBotService implements LongPollingSingleThreadUpdateConsumer, SpringLongPollingBot {

    private final TelegramClient telegramClient;
    private final String botToken;
    private final AiChatService aiChatService;

    public TelegramBotService(TelegramClient telegramClient,
                              String botToken,
                              @Qualifier("open-ai-chat") AiChatService aiChatService) {
        this.telegramClient = telegramClient;
        this.botToken = botToken;
        this.aiChatService = aiChatService;
    }

    public String getBotToken() {
        return botToken;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = SendMessage.builder()
                    .chatId(chatId)
                    .text(aiChatService.getResponseMessage(userMessage))
                    .build();

            try {
                telegramClient.execute(message);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
