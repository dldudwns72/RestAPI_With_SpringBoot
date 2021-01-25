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
@RequestMapping(value = "/api/events") //,produces = MediaTypes.HAL_JSON 
public class EventController {
	
	@PostMapping
	public ResponseEntity createEvent(@RequestBody Event event) {
		//URI 제공 시HATEOS가 제공하는 linkTo(), MethodOn 사용
		URI createdURI = linkTo(EventController.class).slash("{id}").toUri();
//		event.setId(10); // ID가 설정되지 않는 이유 확인
		return ResponseEntity.created(createdURI).body(event); // build() 해주는 이유는? -> createURI 헤더를 가지고 201응답을 줄 수 있다.
	}
	
}
