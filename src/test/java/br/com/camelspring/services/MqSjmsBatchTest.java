package br.com.camelspring.services;

import br.com.camelspring.CamelConfig;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.DisableJmx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static br.com.camelspring.services.MqSjmsBatch.ROUTE_ID_MQSJMS;

@ActiveProfiles(value = "test")
@DisableJmx
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = {CamelConfig.class},
        properties = {"camel.springboot.java-routes-include-pattern=**/MqSjmsBatch*"})
public class MqSjmsBatchTest {
    @Autowired
    private CamelContext context;

    @EndpointInject(uri = "{{mqsjms.from}}")
    private ProducerTemplate template;

    @EndpointInject(uri = "{{mqsjms.split}}")
    private MockEndpoint mock;

    @Test
    public void shouldReceiveMessage() throws Exception {

        NotifyBuilder notify = new NotifyBuilder(context).whenFailed(1).or().whenDone(1).create();

        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("Camel rocks");

        template.sendBody("Camel rocks");

        notify.matches(1, TimeUnit.SECONDS);
        mock.assertIsSatisfied();
    }

    @Test
    public void simulateErrorBeforeSendingMessage() throws Exception {

        NotifyBuilder notify = new NotifyBuilder(context).whenFailed(1).or().whenDone(1).create();

        RouteDefinition route = context.getRouteDefinition(ROUTE_ID_MQSJMS);
        // advice the route by enriching it with the route builder where
        // we add a couple of interceptors to help simulate the error
        route.adviceWith(context, new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // intercept sending to http and detour to our processor instead
                interceptSendToEndpoint("{{mqsjms.split}}")
                        // skip sending to the real http when the detour ends
                        .process(exchange -> exchange.setException(new Exception("Simulated error")));
            }
        });

        mock.expectedMessageCount(0);
        template.sendBody("Camel rocks");

        notify.matches(1, TimeUnit.SECONDS);
        mock.assertIsSatisfied();
    }

}