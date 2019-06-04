package br.com.camelspring.services;

import br.com.camelspring.bean.Player;
import br.com.camelspring.bean.actc101.Actc101;
import br.com.camelspring.bean.actc101.Actc101Processor;
import br.com.camelspring.processor.GravaTabelaProcessor;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlToBeanService extends RouteBuilder {

    @Autowired
    private GravaTabelaProcessor gravaTabelaProcessor;

    @Override
    public void configure() throws Exception {

        onException(Exception.class).
                handled(true).
                log("${body}").
                log("${exception}").
                log("${exception.message}").
                log("${exception.cause}").
                to("mock:error");

        from("file:aXmlFolder?delay=5&noop=true").
                log("${id} - ${body}").
                choice().
                    when(xpath("/ACTC101")).
                        unmarshal().
                        jacksonxml(Actc101.class, Actc101.class, String.valueOf(JsonInclude.Include.NON_NULL), true).
                        bean(Actc101Processor.class,"teste").
                        log("${body}").
                        to("mock:saidaXML").
                    otherwise().
                        log("caiu no other").
                        to("mock:outro").
                        end();
    }
}
