package com.deviceproblem.help.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "problem")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column
    private String title;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "user_username")
    private String userUsername;
    @Column
    private String text;
    @ManyToOne
    private Picture image;
    @Column
    private String timestamp;

}

