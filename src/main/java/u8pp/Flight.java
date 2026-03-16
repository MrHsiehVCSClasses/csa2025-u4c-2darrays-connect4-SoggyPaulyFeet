package u8pp;
import java.util.ArrayList;

public class Flight {
    private Reservation[][] seats;
    private int aisleIndex;

 // This gets  the number of rows and number of seats per each row
    public Flight( int rows, int seatsPerRow) {
        seats = new Reservation[rows][seatsPerRow + 1];
        aisleIndex = seats[0].length / 2;

        for (int r=0; r<seats.length; r++) {
            seats[r][aisleIndex] = new Reservation("AISLE", false);
        }
    }
    // This gets a 2D array of the reservations where null represents an empty seat and a reservation with name "AISLE"
    public Reservation[][] getSeats() {
        return seats;
    }
    // It is an ArrayList of the names of the frequent flyers on the flight. 
    public ArrayList<String> getFrequentFlyers() {
        ArrayList<String> flyers = new ArrayList<>();

        for (int r=0; r<seats.length; r++) {
            for (int c=0; c<seats[r].length; c++) {
                if (seats[r][c] != null && !seats[r][c].getPassengerName().equals("AISLE") && seats[r][c].isFrequentFlyer()) {
                    flyers.add(seats[r][c].getPassengerName());
                }
            }
        }
        return flyers;
    }
    // This method reserves the available seat which closest to the front of the plane.
    public boolean reserveNextAvailableSeat (String passengerName, boolean frequentFlyer) {
        for (int r=0; r<seats.length; r++) {
            for (int c=0; c<seats[r].length; c++) {
                if (c == aisleIndex) {
                    continue;
                }
                if (seats[r][c] == null) {
                    seats[r][c] = new Reservation(passengerName, frequentFlyer);
                    return true;
                }
            }
        }
        return false;
    }
    // This method reserves two adjacent seats for the two passegets and shows true if seats are available

    public boolean reserveAdjacentSeats(String passengerName1, boolean frequentFlyer1, String passengerName2, boolean frequentFlyer2) {
        for (int r=0; r<seats.length; r++) {
            for (int c=0; c<seats[r].length-1; c++) {
                if (c == aisleIndex || c+1 == aisleIndex) {
                    continue;
                }
                if (seats[r][c] == null && seats[r][c+1] == null) {
                    seats[r][c] = new Reservation(passengerName1, frequentFlyer1);
                    seats[r][c+1] = new Reservation(passengerName2, frequentFlyer2);
                    return true;
                }
            }
        }
        return false;
    }
    // this method reserves the available aisle seatthats closes to the front of the plane
    public boolean reserveAisleSeat(String passengerName, boolean frequentFlyer) {
        int leftOfAisle = aisleIndex - 1;
        int rightOfAisle = aisleIndex + 1;

        for (int r=0; r<seats.length; r++) {
            if(leftOfAisle >= 0 && seats[r][leftOfAisle] == null) {
                seats[r][leftOfAisle] = new Reservation(passengerName, frequentFlyer);
                return true;
            }
            if(rightOfAisle < seats[r].length && seats[r][rightOfAisle] == null) {
                seats[r][rightOfAisle] =  new Reservation(passengerName, frequentFlyer);
                return true;
            }
        }
        return false;
    }
    // This returns an ArrayList of the names of passengers which are not sitting next to any other passengers.
    public ArrayList<String> getIsolatedPassengers() {
        ArrayList<String> isolated = new ArrayList<>();

        for (int r=0; r<seats.length; r++) {
            for (int c=0; c<seats[r].length; c++) {
                if (seats[r][c] == null) {
                    continue;
                }
                if (seats[r][c].getPassengerName().equals("AISLE")) {
                    continue;
                }

                boolean leftSafe = true;
                boolean rightSafe = true;

                if(c-1 >= 0) {
                    if(seats[r][c-1] != null && !seats[r][c-1].getPassengerName().equals("AISLE")) {
                        leftSafe = false;
                    }
                }

                if (c+1 < seats[r].length) {
                    if (seats[r][c+1] !=null && !seats[r][c+1].getPassengerName().equals("AISLE")) {
                        rightSafe = false;
                    }
                }
                if(leftSafe && rightSafe) {
                    isolated.add(seats[r][c].getPassengerName());
                }

            }
        }
        return isolated;
    }
    public String toString() {
        String result = "";

        for(int r=0; r<seats.length; r++) {
            for (int c=0; c<seats[r].length; c++) {
                if (seats[r][c] == null) {
                    result += "EMPTY";
                } else {
                    result += seats[r][c].getPassengerName();
                }
                if (c<seats[r].length - 1) {
                    result += " ";
                }
            }
            if (r<seats.length - 1) {
                result += "\n";
            }
        }
        return result;
    }
}