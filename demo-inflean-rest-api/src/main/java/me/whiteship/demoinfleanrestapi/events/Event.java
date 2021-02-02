package me.whiteship.demoinfleanrestapi.events;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 무분별한 @DATA 사용은 지양 why? 다른 클래스와 호환 시 충돌 여부가 있을 수 있다.
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of="id")
public class Event {
	
	private Integer id;
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
	private boolean offline;
	private boolean free;
	@Enumerated(EnumType.STRING) // Enum의 Index 값 충돌 방지로 Type STRING 사용
	private EventStatus eventStatus;
	
	
	
}
