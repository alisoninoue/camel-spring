package br.com.camelspring.bean;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteBindyMarshal {
    public Player geraBindyNegative(){
        Player player = new Player();
        player.setName("PLAYER NAME");
        Address address = new Address();
        address.setCep("131213");
        address.setName("Address");
        player.setAddress(address);
        player.setAverageRunRate(11.12f);
        player.setDebutDate(LocalDate.now());
        player.setStrikeRate(BigDecimal.valueOf(12.34));
        player.setRetirementDate(LocalDate.of(2019,07,12));
        player.setRunsScored(4020);
        player.setMatchesPlayed(5);
        player.setBatingPosition(3);
        player.setCountry("BRASIL");
        player.setValueTest(new BigDecimal(-2.2));
        return player;
    }

    public Player geraBindyPositive(){
        Player player = new Player();
        player.setName("PLAYER NAME");
        Address address = new Address();
        address.setCep("131213");
        address.setName("Address");
        player.setAddress(address);
        player.setAverageRunRate(11.12f);
        player.setDebutDate(LocalDate.now());
        player.setStrikeRate(BigDecimal.valueOf(12.34));
        player.setRetirementDate(LocalDate.of(2019,07,12));
        player.setRunsScored(4020);
        player.setMatchesPlayed(5);
        player.setBatingPosition(3);
        player.setCountry("BRASIL");
        player.setValueTest(new BigDecimal(1));
        return player;
    }

    public Player geraBindyWithout(){
        Player player = new Player();
        player.setName("PLAYER NAME");
        Address address = new Address();
        address.setCep("131213");
        address.setName("Address");
        player.setAddress(address);
        player.setAverageRunRate(11.12f);
        player.setDebutDate(LocalDate.now());
        player.setStrikeRate(BigDecimal.valueOf(12.34));
        player.setRetirementDate(LocalDate.of(2019,07,12));
        player.setRunsScored(4020);
        player.setMatchesPlayed(5);
        player.setBatingPosition(3);
        player.setCountry("BRASIL");
        return player;
    }
}
