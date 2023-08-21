package com.umterrick.weatherbot.db.models;

import com.umterrick.weatherbot.enums.BotState;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Component
public class TelegramUser extends BaseModel {

    @Id
    private long chatId;
    @Column
    private String username;

    @Column
    @ManyToOne
    private City mainCity;


    @Column
    private BotState state;


    @ManyToMany(cascade = {CascadeType.ALL})
    private List<City> cities;


    public TelegramUser(String username, long chatId) {
        this.username = username;
        this.chatId = chatId;
    }

    public TelegramUser() {

    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

    @Override
    public String toString() {
        return "TelegramUser{id=" + getId() + ", username=" + username + ", chatId=" + chatId + "}";
    }

}
