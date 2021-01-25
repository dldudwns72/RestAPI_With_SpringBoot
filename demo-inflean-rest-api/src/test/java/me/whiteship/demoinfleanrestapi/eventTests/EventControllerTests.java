package me.whiteship.demoinfleanrestapi.eventTests;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.whiteship.demoinfleanrestapi.events.Event;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTests {
	
	// 가짜 요청을 dispachServlet에 보내고 가짜 응답을 받을 수 있도록 사용, 웹 서버를 띄우지 않음
	@Autowired
	MockMvc mockMvc;
	
	// Jackson 라이브러리가 설정 되어있다면 자동으로 등록 가능
	@Autowired
	ObjectMapper objectMapper;
	
	// 201을 던져줘야 하는데 404를 던져준다.
	@Test
	public void createEvent() throws Exception {
		Event event = Event.builder()
							.name("Spring")
							.description("REST API")
							.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
							.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
							.beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
							.basePrice(100)
							.maxPrice(200)
							.limitOfEnrollment(100)
							.location("강남역 D2 스타텀 팩토리")
							.build();
									
		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON_UTF8) // contentType 설정 
				.accept(MediaTypes.HAL_JSON)
				.content(objectMapper.writeValueAsString(event))) // event 객체를 받아서 JSON화(Serializaion)을 해준다.
		.andDo(print())
		.andExpect(status().isCreated()) // JSON 예상 응답 201
		.andExpect((ResultMatcher) jsonPath("id").exists())
		
		;
		
	}
	
	
	
}
