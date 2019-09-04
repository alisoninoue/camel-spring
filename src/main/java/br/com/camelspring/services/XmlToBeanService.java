package br.com.camelspring.services;

import br.com.camelspring.processor.GravaTabelaProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XmlToBeanService extends RouteBuilder {

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

        from("file:aXmlFolder?delay=5&noop=true").
                routeId("xmlToBean").
                log("${id} - ${body}").
                choice().
                    when(xpath("/ACTCDOC")).
                        //unmarshal(jaxbDataFormat).
                        //jacksonxml(Actc101.class, Actc101.class, String.valueOf(JsonInclude.Include.NON_NULL), true).
                        //bean(Actc101Processor.class,"testeXpath").
                        //log("${body}").
                        to("mock:saidaXML").
                    otherwise().
                        //unmarshal(jaxbDataFormat).
                        //log("${body}").
                        //bean(Actc101Processor.class,"testeJaxb").
                        //log("caiu no other").
                        to("mock:outro").
                        end();
    }
}
