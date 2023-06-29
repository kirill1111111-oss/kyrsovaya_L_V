package com.devcolibri.secure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity//Говорит Spring о том что обьекты данного класса необходимо поместить в таблицу БД
@Table(name = "Record")//Даем Spring указание о том что таблица должна называться Record
@Setter//Позволяем lombok самому создать set
@Getter//Позволяем lombok самому создать get
@AllArgsConstructor//Позволяем lombok самому создавать конструкторы над полями
@NoArgsConstructor//Позволяем lombok самому создавать конструкторы над полями без параметров
public class PC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//Разрешаем Spring самому генерировать id
    private String title;
    private String number;
    private String description;
}
