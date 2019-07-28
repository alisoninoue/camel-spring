package br.com.camelspring.services;

import br.com.camelspring.bean.Actc104Processor;
import br.com.camelspring.bean.Player;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.routepolicy.quartz2.CronScheduledRoutePolicy;
import org.springframework.stereotype.Service;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.util.Locale;

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
                routeId("104xml").
//                setHeader("exchangeCorrelationId", simple("${id}")).
                wireTap("bean:logBean").newExchangeHeader("action",constant("ACTC104 - Inicio")).
                unmarshal(jaxbDataFormat).
                wireTap("bean:logBean").
//                noAutoStartup().
//                routePolicy(startPolicy).
//                bean(Actc104Processor.class, "testeJaxb").
                to("direct:xml104");

    }
}
