package br.com.camelspring.services;

import org.apache.camel.routepolicy.quartz2.CronScheduledRoutePolicy;
import org.springframework.stereotype.Service;

import static org.apache.camel.model.TransactedDefinition.PROPAGATION_REQUIRED;

@Service
public class MqSjmsBatch extends BaseRouteBuilder {

    public final static String ROUTE_ID_MQSJMS = "queue-processor";

    @Override
    public void configure() throws Exception {

        super.configure();

        CronScheduledRoutePolicy cronPolicy = new CronScheduledRoutePolicy();
        cronPolicy.setRouteStartTime("* 20 12 * * ?");
        cronPolicy.setRouteStopTime("*  12 * * ?");


        from("{{mqsjms.from}}")
                .routeId(ROUTE_ID_MQSJMS)
//                .noAutoStartup()
//                .routePolicy(cronPolicy)
                .transacted(PROPAGATION_REQUIRED)
                .log("${body}")
                //.bean(TestException.class, "testException")
                .to("{{mqsjms.split}}")
        ;
    }
}
