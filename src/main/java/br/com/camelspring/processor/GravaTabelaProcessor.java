package br.com.camelspring.processor;

import br.com.camelspring.bean.Player;
import br.com.camelspring.bean.actc101.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GravaTabelaProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Player player = exchange.getIn().getBody(Player.class);

        Actc101 actc101 = new Actc101();
        GrupoPortabilidade grupoPortabilidade = new GrupoPortabilidade();
        grupoPortabilidade.setIdentdPartAdmdo("123456789");
        grupoPortabilidade.setNumCtrlIF("12345");

        GrupoIdentificadorContrato grupoIdentificadorContrato = new GrupoIdentificadorContrato();
        grupoIdentificadorContrato.setCnpjBaseIFOrContrto("123456");
        grupoIdentificadorContrato.setCnpjCorrespBanc("456678");
        grupoIdentificadorContrato.setCodContrtoOr("777");
        grupoIdentificadorContrato.setTpContrto("202");
        grupoIdentificadorContrato.setTpEnteCons("01");

        GrupoCliente grupoCliente = new GrupoCliente();
        grupoCliente.setNome(player.getName());
        grupoCliente.setTelefone("12313");
//        grupoCliente.setEmail("teste@gmail.com");

        GrupoEndereco grupoEndereco = new GrupoEndereco();

        GrupoProposta grupoProposta = new GrupoProposta();

        grupoPortabilidade.setGrupoCliente(grupoCliente);
        grupoPortabilidade.setGrupoEndereco(grupoEndereco);
        grupoPortabilidade.setGrupoIdentificadorContrato(grupoIdentificadorContrato);
        grupoPortabilidade.setGrupoProposta(grupoProposta);

        List<GrupoPortabilidade> gruposList = new ArrayList<>();
        gruposList.add(grupoPortabilidade);
        gruposList.add(grupoPortabilidade);
/*        GrupoPortabilidade[] grupoArray = new GrupoPortabilidade[gruposList.size()];
        gruposList.toArray(grupoArray);*/
        actc101.setGrupoPortabilidade(gruposList);

        exchange.getIn().setBody(actc101);
    }
}
