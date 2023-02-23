package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDTO;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {


    @Autowired
    TransactionRepository transactionRepository;

    // For Getting Book Entity
    @Autowired
    BookRepository bookRepository;

    // For Getting Card Entity
    @Autowired
    CardRepository cardRepository;

    public String isIssueBook(IssueBookRequestDTO issueBookRequestDTO) throws Exception {


        int bookId = issueBookRequestDTO.getBookId();
        int cardId = issueBookRequestDTO.getCardId();

        // we get Book Entity , Card Entity, so that we set its attributes.
        // for getting Entity Autowired the repository

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        // Final goal is to make transaction Entity and set its attributes & save it

        Transactions transactions = new Transactions();

        // Setting the Attributes

        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setIssueOperation(true);
        transactions.setTransactionId(UUID.randomUUID().toString());


        // now we set the transaction History
        // Entity are left Success or Failed
        // Check for validation

        if(book == null || (book.isIssued() == true)){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is invalid");
        }

        if (card == null|| (card.getCardStatus() != CardStatus.ACTIVATED)){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);

            throw new Exception("Card is invalid");

        }
        // If both of if statement are not execute it means transaction successful. Now set transaction success

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        // Set attribute for book

        book.setIssued(true);
        // Btw the book and transaction: Bidirectional
        List<Transactions> listOfTransactionForBook = book.getTransactionsList();
        listOfTransactionForBook.add(transactions);
        book.setTransactionsList(listOfTransactionForBook);

        // I need to make change in Card
        // Book and the card

        List<Book> issuedBookForCard = card.getBooksIssued();
        issuedBookForCard.add(book);
        card.setBooksIssued(issuedBookForCard);


        // Card and Transaction

        List<Transactions> transactionsListForCard = card.getTransactionsList();
        transactionsListForCard.add(transactions);
        card.setTransactionsList(transactionsListForCard);

        // save the parent
        // Automatically by cascading book and transaction automatic save;
        cardRepository.save(card);

        return "Book issued successfully";


    }
}
