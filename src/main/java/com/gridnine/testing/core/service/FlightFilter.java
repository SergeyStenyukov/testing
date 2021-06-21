package com.gridnine.testing.core.service;

import com.gridnine.testing.core.domain.Flight;
import com.gridnine.testing.core.service.model.FlightSearchCriteria;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightFilter {

    private final static Integer TWO_HOURS_DURATION = 120;

    private final FlightSearchCriteria flightSearchCriteria;

    public FlightFilter(FlightSearchCriteria flightSearchCriteria) {
        this.flightSearchCriteria = flightSearchCriteria;
    }

    public List<Flight> getFlightsByCriteria(List<Flight> flights) {
        var predicates = buildPredicates();
        if (predicates.isEmpty()) {
            return flights;
        }
        return flights.stream().filter(predicates.stream().reduce(p -> true, Predicate::and)).collect(Collectors.toList());
    }

    private List<Predicate<Flight>> buildPredicates() {
        List<Predicate<Flight>> predicates = new ArrayList<>();
        if (flightSearchCriteria.getAvailableDepartures().equals(true)) {
            predicates.add(availableDepartures());
        }
        if (flightSearchCriteria.getSegmentsWithArrivalEarlierThanDeparture().equals(true)) {
            predicates.add(arrivalDateBeforeDeparture());
        }
        if (flightSearchCriteria.getTransferTimeMoreThan2Hours().equals(true)) {
            predicates.add(transferMoreThanTwoHours());
        }
        return predicates;
    }

    private Predicate<Flight> transferMoreThanTwoHours() {
        return flight -> {
            var segments = flight.getSegments();
            boolean isFound;
            for (int i = 0; i < segments.size(); i++) {
                if (i + 1 < segments.size()) {
                    isFound = isDurationMoreThanTwoHours(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                    if (isFound) {
                        return true;
                    }
                }
            }
            return false;
        };
    }

    private Predicate<Flight> arrivalDateBeforeDeparture() {
        return flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }

    private Predicate<Flight> availableDepartures() {
        return flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()));
    }

    private boolean isDurationMoreThanTwoHours(LocalDateTime arrival, LocalDateTime departure) {
        return Duration.between(arrival, departure).toMinutes() > TWO_HOURS_DURATION;
    }
}
