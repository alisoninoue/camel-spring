package br.com.camelspring.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.camel.dataformat.bindy.Format;
import org.apache.camel.dataformat.bindy.annotation.BindyConverter;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.Link;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

/**
 * Here we are processing a fixed length record in below format
 * Name, Debut Date, Country , Mathes palyed, Runs scored, Average, Strike rate, Batting posion, Reteriment Date
 *
 * Rahul Dravid      1996-09-10INDIA       00160  9,30060.5480.54 42012-09-01
 * Sachin Tendulkar  1989-09-10INDIA       00140 49,60064.5440.54 42012-09-01+01.11Rua Indianes   02308100
 * ADASDADSAd        2019-07-14BRASIL      00005  0,00415.1212.34 32019-05-12-99.10Rua EAEAE      2131233
 * Steve Waugh       1993-09-10AUSTRALIA   00140 30,90072.5480.54 42012-09-01
 * Kevin Peterson    1999-09-10INDIA       00140 21,60020.5450.54 42012-09-01
 * Adam Gilcrist     1996-09-10AUSTRALIA   00140 18,67077.5460.54102012-09-01
 *
 * @author santosh joshi (santoshjoshi2003@gmail.com)
 */
@FixedLengthRecord(length = 103, paddingChar = ' ')
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

    @DataField(pos = 10, length = 6, precision = 2, pattern = "00.00", defaultValue = "default")
    @BindyConverter(CustomConverter.class)
    private BigDecimal valueTest;


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

    public BigDecimal getValueTest() {
        return valueTest;
    }

    public void setValueTest(BigDecimal valueTest) {
        this.valueTest = valueTest;
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
                ", valueTest=" + valueTest +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return getMatchesPlayed() == player.getMatchesPlayed() &&
                Float.compare(player.getAverageRunRate(), getAverageRunRate()) == 0 &&
                getBatingPosition() == player.getBatingPosition() &&
                Objects.equals(getName(), player.getName()) &&
                Objects.equals(getDebutDate(), player.getDebutDate()) &&
                Objects.equals(getCountry(), player.getCountry()) &&
                Objects.equals(getRunsScored(), player.getRunsScored()) &&
                Objects.equals(getStrikeRate(), player.getStrikeRate()) &&
                Objects.equals(getRetirementDate(), player.getRetirementDate()) &&
                Objects.equals(getValueTest(), player.getValueTest()) &&
                Objects.equals(getAddress(), player.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDebutDate(), getCountry(), getMatchesPlayed(), getRunsScored(), getAverageRunRate(), getStrikeRate(), getBatingPosition(), getRetirementDate(), getValueTest(), getAddress());
    }

    public static class CustomConverter implements Format<Object> {
        @Override
        public String format(Object object) throws Exception {
            if (object instanceof String) {
                return "      ";
            }
            DecimalFormat df = getDecimalFormat();
            return df.format(object);
        }


        @Override
        public Object parse(String string) throws Exception {
            if (string == null || string.trim().isEmpty()) {
                return null;
            }
            DecimalFormat df = getDecimalFormat();
            df.setParseBigDecimal(true);
            BigDecimal bd = (BigDecimal) df.parse(string.trim());
            return bd.setScale(2, RoundingMode.valueOf(0));
        }

        private DecimalFormat getDecimalFormat() {
            DecimalFormat df = new DecimalFormat();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
            df.setDecimalFormatSymbols(dfs);
            df.applyPattern("00.00");
            df.setPositivePrefix("+");
            return df;
        }
    }
}