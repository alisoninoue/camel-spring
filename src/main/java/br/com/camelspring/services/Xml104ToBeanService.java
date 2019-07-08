package br.com.camelspring.services;

import br.com.camelspring.bean.Actc104Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.routepolicy.quartz2.CronScheduledRoutePolicy;
import org.springframework.stereotype.Service;

@Service
public class Xml104ToBeanService extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
        startPolicy.setRouteStartTime("* 13 22 * * ?");
        startPolicy.setRouteStopTime("* 14 22 * * ?");

        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(true);
        jaxbDataFormat.setContextPath("br.com.camelspring.bean.actc104FromXsd");

/*        onException(Exception.class).
                handled(true).
                log("${body}").
                log("${exception}").
                log("${exception.message}").
                log("${exception.cause}").
                to("mock:error");*/

        from("file:arq104?" +
                "readLock=changed&" +
                "preMove=processing&" +
                "maxMessagesPerPoll=1&" +
                "move=successImport&" +
                "moveFailed=failImport").
//                transacted().
        unmarshal(jaxbDataFormat).
//                noAutoStartup().
//                routePolicy(startPolicy).
                bean(Actc104Processor.class, "testeJaxb").
                split().method(Actc104Processor.class, "testeSplit").

                log("ACTC104 - ${body}").
                to("activemq:pedidos");
//                to("mock:saida101RET");
    }
}
