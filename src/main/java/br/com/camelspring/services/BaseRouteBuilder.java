package br.com.camelspring.services;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.component.jms.JmsMessageType;
import org.apache.camel.spring.SpringRouteBuilder;

public abstract class BaseRouteBuilder extends SpringRouteBuilder {

    public void configure() throws Exception {
        errorHandler(transactionErrorHandler()
                .redeliveryDelay(500L)
                .logHandled(true)
                .maximumRedeliveries(2)
                .backOffMultiplier(2)
                .useExponentialBackOff()
                .retryAttemptedLogLevel(LoggingLevel.WARN)
                .retriesExhaustedLogLevel(LoggingLevel.DEBUG)
                .logExhaustedMessageBody(true)
                .logExhaustedMessageHistory(true)
                .useOriginalMessage()
                .log("Erro!")
                .onPrepareFailure(exchange -> {
                    Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    System.out.println(cause);
                    exchange.getIn().setHeader("FailedBecause", cause.getMessage());
                    exchange.getIn().setHeader("CamelJmsMessageType", JmsMessageType.Text);
                    exchange.getIn().setHeader("MifirMessageType", "Error");
                }));
    }
}