package br.com.camelspring.services;

import br.com.camelspring.bean.Player;
import br.com.camelspring.processor.GravaTabelaProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.splunk.event.SplunkEvent;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProdutoFromQueueService extends SpringRouteBuilder {

    @Autowired
    private GravaTabelaProcessor gravaTabelaProcessor;

    @Override
    public void configure() throws Exception {

        BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Player.class);
        bindy.setLocale((Locale.getDefault().getISO3Country()));

/*        onException(Exception.class).
                handled(true).
                log("${exception.message}").
                to("mock:error");*/

        from("{{activemq.from.fake}}").
//        from("activemq:queue:pedidos").
                transacted().
                log("${body}").
                unmarshal(bindy).
                multicast().
                to("direct:splunk").
                to("direct:filesaida").
                end();

        from("direct:filesaida").
                log("${body}").
                process(gravaTabelaProcessor).
                marshal().jacksonxml(true).
                log("${body}").
                setHeader("CamelFileName", simple("ACTC101.xml")).
                to("file:saida");

        from("direct:splunk").
                convertBodyTo(SplunkEvent.class).
                 to("splunk://submit?host={{splunk.host}}&port={{splunk.port}}"
                + "&username={{splunk.username}}&password={{splunk.password}}&index=camel-index&sourceType=camel-type&source=camel-player");

    }
}
