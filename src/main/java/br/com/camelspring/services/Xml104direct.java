package br.com.camelspring.services;

import br.com.camelspring.bean.Actc104Processor;
import br.com.camelspring.bean.Player;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class Xml104direct extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Player.class);
        bindy.setLocale(Locale.US.getISO3Country());

        from("direct:xml104").
                routeId("continua104").
                split().method(Actc104Processor.class, "testeSplit").
                    wireTap("bean:logBean").
                    bean(Actc104Processor.class, "testeBindy").
                    //log("ACTC104 - ${body}").
                    marshal(bindy).
                    wireTap("bean:logBean").
                    //log("ACTC104 - ${body}").
                    to("activemq:retorno");
//                       to("mock:saida101RET");
    }
}
