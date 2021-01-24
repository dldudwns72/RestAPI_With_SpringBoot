package me.whiteship.demoinfleanrestapi.eventTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import me.whiteship.demoinfleanrestapi.events.Event;

// 전체 테스트 시 클래스 명 선택 후 ctrl + f11 단축키
public class EventTest {
	
	// 메소드명 선택 후 ctrl + f11 단축키
	@Test
	public void builder() {
		Event event = Event.builder()
						.name("Spring Rest API")
						.description("With Back")
						.build();
		
		assertThat(event).isNotNull();
	}
	
	@Test
	public void javaBean() {
		String name = "Event";
		String description = "Spring Boot";
		
		Event event = new Event();
		event.setName(name);
		event.setDescription(description);
	
		assertThat(event.getName()).isEqualTo(name);	
		assertThat(event.getDescription()).isEqualTo(description);
	}
}
