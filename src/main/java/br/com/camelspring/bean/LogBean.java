package br.com.camelspring.bean;

import br.com.camelspring.bean.actc104FromXsd.GrupoACTC104PortlddComplexType;
import br.com.camelspring.entity.Log;
import br.com.camelspring.repository.LogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

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
            contrato = message.getBody(GrupoACTC104PortlddComplexType.class).getNUPortlddCTC();
        }

        String fromRouteId1 = exchange.getUnitOfWork().getRouteContext().getRoute().getId();
        String fileName = message.getHeader(Exchange.FILE_NAME, String.class);

        Log log = new Log(1, fromRouteId1, breadcrumbId, contrato, bodyMessage, LocalDateTime.now(), contrato, fileName);
        logger.info(new ObjectMessage(log));
        /*ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(log);
            logger.info(json);
            Map<String, Object> newMap = new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {});
            logger.warn(new ObjectMessage(newMap));

        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }*/

        repository.save(log);
    }
}
