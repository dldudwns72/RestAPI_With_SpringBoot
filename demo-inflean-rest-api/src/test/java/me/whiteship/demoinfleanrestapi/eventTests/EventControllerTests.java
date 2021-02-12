package me.whiteship.demoinfleanrestapi.eventTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.whiteship.demoinfleanrestapi.events.Event;
import me.whiteship.demoinfleanrestapi.events.EventRepository;

@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest // SpringBootApplication 에노테이션을 찾아서 해당 class 하위의 bean을 찾아 사용 할 수 있게 해준다.
@AutoConfigureMockMvc // dispatcher servlet을 이용한 테스트 방법, 실제 repository를 사용하여 테스트 가능
public class EventControllerTests {
	
	// 가짜 요청을 dispachServlet에 보내고 가짜 응답을 받을 수 있도록 사용, 웹 서버를 띄우지 않음
	@Autowired
	MockMvc mockMvc;
	
	// Jackson 라이브러리가 설정 되어있다면 자동으로 등록 가능
	@Autowired
	ObjectMapper objectMapper;
	
//	@MockBean // Repository Test를 위한 가짜 Bean 생성, Junit4에서는 Web에 관한 것만 빈으로 잡기 때문에 따로 설정 해줘야 함
//	EventRepository eventRepository;
	
	// 201을 던져줘야 하는데 404를 던져준다.
	@Test
	public void createEvent() throws Exception {
		Event event = Event.builder()
							.id(100)
							.name("Spring")
							.description("REST API")
							.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
							.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
							.beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
							.basePrice(100)
							.maxPrice(200)
							.limitOfEnrollment(100)
							.location("강남역 D2 스타텀 팩토리")
							.free(true)
							.offline(false)
							.build();
						
		
//		Mockito.when(eventRepository.save(event)).thenReturn(event);
									
		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON) // contentType 설정  요청 본문에 JSON을 넘겨 줄것이라는 선언
				.accept(MediaTypes.HAL_JSON) // 어떠한 응답(HAL_JSON)을 원한다
				.content(objectMapper.writeValueAsString(event)) // content 타입을 JSON으로 주어야 한다  writeValueAsString(객체) => 해당 객체를 JSON화 해서 보여준다.
				) // event 객체를 받아서 JSON화(Serializaion)을 해준다.
		.andDo(print()) // 실제 응답을 볼 수 있는 명령어
		.andExpect(status().isCreated()) // JSON 예상 응답 201
		.andExpect(jsonPath("id").exists()) // id 가 있는지 확인하는 테스트
		.andExpect(header().exists(HttpHeaders.LOCATION))
		.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
		.andExpect(jsonPath("id").value(Matchers.not(100))) // id 값이 100이면 안된다
		.andExpect(jsonPath("free").value(Matchers.not(true))) // 이 에러를 해결 하려면 DTO를 사용하여 해결
		
		;
		
	}
	
	
	
}
