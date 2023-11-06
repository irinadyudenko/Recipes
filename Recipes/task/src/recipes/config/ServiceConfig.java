package recipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class ServiceConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}