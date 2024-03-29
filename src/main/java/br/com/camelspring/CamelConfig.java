package br.com.camelspring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.sjms.batch.SjmsBatchComponent;
import org.apache.camel.component.sjms2.Sjms2Component;
import org.apache.camel.processor.idempotent.jpa.JpaMessageIdRepository;
import org.apache.camel.processor.interceptor.BacklogTracer;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.persistence.EntityManagerFactory;

import static org.apache.camel.model.TransactedDefinition.PROPAGATION_REQUIRED;

@Configuration
@ComponentScan
@EntityScan({"org.apache.camel.processor.idempotent.jpa", "org.apache.camel.processor.interceptor.jpa", "br.com.camelspring.entity"})
public class CamelConfig {

    final CamelContext camelContext;

    @Value("${activemq.host}")
    String brokerURL;

    @Autowired
    public CamelConfig(final CamelContext camelContext) {
        this.camelContext = camelContext;
        this.camelContext.setTracing(false);
        this.camelContext.setUseMDCLogging(true);

    }

    /*@Bean
    public Config hazelCastConfig(){
        Config config = new Config();
        config.setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName("configuration")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(-1));


        return config;
    }

    @Bean(name = "myRepo")
    public HazelcastIdempotentRepository hazelcastIdempotentRepository(Config config){
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
        return new HazelcastIdempotentRepository(hz,"camel");
    }*/

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerURL);
        return activeMQConnectionFactory;
    }

/*    @Bean
    public JmsTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        final JmsTransactionManager transactionManager = new JmsTransactionManager();
        transactionManager.setConnectionFactory(connectionFactory);
        return transactionManager;
    }

    @Bean(name = PROPAGATION_REQUIRED)
    public SpringTransactionPolicy springTransactionPolicy(JmsTransactionManager jmsTransactionManager) {
        final SpringTransactionPolicy springTransactionPolicy = new SpringTransactionPolicy();
        springTransactionPolicy.setTransactionManager(jmsTransactionManager);
        springTransactionPolicy.setPropagationBehaviorName(PROPAGATION_REQUIRED);
        return springTransactionPolicy;
    }*/

    /*@Bean(name = PROPAGATION_REQUIRED_NEW)
    public SpringTransactionPolicy springTransactionPolicyNew (JmsTransactionManager jmsTransactionManager){
        final SpringTransactionPolicy springTransactionPolicy = new SpringTransactionPolicy();
        springTransactionPolicy.setTransactionManager(jmsTransactionManager);
        springTransactionPolicy.setPropagationBehaviorName(PROPAGATION_REQUIRED_NEW);
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

    @Bean(name = "testeIdempotent")
    public JpaMessageIdRepository jpa(EntityManagerFactory entityManagerFactory) {
        JpaMessageIdRepository jpaMessageIdRepository = new JpaMessageIdRepository(entityManagerFactory, "FileConsumer");
        return jpaMessageIdRepository;
    }


    @Bean(name = "activemq")
    @ConditionalOnClass(ActiveMQComponent.class)
    public ActiveMQComponent activeMQComponent(ConnectionFactory connectionFactory) {
//            , JmsTransactionManager jmsTransactionManager) {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConnectionFactory(connectionFactory);
//        activeMQComponent.setTransacted(true);
//        activeMQComponent.setTransactionManager(jmsTransactionManager);
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

    @Bean(name = "sjms2")
    @ConditionalOnClass(Sjms2Component.class)
    public Sjms2Component sjms2Component(ConnectionFactory connectionFactory) {
        Sjms2Component sjms2Component = new Sjms2Component();
        sjms2Component.setConnectionFactory(connectionFactory);
        return sjms2Component;
    }
}
