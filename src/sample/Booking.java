package sample;

public class Booking {

    private String bookingID;
    private String movie;
    private String date;
    private String numberOfTickets;
    private String seats;
    private int totalPrice;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;


    public Booking(String bookingID, String movie, String date, String numberOfTickets, String seats, int totalPrice) {
        this.bookingID = bookingID;
        this.movie = movie;
        this.date = date;
        this.numberOfTickets = numberOfTickets;
        this.seats = seats;
        this.totalPrice = totalPrice;
    }

    public Booking(String bookingID, String movie, String date, String numberOfTickets, String seats, int totalPrice, String firstName, String lastName, String email, String phone) {
        this.bookingID = bookingID;
        this.movie = movie;
        this.date = date;
        this.numberOfTickets = numberOfTickets;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Booking() {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumberOfTickets(String numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
