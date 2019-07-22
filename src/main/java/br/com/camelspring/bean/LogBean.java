package br.com.camelspring.bean;

import br.com.camelspring.bean.actc104FromXsd.GrupoACTC104PortlddComplexType;
import br.com.camelspring.entity.Log;
import br.com.camelspring.repository.LogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LogBean {

    @Autowired
    public LogRepository repository;

    Logger logger = LoggerFactory.getLogger(LogBean.class);

    public void gravaLog(Exchange exchange, @Body String bodyMessage) {
        Message message = exchange.getIn();
        String exchangeCorrelationId = message.getHeader("exchangeCorrelationId", String.class);
        String action = message.getHeader("action", String.class);
        String contrato = null;

        Object body = message.getBody();
        if (body instanceof GrupoACTC104PortlddComplexType) {
            contrato = ((GrupoACTC104PortlddComplexType) body).getNUPortlddCTC();
        }

        String fromRouteId1 = exchange.getUnitOfWork().getRouteContext().getRoute().getId();
        String fileName = message.getHeader(Exchange.FILE_NAME, String.class);

        Log log = new Log(1, fromRouteId1, exchangeCorrelationId, contrato, bodyMessage, LocalDateTime.now(), contrato, fileName);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(log);
            logger.info(json);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }

        repository.save(log);
    }
}
