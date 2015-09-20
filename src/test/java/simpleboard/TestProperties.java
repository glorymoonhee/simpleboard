package simpleboard;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TestProperties {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
	}
	@Test
	public void test_reading_properties() {
		ClassLoader loader = TestProperties.class.getClassLoader(); 
		InputStream in = loader.getResourceAsStream("db.properties");
		Scanner sc = new Scanner(in);
		assertEquals("product.db.url=jdbc:mysql://localhost:3306/sboarddb", sc.nextLine());
		assertEquals("product.db.user=root", sc.nextLine());
		assertEquals("product.db.pass=1111", sc.nextLine()); 
		
		
		 in = loader.getResourceAsStream("db.properties");
		Properties prop = new Properties();
		
		try {
			prop.load(in);
	/*		assertEquals("jdbc:mysql://localhost:3306/sboarddb",prop.get("product.db.url"));
			assertEquals("product.db.user=root",prop.get("product.db.user"));
			assertEquals("product.db.pass=1111",prop.get("product.db.pass"));*/
		
         
			 assertEquals("root",prop.getProperty("product.db.user"));
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
