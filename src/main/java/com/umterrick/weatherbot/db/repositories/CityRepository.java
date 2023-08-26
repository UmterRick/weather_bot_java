package com.umterrick.weatherbot.db.repositories;

import com.umterrick.weatherbot.db.models.City;
import com.umterrick.weatherbot.db.models.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findById(long id);
    City findByName(String name);
}
