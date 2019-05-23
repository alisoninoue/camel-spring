package br.com.camelspring.services;

import br.com.camelspring.bean.Player;
import br.com.camelspring.processor.GravaTabelaProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.splunk.event.SplunkEvent;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class FixedLengthService extends RouteBuilder {

    @Autowired
    private GravaTabelaProcessor gravaTabelaProcessor;

    @Override
    public void configure() throws Exception {

        BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Player.class);
        bindy.setLocale((Locale.getDefault().getISO3Country()));

        from("file:fixedLength?delay=5s&noop=true").
                unmarshal(bindy).
                log("${id} - ${body}").
                marshal().jacksonxml(true).
                log("${body}").
                setHeader(Exchange.FILE_NAME, simple("${file:name.noext}.xml")).
                to("file:saida");
    }
}
