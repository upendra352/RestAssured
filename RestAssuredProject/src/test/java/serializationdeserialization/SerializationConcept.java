package serializationdeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationConcept {

	public static void main(String[] args) throws JsonProcessingException {
		
		Person person = new Person();
		person.setName("Upendra");
		person.setAge(30);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
		
		System.out.println(jsonString);
		
	}

}
