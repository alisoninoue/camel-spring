package br.com.camelspring.services;

import br.com.camelspring.bean.Actc101Processor;
import br.com.camelspring.bean.Player;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
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

        from("{{mqsjms.split}}")
                .routeId(ROUTE_ID_SPLIT)
                .split(body().tokenize("\n"), new JoinBindyAggregationStrategy())
                        .unmarshal(bindy)
                    .log("${body}")
                .end()
                .log("after split: ${body}")
                .bean(Actc101Processor.class, "processa")
                .to("mock:saidaSplit");
    }
}
