package sample;

public class Booking {

    private String bookingID;
    private String movie;
    private String date;
    private String numberOfTickets;
    private String seats;
    private int totalPrice;


    public Booking(String bookingID, String movie, String date, String numberOfTickets, String seats, int totalPrice) {
        this.bookingID = bookingID;
        this.movie = movie;
        this.date = date;
        this.numberOfTickets = numberOfTickets;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getMovie() {
        return movie;
    }

    public String getDate() {
        return date;
    }

    public String getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getSeats() {
        return seats;
    }

    public int getTotalPrice() {
        return totalPrice;
    }


}
