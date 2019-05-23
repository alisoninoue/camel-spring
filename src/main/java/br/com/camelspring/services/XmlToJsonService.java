package br.com.camelspring.services;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;
import org.springframework.stereotype.Service;

@Service
public class XmlToJsonService extends RouteBuilder {
    @Override
    public void configure() throws Exception {

    }
}
