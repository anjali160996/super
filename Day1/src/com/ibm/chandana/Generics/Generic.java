package com.ibm.chandana.Generics;

import java.util.List;
import java.util.NoSuchElementException;

public class Generic {
	public static void main(String[] args) {
		Repository<User> users = new Repository<>(100);
		UserService userService = new UserService(users);
		users.save(new User(1L, "Prakash", "prakash@gmail.com"));
		users.save(new User(2L, "Arjun", "arjun@gmail.com"));
		users.save(new User(3L, "Maya", "maya@gmail.com"));
		users.save(new User(4L, "Riya",null));
		users.save(new User(5L, "Vikram", "vikram@gmail.com"));
		users.save(new User(6L, "Aisha", "aisha@gmail.com"));
		users.save(new User(7L, "Kiran"));
		users.save(new User(8L, "Neha", "neha@gmail.com"));
		users.save(new User(9L, "Rahul"));
		users.save(new User(10L, "Sneha", "sneha@gmail.com"));
		users.save(new User(11L, "Aditya"));
		users.save(new User(12L, "Pooja", "pooja@gmail.com"));
		User u = users.findById(1L).orElseThrow(() -> new NoSuchElementException("User not found with id: 1"));
		System.out.println(u);
		System.out.println(userService.findNameOr(2L, "Guest"));
		System.out.println(userService.findNameOr(null, "Guest"));
		System.out.println(userService.findNameOr(1L, "Guest")) ;                       
		System.out.println(userService.findNameOr(99L, "Guest"));                      
		System.out.println("userService.findEmail(2L)--->"+userService.findEmail(1L));
		System.out.println("userService.findEmail(2L)--->"+userService.findEmail(2L));    
		System.out.println("userService.findEmail(4L)--->"+userService.findEmail(4L)); 
		System.out.println("userService.findVerifiedEmail(1L, e -> e.contains(\"ibm\"))--->"+userService.findVerifiedEmail(1L, e -> e.contains("ibm")));
		System.out.println("userService.findVerifiedEmail--->"+userService.findVerifiedEmail(2L, e -> true));
		Repository<Product> products = new Repository<>(100);
		ProductService productService = new ProductService();
		products.save(new Product(10L, "Keyboard",100));
		products.save(new Product(20L, "Mouse",200));
		products.save(new Product(30L, "Monitor",10));
		products.save(new Product(40L, "KLaptop",2000));
		products.findById(30L).ifPresentOrElse(p -> System.out.println(p),
				() -> System.out.println("Product  is found"));
		List<Product> allp = products.findAll();
		System.out.println(allp);
		ProductStats productstatus = productService.summarizeProducts(products, "Key");
		System.out.println(productstatus.toString());
	}
}
