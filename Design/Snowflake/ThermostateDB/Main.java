package Snowflake.ThermostateDB;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        ThermostateDBImpl thermostateDB = new ThermostateDBImpl();
        thermostateDB.addTemperature(LocalDateTime.of(2020, 1, 1, 0, 0), 1.0);
        thermostateDB.addTemperature(LocalDateTime.of(2020, 1, 1, 1, 0), 2.0);
        thermostateDB.addTemperature(LocalDateTime.of(2020, 1, 1, 2, 0), 3.0);


        LocalDateTime queryTime1 = LocalDateTime.of(2020, 1, 1, 1, 0);
        double temperatureAtQuery1 = thermostateDB.getTemperature(queryTime1);
        System.out.println(temperatureAtQuery1);

        LocalDateTime start = LocalDateTime.of(2020, 1, 1, 1, 0);
        LocalDateTime end = LocalDateTime.of(2020, 1, 10, 1, 0);
        System.out.println(thermostateDB.getTemperatureBetween(start, end));
    }
}