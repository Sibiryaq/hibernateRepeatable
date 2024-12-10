package ru.example.hibernateRepeatable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.Instant;

@Entity
@Table(name = "users", schema = "public") // В PostgreSQL schema по умолчанию паблик
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //автоматические будет присваиваться автоинкрементное значение,
    //тем самым переложили отвественность на БД
    //В случае успешной  вставки в таблицу фреймворк запишет в это поле новое значение, полученное от базы
    Long id;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", length = 128)
    String lastName;

    String email;

    @Column(name = "registration_date")
    private Instant registrationDate = Instant.now();

    @Enumerated(EnumType.STRING)
    private UserState state;

    /*
    CREATE TABLE users (
        id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
        --
    )*/
}
