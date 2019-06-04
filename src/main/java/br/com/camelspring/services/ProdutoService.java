package br.com.camelspring.services;

import org.apache.camel.builder.RouteBuilder;

import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(Exception.class).
                handled(true).
                log("${exception.message}").
                to("mock:error");

        from("direct:pedidosFile").
                log("${body}").
                multicast().
                to("{{activemq.to}}").
                to("direct:saida");

        from("direct:saida").
                log("${id}").
                transform(simple("<xpto>${body}</xpto>")).
                log("${body}").
                setHeader("CamelFileName", simple("${file:name.noext}_novaTag.xml")).
        to("file:saida");
    }
}
