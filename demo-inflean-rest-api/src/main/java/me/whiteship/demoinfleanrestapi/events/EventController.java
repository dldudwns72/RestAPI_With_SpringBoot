package me.whiteship.demoinfleanrestapi.events;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo 강의에서 사용 버전 업되서 WebMvcLinkBuilder 사용하면 된다.
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.net.URI;


@Controller
@RequestMapping(value = "/api/events/", produces = MediaTypes.HAL_JSON_VALUE) //,produces = MediaTypes.HAL_JSON 
public class EventController {
	
	private final EventRepository eventRepository;
	
	private final ModelMapper modelMapper;
	
	public EventController(EventRepository eventRepository, ModelMapper modelMapper) {
		// 생성자가 하나만 있고 생성자로 받아올 파라미터가 이미 Bean으로 등록되어있다면 Autowired 생략 가능 Spring 4.X 이상부터 
		this.eventRepository = eventRepository;
		this.modelMapper = modelMapper;
	}
	
	// 상위 코드는 @Autowired EventRepository eventRepository 와 동일한 코드
	
	@PostMapping
	public ResponseEntity createEvent(@RequestBody EventDto eventDto) {
//		URI createdUri = linkTo(methodOn(EventController.class).createEvent()).slash("{id}").toUri();
//		return ResponseEntity.created(createdUri).build();
		// 상위 코드 201 응답을 만들기 위한 방법
		
		// 기존 대로라면 Event event = Event.builder()안에 값 다 설정해 주고 Dto에 값을 넣어줘야 하지만 ModelMapper 사용으로 DTO를 도메인 객체로 값 을 복사해준다. maven 설정 필요
		Event event = modelMapper.map(eventDto, Event.class);
		
		Event newEvent = this.eventRepository.save(event);
		URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
		return ResponseEntity.created(createdUri).body(event);
		
		
//		Event newEvent = this.eventRepository.save(event);
		//URI 제공 시HATEOS가 제공하는 linkTo(), MethodOn 사용
//		URI createdURI = linkTo(EventController.class).slash(newEvent.getId()).toUri();
//		event.setId(10); 
//		return ResponseEntity.created(createdURI).body(event); // build() 해주는 이유는? -> createURI 헤더를 가지고 201응답을 줄 수 있다.
	
		
	}
	
}
