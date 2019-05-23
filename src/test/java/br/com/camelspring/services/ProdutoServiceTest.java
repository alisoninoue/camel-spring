package br.com.camelspring.services;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ProdutoServiceTest extends CamelTestSupport {


    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new ProdutoService();
    }


    @Test
    public void test() throws InterruptedException {

    }

}