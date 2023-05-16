package org.example;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
public class Souvenirs implements Serializable {
    @JsonIgnore
    private transient int id;
    private static int tempId = 1;
    private String souvenirName;
    private double price;
    private  String manufacturerDetails;
    private String manufacturer;
    private String country;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate date;


    public Souvenirs(String SouvenirName, double price, String manufacturerDetails, String manufacturer, String country, LocalDate date) {
        this.id = tempId;
        this.souvenirName = SouvenirName;
        this.price = price;
        this.manufacturerDetails = manufacturerDetails;
        this.manufacturer = manufacturer;
        this.country = country;
        this.date = date;
        tempId++;
    }

    public Souvenirs() {
        this("", 0.0, "","","",null);
    }

    public int getId() {
        return id;
    }

    public String getSouvenirName() {
        return souvenirName;
    }

    public void setSouvenirName(String souvenirName) {
        this.souvenirName = souvenirName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturerDetails() {
        return manufacturerDetails;
    }

    public void setManufacturerDetails(String manufacturerDetails) {
        this.manufacturerDetails = manufacturerDetails;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Сувениры : " +
                "id=" + id +
                ", Сувенир='" + souvenirName + '\'' +
                ", Цена=" + price +"$"+
                ", Реквезиты производителя='" + manufacturerDetails + '\'' +
                ", Производитель='" + manufacturer + '\'' +
                ", Страна ='" + country + '\'' +
                ", Дата выпуска=" + date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Souvenirs souvenirs = (Souvenirs) o;
        return id == souvenirs.id && Double.compare(souvenirs.price, price) == 0 && Objects.equals(souvenirName, souvenirs.souvenirName) && Objects.equals(manufacturerDetails, souvenirs.manufacturerDetails) && Objects.equals(manufacturer, souvenirs.manufacturer) && Objects.equals(country, souvenirs.country) && Objects.equals(date, souvenirs.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, souvenirName, price, manufacturerDetails, manufacturer, country, date);
    }
}
