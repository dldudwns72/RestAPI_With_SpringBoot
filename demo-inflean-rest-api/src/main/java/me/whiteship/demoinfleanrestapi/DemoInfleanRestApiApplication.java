package me.whiteship.demoinfleanrestapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoInfleanRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoInfleanRestApiApplication.class, args);
	}
	
	
	// Bean으로 등록 시 해당 객체를 꺼내 사용할 수 있다.
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
