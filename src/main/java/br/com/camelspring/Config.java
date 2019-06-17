package br.com.camelspring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.sjms.batch.SjmsBatchComponent;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.ConnectionFactory;

import static org.apache.camel.model.TransactedDefinition.PROPAGATION_REQUIRED;

@Configuration
@ComponentScan
public class Config {

    final CamelContext camelContext;

    @Value("${activemq.host}")
    String brokerURL;

    @Autowired
    public Config(final CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerURL);
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        final JmsTransactionManager transactionManager = new JmsTransactionManager();
        transactionManager.setConnectionFactory(connectionFactory);
        return transactionManager;
    }

/*    @Bean(name = "PROPAGATION_REQUIRED")
    public SpringTransactionPolicy springTransactionPolicy (JmsTransactionManager jmsTransactionManager){
        final SpringTransactionPolicy springTransactionPolicy = new SpringTransactionPolicy();
        springTransactionPolicy.setTransactionManager(jmsTransactionManager);
        springTransactionPolicy.setPropagationBehaviorName(PROPAGATION_REQUIRED);
        return springTransactionPolicy;
    }*/

    /*@Bean
    @Primary
    public PooledConnectionFactory pooledConnectionFactory(ConnectionFactory cf) {
        final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(1);
        pooledConnectionFactory.setConnectionFactory(cf);
        return pooledConnectionFactory;
    }*/

    @Bean(name = "activemq")
    @ConditionalOnClass(ActiveMQComponent.class)
    public ActiveMQComponent activeMQComponent(ConnectionFactory connectionFactory, JmsTransactionManager jmsTransactionManager) {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(connectionFactory);
//        activeMQComponent.setTransacted(true);
        activeMQComponent.setTransactionManager(jmsTransactionManager);
        activeMQComponent.setCacheLevel(DefaultMessageListenerContainer.CACHE_CONSUMER);
        activeMQComponent.setLazyCreateTransactionManager(false);
        return activeMQComponent;
    }

    @Bean(name = "sjms-batch")
    @ConditionalOnClass(SjmsBatchComponent.class)
    public SjmsBatchComponent sjmsBatchComponent(ConnectionFactory connectionFactory) {
        SjmsBatchComponent sjmsBatchComponent = new SjmsBatchComponent();
        sjmsBatchComponent.setConnectionFactory(connectionFactory);
        return sjmsBatchComponent;
    }
}
