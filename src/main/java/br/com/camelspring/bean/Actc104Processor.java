package br.com.camelspring.bean;

import br.com.camelspring.bean.actc104FromXsd.ACTCDOCComplexType;
import br.com.camelspring.bean.actc104FromXsd.GrupoACTC104PortlddComplexType;
import org.apache.camel.Body;
import org.apache.camel.Exchange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Actc104Processor {
    public void auditLog(Exchange exchange){
        System.out.println("exchange = [" + exchange + "]");
    }

    public void testeJaxb(@Body ACTCDOCComplexType actcdocComplexType) {
        System.out.println(actcdocComplexType);
    }

    public List<GrupoACTC104PortlddComplexType> testeSplit(@Body ACTCDOCComplexType actcdocComplexType) {
        return actcdocComplexType.getSISARQ().getACTC104().getGrupoACTC104Portldd();
    }

    public Player testeBindy(GrupoACTC104PortlddComplexType grupoACTC104PortlddComplexType){


        Player player = new Player();
        player.setName("ADASDADSAd");
        Address address = new Address();
        address.setCep("2131233");
        address.setName("Rua EAEAE");
        player.setAddress(address);
        player.setAverageRunRate(15.12f);
        player.setDebutDate(LocalDate.now());
        player.setStrikeRate(BigDecimal.valueOf(12.34));
        player.setRetirementDate(LocalDate.of(2019,05,12));
        player.setRunsScored(4000);
        player.setMatchesPlayed(5);
        player.setBatingPosition(3);
        player.setCountry("BRASIL");
        return player;

    }
}