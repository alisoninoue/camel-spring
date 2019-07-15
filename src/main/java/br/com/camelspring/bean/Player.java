package br.com.camelspring.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.Link;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Here we are processing a fixed length record in below format
 * Name, Debut Date, Country , Mathes palyed, Runs scored, Average, Strike rate, Batting posion, Reteriment Date
 * <p>
 * Rahul Dravid      1996-09-10INDIA       00160  9,30060.5480.54 42012-09-01
 * Sachin Tendulkar  1989-09-10INDIA       00140 49,60064.5440.54 42012-09-01Rua Indianes   02308100
 * ADASDADSAd        2019-07-14BRASIL      00005  0,00415.1212.34 32019-05-12Rua EAEAE      2131233
 * Steve Waugh       1993-09-10AUSTRALIA   00140 30,90072.5480.54 42012-09-01
 * Kevin Peterson    1999-09-10INDIA       00140 21,60020.5450.54 42012-09-01
 * Adam Gilcrist     1996-09-10AUSTRALIA   00140 18,67077.5460.54102012-09-01
 *
 * @author santosh joshi (santoshjoshi2003@gmail.com)
 */
@FixedLengthRecord(length = 97, paddingChar = ' ')
public class Player implements Serializable {


    private static final long serialVersionUID = 1L;

    @DataField(pos = 1, length = 18, trim = true, align = "L")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DataField(pos = 2, length = 10, pattern = "yyyy-MM-dd")
    private LocalDate debutDate;

    @DataField(pos = 3, length = 12, trim = true, align = "L")
    private String country;

    @DataField(pos = 4, length = 5, paddingChar = '0', trim = true)
    private int matchesPlayed;

    @DataField(pos = 5, length = 7, paddingChar = ' ', trim = true, pattern = "#0,000")
    private Integer runsScored;

    @DataField(pos = 6, length = 5, paddingChar = '0', precision = 2)
    private float averageRunRate;

    @DataField(pos = 7, length = 5, precision = 2)
    private BigDecimal strikeRate;

    @DataField(pos = 8, length = 2, paddingChar = ' ', trim = true)
    private int batingPosition;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DataField(pos = 9, length = 10, pattern = "yyyy-MM-dd", defaultValue = "2021-12-31")
    private LocalDate retirementDate;

    @Link
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(LocalDate debutDate) {
        this.debutDate = debutDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(Integer runsScored) {
        this.runsScored = runsScored;
    }

    public float getAverageRunRate() {
        return averageRunRate;
    }

    public void setAverageRunRate(float averageRunRate) {
        this.averageRunRate = averageRunRate;
    }

    public BigDecimal getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(BigDecimal strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getBatingPosition() {
        return batingPosition;
    }

    public void setBatingPosition(int batingPosition) {
        this.batingPosition = batingPosition;
    }

    public LocalDate getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(LocalDate retirementDate) {
        this.retirementDate = retirementDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", debutDate=" + debutDate +
                ", country='" + country + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", runsScored=" + runsScored +
                ", averageRunRate=" + averageRunRate +
                ", strikeRate=" + strikeRate +
                ", batingPosition=" + batingPosition +
                ", retirementDate=" + retirementDate +
                ", address=" + address +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result
                + ((debutDate == null) ? 0 : debutDate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (debutDate == null) {
            if (other.debutDate != null)
                return false;
        } else if (!debutDate.equals(other.debutDate))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }


}