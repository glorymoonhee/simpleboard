package demo.properties;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Scanner;

import org.junit.Test;

public class TestProperties {

	@Test
	public void test_reading_properties() throws URISyntaxException {
//		System.out.println(getClass().getResource("/logincheck-url.txt").toURI());
		ClassLoader loader = TestProperties.class.getClassLoader();
		InputStream in = loader.getResourceAsStream("db.properties");
		Scanner sc = new Scanner(in);
		assertEquals ( "product.db.url=jdbc:mysql://localhost:3306/sboarddb", sc.nextLine());
		assertEquals ( "product.db.user=root", sc.nextLine());
		assertEquals ( "product.db.pass=1111", sc.nextLine());
		sc.close();
		
		in = loader.getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			assertEquals ( "jdbc:mysql://localhost:3306/sboarddb", prop.get("product.db.url"));
			assertEquals ( "root", prop.getProperty("product.db.user"));
			assertEquals ( "1111", prop.getProperty("product.db.pass"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
