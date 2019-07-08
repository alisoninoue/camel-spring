package br.com.camelspring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer status;

    private String exchangeId;

    private String nroContrato;

    private String message;

    private LocalDateTime dataInclusao;

    /**
     * @Deprecated Somente para Framework
     */
    @Deprecated
    private Log() {
    }

    public Log(Integer status, String exchangeId, String nroContrato, String message, LocalDateTime dataInclusao) {
        this.status = status;
        this.exchangeId = exchangeId;
        this.nroContrato = nroContrato;
        this.message = message;
        this.dataInclusao = dataInclusao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nroContrato) {
        this.nroContrato = nroContrato;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDateTime dataInclusao) {
        this.dataInclusao = dataInclusao;
    }
}
