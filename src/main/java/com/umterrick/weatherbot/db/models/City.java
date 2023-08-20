package com.umterrick.weatherbot.db.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Entity
@Table(name = "cities")
@Getter
@Data
//@RequiredArgsConstructor
public class City extends BaseModel{
    @Id
    private Long id;
    private String country;
    private String name;

    @ManyToMany(mappedBy = "cities", cascade = {CascadeType.ALL})
    private List<TelegramUser> users;


}
