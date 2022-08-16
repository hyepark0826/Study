package backboard;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.dto.Board;
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class LombokTest {
	@Autowired
	Board board;
	@Test(expected = NullPointerException.class)
	public void test() {
		board.setBoardNo(1);
		board.setBoardId(null);
	}

}
