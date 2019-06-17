package br.com.camelspring.services;

import br.com.camelspring.bean.Actc104Processor;
import br.com.camelspring.processor.GravaTabelaProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Xml104ToBeanService extends RouteBuilder {

    @Override
    public void configure() throws Exception {

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
                bean(Actc104Processor.class, "testeJaxb").
                log("ACTC104 - ${body}").
                to("mock:saida101RET");
    }
}
