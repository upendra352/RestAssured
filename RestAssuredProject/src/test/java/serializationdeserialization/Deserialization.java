package serializationdeserialization;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Deserialization {

	public static void main(String[] args) throws JsonProcessingException {
		
		String jsonResponse="{\r\n"
				+ "  \"name\" : \"Upendra\",\r\n"
				+ "  \"age\" : 30\r\n"
				+ "}\r\n"
				+ "";
		
		ObjectMapper objectMapper= new ObjectMapper();
		Person person=objectMapper.readValue(jsonResponse, Person.class);
		System.out.println(person.getName());
		System.out.println(person.getAge());
		System.out.println(person);//serializationdeserialization.Person@62fdb4a6
		
		// to overcome that error need to override toString method in Person class.(right click -> source ->Generate toString 

		
//		by using Map	
		Map<String,Object> personMap=objectMapper.readValue(jsonResponse, Map.class);
		System.out.println(personMap.get("name"));
		System.out.println(personMap.get("age"));

		
	}

}
