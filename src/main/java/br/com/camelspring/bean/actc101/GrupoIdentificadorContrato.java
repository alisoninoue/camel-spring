package br.com.camelspring.bean.actc101;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Grupo_ACTC101_IdentdContrto")
@JsonPropertyOrder({"codContrtoOr", "cnpjBaseIFOrContrto", "tpContrto", "tpEnteCons", "cnpjCorrespBanc"})
public class GrupoIdentificadorContrato {

    @JacksonXmlProperty(localName = "CodContrtoOr")
    private Integer codContrtoOr;

    @JacksonXmlProperty(localName = "CNPJBase_IFOrContrto")
    private Integer cnpjBaseIFOrContrto;
    private Integer tpContrto;
    private Integer tpEnteCons;
    private Integer cnpjCorrespBanc;

    public Integer getCodContrtoOr() {
        return codContrtoOr;
    }

    public void setCodContrtoOr(Integer codContrtoOr) {
        this.codContrtoOr = codContrtoOr;
    }

    public Integer getCnpjBaseIFOrContrto() {
        return cnpjBaseIFOrContrto;
    }

    public void setCnpjBaseIFOrContrto(Integer cnpjBaseIFOrContrto) {
        this.cnpjBaseIFOrContrto = cnpjBaseIFOrContrto;
    }

    public Integer getTpContrto() {
        return tpContrto;
    }

    public void setTpContrto(Integer tpContrto) {
        this.tpContrto = tpContrto;
    }

    public Integer getTpEnteCons() {
        return tpEnteCons;
    }

    public void setTpEnteCons(Integer tpEnteCons) {
        this.tpEnteCons = tpEnteCons;
    }

    public Integer getCnpjCorrespBanc() {
        return cnpjCorrespBanc;
    }

    public void setCnpjCorrespBanc(Integer cnpjCorrespBanc) {
        this.cnpjCorrespBanc = cnpjCorrespBanc;
    }
}
