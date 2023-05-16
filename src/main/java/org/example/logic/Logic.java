package org.example.logic;

import org.example.Souvenirs;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Logic {
    private List<Souvenirs> souvenirs;

    public Logic() {
        souvenirs = new ArrayList<>();
    }

    public List<Souvenirs> getSouvenirs() {
        return souvenirs;
    }

    public void setSouvenirs(List<Souvenirs> souvenirs) {
        this.souvenirs = souvenirs;
    }

    public void addSouvenir(String souvenirName , double price, String manufacturerDetails, String manufacturer, String country,LocalDate date) {
        Souvenirs souvenirs1 = new Souvenirs(souvenirName, price, manufacturerDetails, manufacturer,country,date);
        souvenirs.add(souvenirs1);
    }
    public boolean remove(int id) {
        return souvenirs.removeIf(house -> house.getId() == id);
    }

    public void editSouvenir(int id, String souvenirName, double price, String manufacturerDetails, String manufacturer, String country, LocalDate date) {
        for (Souvenirs souvenir : souvenirs) {
            if (souvenir.getId() == id) {
                souvenir.setSouvenirName(souvenirName);
                souvenir.setPrice(price);
                souvenir.setManufacturerDetails(manufacturerDetails);
                souvenir.setManufacturer(manufacturer);
                souvenir.setCountry(country);
                souvenir.setDate(date);
                return;
            }
        }
    }
    public void showAll() {
        if (souvenirs.isEmpty()) {
            return;
        }
        souvenirs.forEach(System.out::println);
    }

    public void showManufacturers() {
        if (souvenirs.isEmpty()) {
            return;
        }
        Set<String> manufacturers = new HashSet<>();
        for (Souvenirs souvenir : souvenirs) {
            manufacturers.add(souvenir.getManufacturer());
        }
        manufacturers.forEach(System.out::println);
    }
    public List<Souvenirs> filterSouvenirsByManufacturers(String manufacturerName) {
        return souvenirs.stream()
                .filter(s->s.getManufacturer().equals(manufacturerName))
                .toList();
    }
    public List<Souvenirs> filterSouvenirsByCountry(String country){
        return souvenirs.stream()
                .filter(s->s.getCountry().equals(country))
                .toList();

    }

    public List<Souvenirs> showManufacturersWithPricesLessThan(double price) {
        List<Souvenirs> matchingSouvenirs = souvenirs.stream()
                .filter(s -> s.getPrice() < price)
                .toList();

        if (matchingSouvenirs.isEmpty()) {
            System.out.println("Нет сувениров с ценами меньше " + price);
        } else {
            System.out.println("Производители сувениров с ценами меньше " + price + ":");
            matchingSouvenirs.stream()
                    .map(s -> s.getManufacturer() + " (" + s.getCountry() + ")")
                    .forEach(System.out::println);
        }

        return matchingSouvenirs;
    }
    public void showManufacturersAndTheirSouvenirs() {
        Map<String, List<Souvenirs>> manufacturersMap = souvenirs.stream()
                .collect(Collectors.groupingBy(Souvenirs::getManufacturer));
        if (manufacturersMap.isEmpty()) {
            System.out.println("Нет информации о производителях и их сувенирах");
        } else {
            System.out.println("Информация о производителях и их сувенирах:");
            for (Map.Entry<String, List<Souvenirs>> entry : manufacturersMap.entrySet()) {
                String manufacturer = entry.getKey();
                List<Souvenirs> souvenirsList = entry.getValue();
                System.out.println("Производитель: " + manufacturer + " (" +souvenirsList.get(0).getCountry()+ ")");
                System.out.println("Сувениры:");
                for (Souvenirs souvenir : souvenirsList) {
                    System.out.println("- " + souvenir.getSouvenirName() + ":" +" Цена: "+ souvenir.getPrice() + "; Дата: " + souvenir.getDate() + "; Реквезиты производителя: " + souvenir.getManufacturerDetails());
                }
                System.out.println();
            }
        }
    }
    public List<Souvenirs> showManufacturersOfSouvenirByYear(String souvenirName, int year) {
        List<Souvenirs> matchingSouvenirs = souvenirs.stream()
                .filter(s -> s.getSouvenirName().equals(souvenirName) && s.getDate().getYear() == year)
                .toList();

        if (matchingSouvenirs.isEmpty()) {
            System.out.println("Нет информации о производителях, которые выпускали сувенир '" + souvenirName + "' в " + year);
        } else {
            System.out.println("Информация о производителях сувенира '" + souvenirName + "', выпущенного в " + year + ":");
            Set<String> manufacturers = new HashSet<>();
            for (Souvenirs souvenir : matchingSouvenirs) {
                manufacturers.add("Производитель: "+souvenir.getManufacturer()+"; Страна: "+ souvenir.getCountry());
            }
            manufacturers.forEach(System.out::println);
        }

        return matchingSouvenirs;
    }
    public void showSouvenirsByYear() {
        Map<Integer, List<Souvenirs>> souvenirsByYear = souvenirs.stream()
                .collect(Collectors.groupingBy(s -> s.getDate().getYear()));

        if (souvenirsByYear.isEmpty()) {
            System.out.println("Нет информации о сувенирах");
        } else {
            System.out.println("Список сувениров по годам:");
            for (Map.Entry<Integer, List<Souvenirs>> entry : souvenirsByYear.entrySet()) {
                int year = entry.getKey();
                List<Souvenirs> souvenirsList = entry.getValue();
                System.out.println("Год: " + year);
                System.out.println("Сувениры:");
                for (Souvenirs souvenir : souvenirsList) {
                    System.out.println("- " + souvenir.getSouvenirName() + "; Цена: " + souvenir.getPrice() + "; Реквезиты производителя: " + souvenir.getManufacturerDetails()+"; Производитель: " + souvenir.getManufacturer()+"; Страна: " + souvenir.getCountry());
                }
                System.out.println();
            }
        }
    }
    public void removeManufacturerAndTheirSouvenirs(String manufacturer) {
        List<Souvenirs> souvenirsToRemove = souvenirs.stream()
                .filter(s -> s.getManufacturer().equals(manufacturer))
                .toList();

        souvenirs.removeAll(souvenirsToRemove);

        System.out.println("Удалены производитель '" + manufacturer + "' и его сувениры.");
    }

}
