package br.com.camelspring.bean;

import br.com.camelspring.entity.Log;
import br.com.camelspring.repository.LogRepository;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LogBean {

    @Autowired
    public LogRepository repository;

    public void gravaLog(Exchange exchange, Integer status){
        Log log = new Log(status, exchange.getExchangeId(), "contrato", (String) exchange.getIn().getBody(), LocalDateTime.now());
        repository.save(log);
    }
}
