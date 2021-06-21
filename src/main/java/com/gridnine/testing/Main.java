package com.gridnine.testing;

import com.gridnine.testing.core.domain.FlightBuilder;
import com.gridnine.testing.core.service.model.FlightSearchCriteria;
import com.gridnine.testing.core.service.FlightFilter;

public class Main {
    public static void main(String[] args) {
        var flights = FlightBuilder.createFlights();

        var criteria = new FlightSearchCriteria();
        var filtered = new FlightFilter(criteria).getFlightsByCriteria(flights);
        filtered.forEach(System.out::println);
    }
}
