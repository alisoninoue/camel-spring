package br.com.camelspring.services;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class JoinBodyAggregatorStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null){
            return newExchange;
        }
/*        else {
            try {
                throw new RuntimeException("Errooooo");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        String oldBody = oldExchange.getIn().getBody().toString();
        String newBody = newExchange.getIn().getBody().toString();
        oldExchange.getIn().setBody(oldBody + "\n" + newBody);

        return oldExchange;
    }
}
