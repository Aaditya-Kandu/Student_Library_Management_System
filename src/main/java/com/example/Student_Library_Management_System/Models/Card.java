package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card_table")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp    // set or stamp real time when an entry is created
    Date createdOn;

    @UpdateTimestamp     // set or update real time when an entry update
    Date updatedOn;

    @Enumerated(value = EnumType.STRING) // Enumerated datatype use as a String
    private CardStatus cardStatus;

    public Card(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }


    @OneToOne      // These 3 lines are use for the estabilis unidirectional relation for parent class(Student)  &  child class(Card)
    @JoinColumn
    private Student studentVariableName;


    // Card is parent wrt book;
    @OneToMany(mappedBy = "card" ,cascade = CascadeType.ALL)
   private List<Book> booksIssued = new ArrayList<>();

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public Student getStudentVariableName() {
        return studentVariableName;
    }

    public void setStudentVariableName(Student studentVariableName) {
        this.studentVariableName = studentVariableName;
    }


    //>>>>>>>>>Now mapping also happen here of transaction entity so that work as bidirectional<<<<<//
    // It is parent class so that use cascade;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transactions>  transactionsList = new ArrayList<>();

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
    //    public List<Transactions> getCards() {
//        return cards;
//    }
//
//    public void setCards(List<Transactions> cards) {
//        this.cards = cards;
//    }
}
