package com.webapp.service;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webapp.dao.BookRepository;
import com.webapp.entity.Book;

@Component
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
//
//	private static List<Book> list = new ArrayList<>();
//	
// static {
//	list.add(new Book(12,"Java Complete Reference","XYZ"));
//	list.add(new Book(36,"Head First to Java ","ABC"));
//	list.add(new Book(56,"Think in Java","LMN"));
//	
	//}
	
	public List<Book> getAllBooks() {
		List<Book> list=(List<Book>)this.bookRepository.findAll();
		return list;
	}
	
	public Book getBookById(int id) {
		Book book = null;
		try {
	//book = list.stream().filter(e->e.getId()==id).findFirst().get();
		
			book=this.bookRepository.findById(id);
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return book;
	}
	
	public Book addBook(Book b) {
		Book result = bookRepository.save(b);
		return result;
	}
	
	public void deleteBook(int bid) {
//		list = list.stream().filter(book->{
//			if(book.getId()!=bid)
//			{
//				return true;
//				
//			}else {
//				return false;
//			}
//		}).collect(Collectors.toList());
		bookRepository.deleteById(bid);
	}
	
	public void updateBook(Book book,int bookId) {
//		list = list.stream().map(b->{
//			if(b.getId()==bookId) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(b.getAuthor()); 
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(bookId);
		bookRepository.save(book);
	}
}

