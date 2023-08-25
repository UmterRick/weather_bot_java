package com.umterrick.weatherbot.db.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Entity
@Table(name = "cities")
@Getter
@Data
//@RequiredArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String country;
    private String name;
    private String coordinates;

    @ManyToMany(mappedBy = "cities", cascade = {CascadeType.ALL})
    private List<TelegramUser> users;


}
