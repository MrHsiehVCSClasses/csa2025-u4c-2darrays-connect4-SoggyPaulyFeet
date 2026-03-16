package u8pp;

// This represents a reservation for the flight.
public class Reservation {
    private String name;
    private boolean frequentFlyer;

    // This constructor takes in the name of the passenger and if they are a frequent flyer.
    public Reservation(String name, boolean frequentFlyer) {
        this.name = name;
        this.frequentFlyer = frequentFlyer;
    }
    // This returns the name of the passenger for this reservation.
    public String getPassengerName(){
        return name;
    }
    // This returns true if the passenger is a frequent flyer.
    public boolean isFrequentFlyer() {
        return frequentFlyer;
    }

}