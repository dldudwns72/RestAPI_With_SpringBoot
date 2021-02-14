package me.whiteship.demoinfleanrestapi.events;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

// Wrong Input에 의한 검증 Validaior class 생성
@Component
public class EventValidator {
	public void validate(EventDto eventDto, Errors errors) {
		if (eventDto.getMaxPrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
			// rejectValue("validator 할 변수명(field)","errorCode","errorMessage")
			errors.rejectValue("basePrice", "wrongValue", "BasePrice is wrong");
			errors.rejectValue("maxPrice", "wrongValue", "maxPrice is wrong");
		}

		LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
		if (endEventDateTime.isBefore(eventDto.getBeginEventDateTime())
				|| endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())
				|| endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
			errors.rejectValue("endEventDateTime", "wrongValue" , "Worng DateTime");
		}
		
		System.out.println("eventDto DateTime 확인 :"+eventDto.getEndEventDateTime());
		
		// TODO BeginEventDateTIme
		// TODO CloseEventDateTime

	}
}
