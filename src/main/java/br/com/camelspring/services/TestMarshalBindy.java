package br.com.camelspring.services;

import br.com.camelspring.bean.Player;
import br.com.camelspring.bean.TesteBindyMarshal;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.springframework.stereotype.Service;

@Service
public class TestMarshalBindy extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        BindyFixedLengthDataFormat bindy = new BindyFixedLengthDataFormat(Player.class);
        from("sjms2:queue:teste")
                .bean(TesteBindyMarshal.class, "geraBindyNegative")
                .marshal(bindy)
                .log("${body}")
                .bean(TesteBindyMarshal.class, "geraBindyPositive")
                .marshal(bindy)
                .log("${body}")
                .bean(TesteBindyMarshal.class, "geraBindyWithout")
                .marshal(bindy)
                .log("${body}")
                .to("mock:teste");
    }
}
