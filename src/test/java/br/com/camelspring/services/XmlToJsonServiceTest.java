package br.com.camelspring.services;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
@Ignore
public class XmlToJsonServiceTest extends CamelTestSupport {
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new XmlToJsonService();
    }


    @Test
    public void test() throws InterruptedException {

    }
}