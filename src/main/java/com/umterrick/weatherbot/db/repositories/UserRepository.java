package com.umterrick.weatherbot.db.repositories;

import com.umterrick.weatherbot.db.models.telegram.TelegramUser;
import com.umterrick.weatherbot.enums.BotState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TelegramUser, Long> {
    TelegramUser findByChatId(long chatId);

    BotState findBotStateByChatId(long chatId);

}
