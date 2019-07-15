package br.com.camelspring.services;

import br.com.camelspring.bean.Actc104Processor;
import br.com.camelspring.bean.Player;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.routepolicy.quartz2.CronScheduledRoutePolicy;
import org.springframework.stereotype.Service;

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

        BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Player.class);
        bindy.setLocale(Locale.US.getISO3Country());

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
                    bean(Actc104Processor.class, "testeBindy").
                    log("ACTC104 - ${body}").
                    marshal(bindy).
                    log("ACTC104 - ${body}").
                    to("activemq:retorno");
//                    to("mock:saida101RET");
    }
}
