package br.com.camelspring.services;

import br.com.camelspring.bean.LogBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class Audit extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:audit")
                .routeId("logAudit")
                .bean(LogBean.class, "gravaLog")
                .to("jpa:br.com.camelspring.entity.Log");
    }
}
