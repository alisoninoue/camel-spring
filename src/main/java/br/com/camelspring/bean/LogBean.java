package br.com.camelspring.bean;

import br.com.camelspring.bean.actc104FromXsd.GrupoACTC104PortlddComplexType;
import br.com.camelspring.entity.Log;
import br.com.camelspring.repository.LogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LogBean {

    private LogRepository repository;

    private static final Logger logger = LogManager.getLogger(LogBean.class);

    public LogBean(LogRepository repository) {
        this.repository = repository;
    }

    public void gravaLog(Exchange exchange, @Body String bodyMessage) {
        Message message = exchange.getIn();
        String exchangeId = exchange.getExchangeId();
        String breadcrumbId = message.getHeader("breadcrumbId", String.class);
        String action = message.getHeader("action", String.class);
        String contrato = null;

        Object body = message.getBody();
        if (body instanceof GrupoACTC104PortlddComplexType) {
            contrato = ((GrupoACTC104PortlddComplexType) body).getNUPortlddCTC();
        }

        String fromRouteId1 = exchange.getUnitOfWork().getRouteContext().getRoute().getId();
        String fileName = message.getHeader(Exchange.FILE_NAME, String.class);

        Log log = new Log(1, fromRouteId1, breadcrumbId, contrato, bodyMessage, LocalDateTime.now(), contrato, fileName);

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
