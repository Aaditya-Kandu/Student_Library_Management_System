package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDTO;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDTO authorEntryDTO){

        //Important: I pass entity type AuthorEntityDTO but repository read only Author Entity
        // So, We need to convert Author Entity DTO to   ---->>>>>> Author entity

        Author author = new Author();

        // We are set the attribute value so that we found correct value in DB

        author.setName(authorEntryDTO.getName());

        author.setAge(authorEntryDTO.getAge());

        author.setCountry(authorEntryDTO.getCountry());

        author.setRating(author.getRating());



        authorRepository.save(author);


        return "Author added successful";


    }

}
