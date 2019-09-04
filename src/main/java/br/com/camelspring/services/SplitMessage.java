package br.com.camelspring.services;

import br.com.camelspring.bean.Actc101Processor;
import br.com.camelspring.bean.Player;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Service;


@Service
public class SplitMessage extends BaseRouteBuilder {

    private static final String ROUTE_ID_SPLIT = "split-message";

    @Override
    public void configure() throws Exception {

        super.configure();

        onException(IllegalArgumentException.class).
//                handled(true).
                continued(true).
                log("${body}").
                log("${exception.message}").
                log("${exception.cause}").
                to("mock:error");

        BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Player.class);
        bindy.setLocale("default");


        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(true);
        jaxbDataFormat.setContextPath("br.com.camelspring.bean.actc101FromXsd");


        from("{{mqsjms.split}}")
                .routeId(ROUTE_ID_SPLIT)
                .split(body().tokenize("\n"), new JoinBindyAggregationStrategy())
                        .wireTap("bean:logBean")
                        .unmarshal(bindy)
                    .end()
                .end()
                .wireTap("bean:logBean")
                .bean(Actc101Processor.class, "processa")
                .marshal(jaxbDataFormat)
                .setHeader("CamelFileName", simple("MP3_${bean:fileIdBean?method=generateId}_{{cnpj.xpto}}_${header.breadcrumbid.replaceAll(\"[^a-zA-Z0-9_-]\", \"\")}_${id}.xml"))
                .wireTap("bean:logBean")
                .to("file:saida");
    }
}
