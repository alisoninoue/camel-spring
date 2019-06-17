package br.com.camelspring;

import org.apache.camel.component.sjms2.springboot.Sjms2ComponentAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication(exclude = {JmsAutoConfiguration.class, ActiveMQAutoConfiguration.class, Sjms2ComponentAutoConfiguration.class})
public class Boot {
/*	@Autowired
	private CamelContext context;

	@Value("${activemq.host}")
	String brokerURL;*/

    public static String ACTIVE_MQ = "activemq";

    @PostConstruct
    public void init() throws Exception {
		/*SimpleRegistry registry = new SimpleRegistry();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

		SjmsBatchComponent sjmsBatchComponent = new SjmsBatchComponent();
		sjmsBatchComponent.setConnectionFactory(connectionFactory);

		ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent(brokerURL);
		activeMQComponent.setTransactionManager(configJmsTransaction());
		activeMQComponent.setTransacted(true);
		activeMQComponent.setCacheLevelName(String.valueOf(CACHE_CONNECTION));

		context.addComponent(ACTIVE_MQ, activeMQComponent);
		context.addComponent("sjms-batch", sjmsBatchComponent);*/
    }

/*	@Bean
	public JmsTransactionManager configJmsTransaction(){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
		jmsTransactionManager.setConnectionFactory(connectionFactory);
		return jmsTransactionManager;
	}*/

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }
}
