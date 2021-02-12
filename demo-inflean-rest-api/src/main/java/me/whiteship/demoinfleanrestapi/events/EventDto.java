package me.whiteship.demoinfleanrestapi.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class EventDto {
	private String name; 
	private String description; 
	private	LocalDateTime beginEnrollmentDateTime;
	private	LocalDateTime closeEnrollmentDateTime; 
	private	LocalDateTime beginEventDateTime; 
	private LocalDateTime endEventDateTime; 
	private String location; // (optional)
	private int basePrice; //   (optional) 이게 없으면 온라인 모임 , 등록비
	private int maxPrice; // (optional) 
	private int	limitOfEnrollment;
}
