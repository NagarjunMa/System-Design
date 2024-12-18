import java.util.List;

public class Booking {
    private BookingStatus bookingStatus;
    private final String id;
    private final User user;
    private final Show show;
    private final List<Seat> seats;
    private final double price;


    public Booking(String id, BookingStatus bookingStatus, User user, Show show, List<Seat> seats, double price) {
        this.id = id;
        this.bookingStatus = bookingStatus;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.price = price;
    }


    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }
}
