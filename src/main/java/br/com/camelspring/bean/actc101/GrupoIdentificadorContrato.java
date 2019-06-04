package br.com.camelspring.bean.actc101;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({"codContrtoOr", "cnpjBaseIFOrContrto", "tpContrto", "tpEnteCons", "cnpjCorrespBanc"})
public class GrupoIdentificadorContrato {

    @JacksonXmlProperty(localName = "CodContrtoOr")
    private String codContrtoOr;

    @JacksonXmlProperty(localName = "CNPJBase_IFOrContrto")
    private String cnpjBaseIFOrContrto;
    private String tpContrto;
    private String tpEnteCons;
    private String cnpjCorrespBanc;

    public String getCodContrtoOr() {
        return codContrtoOr;
    }

    public void setCodContrtoOr(String codContrtoOr) {
        this.codContrtoOr = codContrtoOr;
    }

    public String getCnpjBaseIFOrContrto() {
        return cnpjBaseIFOrContrto;
    }

    public void setCnpjBaseIFOrContrto(String cnpjBaseIFOrContrto) {
        this.cnpjBaseIFOrContrto = cnpjBaseIFOrContrto;
    }

    public String getTpContrto() {
        return tpContrto;
    }

    public void setTpContrto(String tpContrto) {
        this.tpContrto = tpContrto;
    }

    public String getTpEnteCons() {
        return tpEnteCons;
    }

    public void setTpEnteCons(String tpEnteCons) {
        this.tpEnteCons = tpEnteCons;
    }

    public String getCnpjCorrespBanc() {
        return cnpjCorrespBanc;
    }

    public void setCnpjCorrespBanc(String cnpjCorrespBanc) {
        this.cnpjCorrespBanc = cnpjCorrespBanc;
    }

    @Override
    public String toString() {
        return "GrupoIdentificadorContrato{" +
                "codContrtoOr=" + codContrtoOr +
                ", cnpjBaseIFOrContrto=" + cnpjBaseIFOrContrto +
                ", tpContrto=" + tpContrto +
                ", tpEnteCons=" + tpEnteCons +
                ", cnpjCorrespBanc=" + cnpjCorrespBanc +
                '}';
    }
}
