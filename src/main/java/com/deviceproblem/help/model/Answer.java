package com.deviceproblem.help.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column(name = "question_id")
    private int questionId;
    @Column
    private String text;
    @Column
    private String timestamp;
    @Column(name = "pic_url")
    private String image;
    @Column(name = "user_username")
    private String userUsername;
}
