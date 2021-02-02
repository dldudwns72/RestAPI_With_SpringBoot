package me.whiteship.demoinfleanrestapi.events;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo 강의에서 사용
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.net.URI;


@Controller
@RequestMapping(value = "/api/events/", produces = MediaTypes.HAL_JSON_VALUE) //,produces = MediaTypes.HAL_JSON 
public class EventController {
	
	private final EventRepository eventRepository;
	
	public EventController(EventRepository eventRepository) {
		// 생성자가 하나만 있고 생성자로 받아올 파라미터가 이미 Bean으로 등록되어있다면 Autowire 생략 가능 Spring 4.X 이상부터 
		this.eventRepository = eventRepository;
	}
	
	@PostMapping
	public ResponseEntity createEvent(@RequestBody Event event) {
		Event newEvent = this.eventRepository.save(event);
		//URI 제공 시HATEOS가 제공하는 linkTo(), MethodOn 사용
		URI createdURI = linkTo(EventController.class).slash(newEvent.getId()).toUri();
//		event.setId(10); 
		return ResponseEntity.created(createdURI).body(event); // build() 해주는 이유는? -> createURI 헤더를 가지고 201응답을 줄 수 있다.
	}
	
}
