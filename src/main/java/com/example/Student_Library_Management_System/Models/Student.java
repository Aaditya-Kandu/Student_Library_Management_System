package com.example.Student_Library_Management_System.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "student_DB")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String  email;

    private int age;

    private String mobileNo;

    private String country;

    public Student(){

    }

    public Student(int id, String name, String email, int age, String mobileNo, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.mobileNo = mobileNo;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    // use for bidirectional connection
    //In "" we write the variable name that use for connection the unidirection (student)
    @OneToOne(mappedBy = "studentVariableName", cascade = CascadeType.ALL)
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
