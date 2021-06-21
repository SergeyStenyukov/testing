package com.gridnine.testing.core.service.model;

public class FlightSearchCriteria {

    private boolean arrivalDateBeforeDeparturePresents;

    private boolean availableDepartures;

    private boolean transferTimeMoreThan2Hours;

    public FlightSearchCriteria() {
    }

    public FlightSearchCriteria(boolean segmentsWithArrivalEarlierThanDeparture, boolean availableDepartures,
                                boolean transferTimeMoreThan2Hours) {
        this.arrivalDateBeforeDeparturePresents = segmentsWithArrivalEarlierThanDeparture;
        this.availableDepartures = availableDepartures;
        this.transferTimeMoreThan2Hours = transferTimeMoreThan2Hours;
    }

    public Boolean getSegmentsWithArrivalEarlierThanDeparture() {
        return arrivalDateBeforeDeparturePresents;
    }

    public void setArrivalDateBeforeDeparturePresents(boolean arrivalDateBeforeDeparturePresents) {
        this.arrivalDateBeforeDeparturePresents = arrivalDateBeforeDeparturePresents;
    }

    public Boolean getAvailableDepartures() {
        return availableDepartures;
    }

    public void setAvailableDepartures(boolean availableDepartures) {
        this.availableDepartures = availableDepartures;
    }

    public Boolean getTransferTimeMoreThan2Hours() {
        return transferTimeMoreThan2Hours;
    }

    public void setTransferTimeMoreThan2Hours(boolean transferTimeMoreThan2Hours) {
        this.transferTimeMoreThan2Hours = transferTimeMoreThan2Hours;
    }
}
