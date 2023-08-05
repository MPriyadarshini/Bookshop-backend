package com.example.demo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="*")
public class BookController {
	@Autowired
	BookRepo bookRepo;
	
	@GetMapping("/book/find")
	public Book findById(@RequestParam int id) {
		
		
		Book book =bookRepo.findById(id).get();
		 
		book.setImage(decompressBytes(book.getImage()));
		 
		 return book;
		
	}
	
	@PostMapping("/book/add")
	public String addProduct(@RequestParam ("dietFile") MultipartFile myFile,
			String bookname,
			String price,
			String author,
			String edition,
			String address,
			String phone,
			String other){
		
		try {
			Book prdImage = new Book(bookname,price,author,edition,address,phone,other,
					compressBytes(myFile.getBytes()));
			bookRepo.save(prdImage);		
		}catch(Exception e) {
			
		}
		
		
		
		return "Successfully Added New Product";
		
	}
	
	@GetMapping("/book/delete")
	public List<Book> deleteBook(@RequestParam int id){
		
		bookRepo.deleteById(id);
		
		return getAllProducts();
	}
	@GetMapping("/book/all")
	public List<Book> getAllProducts(){
		
		List<Book> drList = new ArrayList<Book>();
		
		List<Book> resDrList = bookRepo.findAll();
		Book book = null;
		for(int i=0;i<resDrList.size();i++) {
			
			book = resDrList.get(i);
			
			book.setImage(decompressBytes(book.getImage()));
			
			drList.add(book);
			
		}
		
		
		return drList;
	}
	
	// compress the image bytes before storing it in the database
			public static byte[] compressBytes(byte[] data) {
				Deflater deflater = new Deflater();
				deflater.setInput(data);
				deflater.finish();

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				while (!deflater.finished()) {
					int count = deflater.deflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				try {
					outputStream.close();
				} catch (IOException e) {
				}
				System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

				return outputStream.toByteArray();
			}

			// uncompress the image bytes before returning it to the angular application
			public static byte[] decompressBytes(byte[] data) {
				Inflater inflater = new Inflater();
				inflater.setInput(data);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				try {
					while (!inflater.finished()) {
						int count = inflater.inflate(buffer);
						outputStream.write(buffer, 0, count);
					}
					outputStream.close();
				} catch (IOException ioe) {
				} catch (DataFormatException e) {
				}
				return outputStream.toByteArray();
			}



}
