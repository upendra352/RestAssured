package fakerJava;

import com.github.javafaker.Faker;

public class CreateRandomValuesUsingJavaFaker {

	public static void main(String[] args) {
		
//		Create Dummy Data using javaFaker
		
//		add maven dependency "javafacker" in pom.xml
		
//		tken:d67b2fa9ab1448c4ef007bb49a1106b92d8aedde47e9a0f5b32572ba7b900b3a//for GoRest Api token Bearer 
		
		Faker faker=new Faker();
		String fullName=faker.name().fullName();
		System.out.println(fullName);
		
		String lastName=faker.name().lastName();
		System.out.println(lastName);
		
		String email=faker.internet().emailAddress();
		System.out.println(email);
		
		String password=faker.internet().password(5, 8);
		System.out.println(password);
		
		
		String phoneNumber=faker.phoneNumber().cellPhone();
		System.out.println(phoneNumber);

	}

}
