package br.com.camelspring.services;

import org.springframework.stereotype.Service;

@Service
public class MqSjmsBatch extends BaseRouteBuilder {

    public static final String ROUTE_ID_MQSJMS = "queue-processor";

    @Override
    public void configure() throws Exception {

        super.configure();

        CustomCronScheduleRoutePolicy cronPolicy = new CustomCronScheduleRoutePolicy(getContext());
        cronPolicy.setRouteStartTime("* * * * * ?");
        cronPolicy.setRouteStopTime("* 59 23 * * ?");
        cronPolicy.setTimeZone("America/Sao_Paulo");


        from("{{mqsjms.from}}")
                .routeId(ROUTE_ID_MQSJMS)
                .noAutoStartup()
                .routePolicy(cronPolicy)
                .transacted()
                .wireTap("bean:logBean")
                //.bean(TestException.class, "testException")
                .to("{{mqsjms.split}}");
    }
}
