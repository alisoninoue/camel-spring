package br.com.camelspring.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer status;

    @Column(length = 100)
    private String routeId;

    @Column(length = 100)
    private String exchangeId;

    private String nroContrato;

    @Column(length = 50000)
    private String message;

    private LocalDateTime dataInclusao;

    private String nroPortabilidadeCTC;

    @Column(length = 100)
    private String nomeArquivo;

    /**
     * @Deprecated Somente para Framework
     */
    @Deprecated
    private Log() {
    }

    public Log(@NotNull Integer status, String routeId, String exchangeId, String nroContrato, String message, LocalDateTime dataInclusao) {
        this.status = status;
        this.routeId = routeId;
        this.exchangeId = exchangeId;
        this.nroContrato = nroContrato;
        this.message = message;
        this.dataInclusao = dataInclusao;
    }

    public Log(Integer status, String exchangeId, String nroContrato, String message, LocalDateTime dataInclusao) {
        this.status = status;
        this.exchangeId = exchangeId;
        this.nroContrato = nroContrato;
        this.message = message;
        this.dataInclusao = dataInclusao;
    }

    public Log(@NotNull Integer status, String routeId, String exchangeId, String nroContrato, String message, LocalDateTime dataInclusao, String nroPortabilidadeCTC, String nomeArquivo) {
        this.status = status;
        this.routeId = routeId;
        this.exchangeId = exchangeId;
        this.nroContrato = nroContrato;
        this.message = message;
        this.dataInclusao = dataInclusao;
        this.nroPortabilidadeCTC = nroPortabilidadeCTC;
        this.nomeArquivo = nomeArquivo;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
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

    public String getNroPortabilidadeCTC() {
        return nroPortabilidadeCTC;
    }

    public void setNroPortabilidadeCTC(String nroPortabilidadeCTC) {
        this.nroPortabilidadeCTC = nroPortabilidadeCTC;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
}
