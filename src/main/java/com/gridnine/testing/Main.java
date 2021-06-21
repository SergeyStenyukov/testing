package com.gridnine.testing;

import com.gridnine.testing.core.domain.FlightBuilder;
import com.gridnine.testing.core.service.model.FlightSearchCriteria;
import com.gridnine.testing.core.service.FlightFilter;

public class Main {
    public static void main(String[] args) {
        var flights = FlightBuilder.createFlights();

        System.out.println("Вылет до текущего момента времени");
        var criteria = new FlightSearchCriteria(false, true, false);
        new FlightFilter(criteria).getFlightsByCriteria(flights).forEach(System.out::println);

        System.out.println("Имеются сегменты с датой прилёта раньше даты вылета");
        criteria = new FlightSearchCriteria(true, false, false);
        new FlightFilter(criteria).getFlightsByCriteria(flights).forEach(System.out::println);

        System.out.println("Общее время, проведённое на земле превышает два часа ");
        criteria = new FlightSearchCriteria(false, false, true);
        new FlightFilter(criteria).getFlightsByCriteria(flights).forEach(System.out::println);

        System.out.println("Дефолтный запрос");
        criteria = new FlightSearchCriteria();
        new FlightFilter(criteria).getFlightsByCriteria(flights).forEach(System.out::println);
    }
}
