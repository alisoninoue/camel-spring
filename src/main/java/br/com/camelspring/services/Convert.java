package br.com.camelspring.services;

import br.com.camelspring.bean.Player;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.apache.camel.component.splunk.event.SplunkEvent;
import org.springframework.stereotype.Component;

@Component
public class Convert implements TypeConverters {

    @Converter
    public static SplunkEvent test(Player player) {
        SplunkEvent data = new SplunkEvent("actc101-message", null);

        data.addPair("player_name", player.getName());
        data.addPair("player_address", player.getAddress());


        return data;
    }
}
