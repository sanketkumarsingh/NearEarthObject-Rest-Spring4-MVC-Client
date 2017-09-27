package rest.nasa.neows.configuration.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import rest.nasa.neows.services.NeoService;
import rest.nasa.neows.services.impl.NeoServiceImpl;

@Configuration
@ComponentScan(basePackages = {
	"rest.nasa.neows.configuration",
	"rest.nasa.neows.services",
	"rest.nasa.neows.services.impl",
	"rest.nasa.neows.domain",
	"rest.nasa.neows.controller"
})
public class TestConfig {

	@Bean
	public NeoService getNeoService() {
		return new NeoServiceImpl();
	}
	
}
