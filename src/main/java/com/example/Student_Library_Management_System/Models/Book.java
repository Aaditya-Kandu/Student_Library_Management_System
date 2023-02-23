package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    private int pages;

    private double bookRating;

    private boolean isIssued;

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public double getBookRating() {
        return bookRating;
    }

    public void setBookRating(double bookRating) {
        this.bookRating = bookRating;
    }

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    // Book class is child with respect to the Author class;
    // Setting here a foreign key : Stander 3 steps;

    // this is part of mapping in child class
    @ManyToOne // told relation b/w book and author
    @JoinColumn  //  it is add foreign key in child table that is by default primary key of parent class (Author);
    private Author author;


    @ManyToOne
    @JoinColumn
    private Card card;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public  Book(){

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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    //>>>>>> Now we do mapping in parent class so that it make bidirectional.<<<<<<<//

    // for This mapping use cassCade

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transactions> transactionsList = new ArrayList<>();

    // make getter and setter also


    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
}
