package com.umterrick.weatherbot.db.models;

import com.umterrick.weatherbot.enums.BotState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
@ToString
public class TelegramUser {

    @Column(name = "userid")
    private long userId;

    @Id
    @Column(name = "chatid")
    private long chatId;

    @Column
    private String username;

    @ManyToOne
    @JoinColumn(name = "maincity_id")
    private City mainCity;


    @Column
    private BotState state;


    @ManyToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<City> cities;

    public TelegramUser(String username, long chatId) {
        this.username = username;
        this.chatId = chatId;
    }


    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }


}
