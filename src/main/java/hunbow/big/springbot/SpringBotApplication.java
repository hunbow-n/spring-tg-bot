package hunbow.big.springbot;

import hunbow.big.springbot.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@SpringBootApplication
public class SpringBotApplication {

    @Value("${telegram.bot.token}")
    private String botToken;
    public static void main(String[] args) {
        SpringApplication.run(SpringBotApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner runner(TelegramClient telegramClient) {
//        return  args -> {
//            try {
//                TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
//                botsApplication.registerBot(botToken, new TelegramBotService(telegramClient));
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//                throw new RuntimeException(ex);
//            }
//        };
//    }



}
