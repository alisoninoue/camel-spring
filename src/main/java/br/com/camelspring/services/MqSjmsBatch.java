package br.com.camelspring.services;

import br.com.camelspring.bean.TestException;
import org.springframework.stereotype.Service;

@Service
public class MqSjmsBatch extends BaseRouteBuilder {

    @Override
    public void configure() throws Exception {

        super.configure();
        String routeId = "queue-processor";

/*        onException(Exception.class).
                handled(true).
                log("${exception.message}").
                to("mock:error");*/

        from("sjms-batch:pedidos?aggregationStrategy=#joinBodyAggregatorStrategy&completionSize=3&completionTimeout=1000")
                .transacted()
                .routeId(routeId)
                .log("${body}")
                .bean(TestException.class, "testException")
                .to("mock:testequeue")
        ;
    }
}
