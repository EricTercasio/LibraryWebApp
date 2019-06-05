package Library.dao;

import Library.model.Book;
import Library.model.Books;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findByisbn(String isbn);

    Iterable<Book> findByAuthor(String name);
}
