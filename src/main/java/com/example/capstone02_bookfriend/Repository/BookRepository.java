package com.example.capstone02_bookfriend.Repository;

import com.example.capstone02_bookfriend.Model.Book;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Book findBooksById(Integer id);

    Book findBooksByIsbn(String isbn);


}
