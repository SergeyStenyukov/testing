package com.gridnine.testing.core.service;

import com.gridnine.testing.core.domain.Flight;
import com.gridnine.testing.core.domain.FlightBuilder;
import com.gridnine.testing.core.service.model.FlightSearchCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightFilterTest {

    @Mock
    FlightSearchCriteria flightSearchCriteria;

    List<Flight> flights;

    @InjectMocks
    FlightFilter flightFilter;

    @BeforeEach
    void initTestData() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void getFlightByCriteriaShouldReturnAllFlightsIfNoCriteriaSpecified() {
        assertEquals(flights.size(), flightFilter.getFlightsByCriteria(flights).size());
    }

    @Test
    void getFlightByCriteriaShouldReturnSpecificFlightsIfAvailableDeparturesSpecified() {
        when(flightSearchCriteria.getAvailableDepartures()).thenReturn(true);
        assertEquals(5, flightFilter.getFlightsByCriteria(flights).size());
    }

    @Test
    void getFlightByCriteriaShouldReturnSpecificFlightsIfArrivalEarlierDepartureSpecified() {
        when(flightSearchCriteria.getSegmentsWithArrivalEarlierThanDeparture()).thenReturn(true);
        assertEquals(1, flightFilter.getFlightsByCriteria(flights).size());
    }

    @Test
    void getFlightByCriteriaShouldReturnSpecificFlightsIfTransferTimeMoreThanTwoHoursSpecified() {
        when(flightSearchCriteria.getTransferTimeMoreThan2Hours()).thenReturn(true);
        assertEquals(1, flightFilter.getFlightsByCriteria(flights).size());
    }

    @Test
    void getFlightByCriteriaShouldReturnSpecificFlightsIfMoreThanOneCriteriaSpecified() {
        when(flightSearchCriteria.getAvailableDepartures()).thenReturn(true);
        when(flightSearchCriteria.getSegmentsWithArrivalEarlierThanDeparture()).thenReturn(true);
        assertEquals(1, flightFilter.getFlightsByCriteria(flights).size());
    }

    @Test
    void getFlightByCriteriaShouldNotReturnFlightsIfNoneMatchesCriteria() {
        when(flightSearchCriteria.getAvailableDepartures()).thenReturn(true);
        when(flightSearchCriteria.getSegmentsWithArrivalEarlierThanDeparture()).thenReturn(true);
        when(flightSearchCriteria.getTransferTimeMoreThan2Hours()).thenReturn(true);
        assertEquals(0, flightFilter.getFlightsByCriteria(flights).size());
    }
}