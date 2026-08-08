package com.ibm.chandana.Annotation;

import java.util.List;

public class Annotation {
	
	public static void main(String args[]) {
		try {
			Validator v = new Validator();
			List<String> error1 =v.validate(new UserRegistration(null,"mail.com",null));
			error1.forEach(s -> System.out.println(s));
            List<String> error2 = v.validate(null);
            error2.forEach(System.out::println);
            List<String> error3 =v.validate(new UserRegistration("Teju","mail.com",null));
            error3.forEach(System.out::println);
            List<String> error4 =v.validate(new UserRegistration("Teju","mail.com",List.of("123456")));
            error4.forEach(System.out::println);
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
	}
	

}
