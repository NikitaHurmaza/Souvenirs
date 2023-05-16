package org.example;

import org.example.file.FileProcessor;
import org.example.for_enter_data.ForInput;
import org.example.for_enter_data.Types;
import org.example.menu.Menu;
import org.example.menu.MenuItem;
import org.example.logic.Logic;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Logic souvenirs = new Logic();

        ForInput forInput = new ForInput();

        FileProcessor fileProcessor = new FileProcessor();

        souvenirs.setSouvenirs(fileProcessor.readFile());
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Завершить программу", () -> System.exit(0)),
                new MenuItem("Список всех сувениров", souvenirs::showAll),
                new MenuItem("Список всех Производителей", souvenirs::showManufacturers),

                new MenuItem("Добавить сувенир", () -> {
                    String souvenirName = forInput.enterData("Введите название сувенира:", Types.STRING);
                    double price = forInput.enterData("Введите цену сувенира:", Types.DOUBLE);
                    String manufacturerDetails = forInput.enterData("Введите реквезиты производителя:", Types.STRING);
                    String manufacturer = forInput.enterData("Введите производителя:", Types.STRING);
                    String country = forInput.enterData("Введите страну производства:", Types.STRING);
                    LocalDate date = forInput.enterData("Введите дату через тире:", Types.LOCALDATE);
                    souvenirs.addSouvenir(souvenirName, price, manufacturerDetails, manufacturer,country,date);
                    fileProcessor.writeFile(souvenirs);}),

                new MenuItem("Редактировать сувенир", () -> {
                    int id = forInput.enterData("Введите ID сувенира, который хотите редактировать:", Types.INTEGER);
                    String souvenirName = forInput.enterData("Введите название сувенира:", Types.STRING);
                    double price = forInput.enterData("Введите цену сувенира:", Types.DOUBLE);
                    String manufacturerDetails = forInput.enterData("Введите реквизиты производителя:", Types.STRING);
                    String manufacturer = forInput.enterData("Введите производителя:", Types.STRING);
                    String country = forInput.enterData("Введите страну производства:", Types.STRING);
                    LocalDate date = forInput.enterData("Введите дату через тире:", Types.LOCALDATE);
                    souvenirs.editSouvenir(id, souvenirName, price, manufacturerDetails, manufacturer, country, date);
                    fileProcessor.writeFile(souvenirs);
                }),

                new MenuItem("Список сувениров заданного производителя" , () -> {
                    String manufacturer = forInput.enterData("Введите производителя:", Types.STRING);
                    System.out.println(souvenirs.filterSouvenirsByManufacturers(manufacturer));}),

                new MenuItem("Список сувениров заданной страны", () ->{
                    String country = forInput.enterData("Введите название страны", Types.STRING);
                    System.out.println(souvenirs.filterSouvenirsByCountry(country));
                }),
                new MenuItem("Список производителей с ценами на сувениры меньше заданой" , () -> {
                    int price = forInput.enterData("Введите цену:", Types.INTEGER);
                    souvenirs.showManufacturersWithPricesLessThan(price);
                }),
                new MenuItem("Список всех Производителей/ информация о сувенирах которые они производят", souvenirs::showManufacturersAndTheirSouvenirs),
                new MenuItem("Вывести информацию о производителях заданного сувенира, произведенного в заданном году ", () ->{
                    String souvenirName = forInput.enterData("Введите название сувенира:", Types.STRING);
                    int year = forInput.enterData("Введите год:", Types.INTEGER);
                    System.out.println(souvenirs.showManufacturersOfSouvenirByYear( souvenirName, year ));
                }),
                new MenuItem("Для каждого года вывести список сувениров, сделанных в этом году ", souvenirs::showSouvenirsByYear),
                new MenuItem("Удалить заданного производителя и его сувениры ", () ->{
                    String manufacturer = forInput.enterData("Введите производителя", Types.STRING);
                    souvenirs.removeManufacturerAndTheirSouvenirs(manufacturer);
                    fileProcessor.writeFile(souvenirs);
                }),
                new MenuItem("Удалить сувенир по id", () -> {
                    int id = forInput.enterData("Введите id сувенира:", Types.INTEGER);
                    souvenirs.remove(id);
                    fileProcessor.writeFile(souvenirs);
                }));
        Menu menu = new Menu(menuItems);
        menu.run();

    }
}