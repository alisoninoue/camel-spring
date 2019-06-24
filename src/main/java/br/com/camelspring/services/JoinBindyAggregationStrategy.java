package br.com.camelspring.services;

import br.com.camelspring.bean.Player;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JoinBindyAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        List<Player> playerList = new ArrayList<>();
        if(newExchange.getException() != null){
            return oldExchange;
        }

        if (oldExchange == null){
            Object newBody = newExchange.getIn().getBody();
            if (newBody instanceof Player)
                playerList.add((Player) newBody);
            newExchange.getIn().setBody(playerList);
            return newExchange;
        }



        List<Player> oldBody = (List<Player>) oldExchange.getIn().getBody();
        Object newBody = newExchange.getIn().getBody();
        if (newBody instanceof Player)
            oldBody.add((Player) newBody);
        oldExchange.getIn().setBody(oldBody);

        return oldExchange;
    }
}
