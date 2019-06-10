package br.com.camelspring.services;

import br.com.camelspring.bean.Actc101Processor;
import br.com.camelspring.processor.GravaTabelaProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Xml101ToBeanService extends RouteBuilder {

    public static final String ACTC_101_XSD = "http://www.cip-bancos.org.br/ARQ/ACTC101.xsd";
    @Autowired
    private GravaTabelaProcessor gravaTabelaProcessor;

    @Override
    public void configure() throws Exception {

        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(true);
        jaxbDataFormat.setContextPath("br.com.camelspring.bean.actc101FromXsd");

        onException(Exception.class).
                handled(true).
                log("${body}").
                log("${exception}").
                log("${exception.message}").
                log("${exception.cause}").
                to("mock:error");

        from("file:arq101?delay=5&noop=true").
                log("${id} - ${body}").
                choice().
                    when(xpath("//c:ACTC101RET or //c:ACTC101").namespace("c", ACTC_101_XSD)).
                        unmarshal(jaxbDataFormat).
                        bean(Actc101Processor.class,"testeJaxb").
                        log("ACTC101 - ${body}").
                        to("mock:saida101RET").
                    otherwise().
                        log("caiu no other").
                        to("mock:outro101RET").
                        end();
    }
}
