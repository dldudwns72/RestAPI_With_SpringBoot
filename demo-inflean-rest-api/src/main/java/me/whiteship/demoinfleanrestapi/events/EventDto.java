package me.whiteship.demoinfleanrestapi.events;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class EventDto {
	
	// validation 사용 시 해당 객체 앞에 @Valid 설정 시 유효성 검사 애노테이션 설정이 가능하다.
	
	@NotEmpty
	private String name; 
	@NotEmpty
	private String description; 
	@NotNull
	private	LocalDateTime beginEnrollmentDateTime;
	@NotNull
	private	LocalDateTime closeEnrollmentDateTime; 
	@NotNull
	private	LocalDateTime beginEventDateTime; 
	@NotNull
	private LocalDateTime endEventDateTime; 
	
	private String location; // (optional)
	
	@Min(0)
	private int basePrice; //   (optional) 이게 없으면 온라인 모임 , 등록비
	@Min(0)
	private int maxPrice; // (optional) 
	@Min(0)
	private int	limitOfEnrollment;
}
