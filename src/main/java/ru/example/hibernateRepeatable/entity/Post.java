package ru.example.hibernateRepeatable.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


/*
Класс Post компилируется и загружается в память в составе приложения.
Фреймворк анализирует класс с помощью специальных функций рефлексии.
По аннотациям устанавливает соответствия между названием таблицы и классом
а также между колонками и полями класс Post
Изолирует разработчика от написания SQL-запросов

Может вызвать метод getLost() для класс Post(select * from post)
фреймфорвк сформирует запрос:
elect _post0.id as _post_id, _post0.message as _post_message from post as _post0

Полученный запрос отправляется в dataSource и через драйвер БД попадает на сервер БД
После получения ответа от БД, преобразует исходный набор ячеек в каждой строке
результата в экземпляр класса Post
C помощью сеттеров заполняет нужные поля объекта значениями и возвращает список объектов в клиентский код

 НЕ нужно создавать мапперы вручную, уменьшается вероятность ошибки при составлении SQL-запроса

 "-" все действия фремворка по инициализации классов, формированию запросов и конвертации результатов
 замедляют скорость обработки данных



 */

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "message")
    private String message;



}
