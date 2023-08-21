package com.umterrick.weatherbot.db.repositories;

import com.umterrick.weatherbot.db.models.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TelegramUser, Long> {
    TelegramUser findByChatId(long chatId);
}
