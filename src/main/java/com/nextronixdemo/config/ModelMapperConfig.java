package com.nextronixdemo.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelmapper() {
		
		ModelMapper mapper = new ModelMapper();
		
		mapper.getConfiguration()
		       .setMatchingStrategy(MatchingStrategies.STRICT)
		       .setAmbiguityIgnored(true);
     return mapper;		
	}

}
