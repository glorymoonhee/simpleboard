package simpleboard;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRegex {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		String s = "sdaoekalxdkdkjdkj123wodkxlsklskskjkk#";
		assertEquals ( true, s.matches(".*123.*") );
		
		// 맨 앞에는 "/post/read/" 가 오고 그 뒤에는 반드시 숫자 형태가 덧붙어야 함!
		String pattern = "/post/read/[0-9]+$";
		assertEquals ( true, "/post/read/3938383".matches(pattern));
		assertEquals ( false, "/post/read/3938383a".matches(pattern));
		assertEquals ( false, "/post/read".matches(pattern));
		
		s = "abCCC";

		// ab, abx abab, ababcccccccccccccccc*
		assertTrue ( s.matches("(ab)+C*") );
		//+ : 문자하나 이상 찾기
		//. : 모든문자일치 (한문자)
		//* : 문자가 없는경우,하나인상 연속하는 문자.\
		
		
		
	}

}
