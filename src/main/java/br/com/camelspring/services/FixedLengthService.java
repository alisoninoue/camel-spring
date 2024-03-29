package br.com.camelspring.services;

import br.com.camelspring.bean.Player;
import br.com.camelspring.processor.GravaTabelaProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FixedLengthService extends RouteBuilder {

    @Autowired
    private GravaTabelaProcessor gravaTabelaProcessor;

    @Override
    public void configure() throws Exception {

        onException(Exception.class).
                handled(true).
                log("${exception.message}").
                log("${exception.cause}").
                to("mock:error");

        BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Player.class);
        bindy.setLocale("default");

        from("file:fixedLength?delay=5s").
                id("fixedLengthOkRoute").
                unmarshal(bindy).
                log("${id} - ${body}").
                choice().
                    when(simple("${body?.matchesPlayed} == 140")).
                        process(gravaTabelaProcessor).
                        marshal().jacksonxml(true).
                        log("${body}").
                        setHeader(Exchange.FILE_NAME, simple("${file:name.noext}.xml")).
                        to("file:saida").
                    otherwise().
                        log("caiu no other").
                        to("mock:outro").
                        end();
    }
}
