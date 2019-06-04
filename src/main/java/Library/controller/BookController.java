package Library.controller;


import Library.dao.BookRepository;
import Library.dao.BooksRepository;
import Library.model.Book;
import Library.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/library")

public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BooksRepository booksRepository;

    @GetMapping(path = "/books")
    public @ResponseBody Iterable<Books> getAllBooks(){
        return booksRepository.findAll();
    }

    @GetMapping(path = "/book")
    @CrossOrigin
    public @ResponseBody Iterable<Book> getAllBook(){
        return bookRepository.findAll();
    }

    @PostMapping(path = "/books")
    public @ResponseBody Books addBooks(@RequestBody Books books){
        Books newBooks = new Books();
        newBooks.setBookId(books.getBookId());
        newBooks.setQuantity(books.getQuantity());
        return booksRepository.save(newBooks);
    }

    @GetMapping(path = "/book/{isbn}")
    public @ResponseBody Book getBookByISBN(@PathVariable("isbn") String bookISBN){
        return bookRepository.findByisbn(bookISBN);
    }

    @PostMapping(path = "/book")
    @CrossOrigin
    public @ResponseBody Book addBook(@RequestBody Book book){
        Book newBook = new Book();
        return setBook(book, newBook);
    }
    @PostMapping(path = "/edit/{isbn}")
    @CrossOrigin
    public @ResponseBody Book editBook(@PathVariable("isbn") String bookISBN, @RequestBody Book book){
        Book newBook = bookRepository.findByisbn(bookISBN);
        return setBook(book, newBook);
    }

    private Book setBook(@RequestBody Book book, Book newBook) {
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setInStock(book.getInStock());
        newBook.setIsbn(book.getIsbn());
        newBook.setPrice(book.getPrice());
        return bookRepository.save(newBook);
    }


}
