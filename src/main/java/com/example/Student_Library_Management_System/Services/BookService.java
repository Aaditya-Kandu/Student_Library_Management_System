package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(Book book){

//        bookRepository.save(book);
//
//        return "Add book successfully";

        //I want to get Author entity
        int authorId = book.getAuthor().getId();

    // Now i am fetching the Author entity

        Author author = authorRepository.findById(authorId).get();

        // set the foreign key att in tha child

        book.setAuthor(author);

        // we need to update list of book written in parent class

        List<Book> currentBooksWritten = author.getBookWritten();

        currentBooksWritten.add(book);

       // author.setBookWritten(currentBooksWritten);


        // Now book is to be saved, but also author is also to be saved.
        //why do we need to again save(update) the author, because
        //the author entity has been updated ......... wee need to resave/update

        authorRepository.save(author);

        // we are no need save the book entity bcz it's added automatically by cascading effect when call the Author

        return "Book added successfully";

    }
}
