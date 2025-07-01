package serializationdeserialization;

import java.io.IOException;

import org.apache.juneau.html.HtmlSerializer;
import org.apache.juneau.json.JsonParser;
import org.apache.juneau.json.JsonSerializer;
import org.apache.juneau.parser.ParseException;
import org.apache.juneau.xml.XmlSerializer;

public class ProductTest {

	public static void main(String[] args) throws ParseException, IOException {
		
//		POJO to JSON:
		
		JsonSerializer jsonSerializer=JsonSerializer.DEFAULT_READABLE; 
		
		String[] sellerNames= {"Neon Enterprise", "abc ltd", "xyx ltd"};
		Product product = new Product("MackBookPro", 1000, "White", sellerNames);
		String json=jsonSerializer.serialize(product);
		System.out.println(json);
		
//		POJO to XML:
		
		XmlSerializer xmlSerializer=XmlSerializer.DEFAULT_NS_SQ_READABLE;
		String xml=xmlSerializer.serialize(product);
		System.out.println(xml);
		
//		POJO to HTML:
		
		HtmlSerializer htmlSerializer=HtmlSerializer.DEFAULT_SQ_READABLE;
		String html=htmlSerializer.serialize(product);
		System.out.println(html);
		
		
//		Deserialization
//		JSON to POJO:
		
		JsonParser jsonParser=JsonParser.DEFAULT;
		
		String jsonVal="{\r\n"
				+ "	\"color\": \"White\",\r\n"
				+ "	\"name\": \"MackBookPro\",\r\n"
				+ "	\"price\": 1000,\r\n"
				+ "	\"sellerName\": [\r\n"
				+ "		\"Neon Enterprise\",\r\n"
				+ "		\"abc ltd\",\r\n"
				+ "		\"xyx ltd\"\r\n"
				+ "	]\r\n"
				+ "}";
		Product pro=jsonParser.parse(jsonVal, Product.class);
		System.out.println(pro.getColor());
		System.out.println(pro);//serializationdeserialization.Product@27ae2fd0 to overcome this issue orverride toString method in Product class.

	}

}
