package me.whiteship.demoinfleanrestapi.eventTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {
	
	// 가짜 요청을 dispachServlet에 보내고 가짜 응답을 받을 수 있도록 사용, 웹 서버를 띄우지 않음
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void createEvent() throws Exception {
		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON_UTF8) // contentType 설정 
				.accept(MediaTypes.HAL_JSON)
				).andExpect(status().isCreated());
		
	}
	
	
	
}
