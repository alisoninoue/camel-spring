package br.com.camelspring;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Boot {
	@Autowired
	private CamelContext context;

	@Autowired
	private Environment env;

	public static String ACTIVE_MQ = "activemq";

	@PostConstruct
	public void init() throws Exception {
		context.addComponent(ACTIVE_MQ, ActiveMQComponent.activeMQComponent(env.getProperty("activemq.host")));
	}

	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}
}
