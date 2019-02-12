package edu.spring.myboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.persistence.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {
			"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
	}
)
@WebAppConfiguration
public class BoardTest {
	
	@Autowired BoardDao boardDao;
	
	@Test
	public void test() {
		for(int i = 100; i< 111; i++) {
			Board board = new Board();
			board.setTitle(i+"번");
			board.setContent(i+"번 게시물");
			board.setWriter("아이디"+i);
			board.setFileCheck(0);
			boardDao.insert(board);
		}
	}
	
}
