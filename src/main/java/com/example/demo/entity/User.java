package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String mobile;
    private String gender;
    private int age;
    private String nationality;
}
