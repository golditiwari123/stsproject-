package com.webapp;

import java.util.List;import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.entity.Book;
import com.webapp.service.BookService;

@Controller
@RestController
public class TestController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	@ResponseBody
	public ResponseEntity<List<Book>> getBooks() {
    List<Book> list= this.bookService.getAllBooks();
    if(list.size()<=0) {
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }else {
    	
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
	   if(book==null) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	
	}
	return ResponseEntity.of(Optional.of(book));
	
	}	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookService.addBook(book);
			System.out.println(book);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
//		Book b = this.bookService.addBook(book);
//		System.out.println(book);
//		return b;
	}
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		try {
		this.bookService.deleteBook(bookId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId) {
		try {
		this.bookService.updateBook(book,bookId);
		return ResponseEntity.ok().body(book);
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}

}
