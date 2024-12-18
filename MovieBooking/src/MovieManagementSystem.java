import java.nio.file.AtomicMoveNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MovieManagementSystem {
    private static MovieManagementSystem instance;
    private final List<Movie> movies;
    private final List<Theatre> theatres;
    private final Map<String, Seat> seats;
    private final Map<String, Booking> bookings;
    private final List<Show> shows;

    private static final String BOOKING_ID_PREFIX = "BKG";
    private static final AtomicLong bookingCounter = new AtomicLong(0);

    public MovieManagementSystem() {
        movies = new ArrayList<>();
        theatres = new ArrayList<>();
        seats = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
        shows = new ArrayList<>();
    }

    public static MovieManagementSystem getInstance(){
        if(instance == null){
            return new MovieManagementSystem();
        }
        return instance;
    }

    public void addMovies(Movie movie){
        movies.add(movie);
    }

    public void addTheatres(Theatre theatre){
        theatres.add(theatre);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void addShow(Show show){
        shows.add(show);
    }

    public synchronized Booking bookTickets(User user, Show show, List<Seat> seats){
        if(areSeatsAvailable(show,seats)){
            markSeatedSeats(show,seats);
            double totalPrice = calculateTotalPrice(seats);
            String bookingID = generateBookingId();
            Booking booking = new Booking(bookingID, BookingStatus.PENDING,user,show,seats,totalPrice);
            return booking;
        }
        return null;
    }

    public boolean areSeatsAvailable(Show show, List<Seat> seats){
        for(Seat seat : seats){
            Seat showSeat = show.getSeats().get(seat.getId());
            if(showSeat == null || showSeat.getSeatStatus() != SeatStatus.AVAILABLE){
                return false;
            }
        }
        return true;
    }


    public double calculateTotalPrice(List<Seat> seats){
        return seats.stream().mapToDouble(Seat::getPrice).sum();
    }

    public void markSeatedSeats(Show show, List<Seat> seats){
        for(Seat seat : seats){
            Seat showSeat = show.getSeats().get(seat.getId());
            showSeat.setSeatStatus(SeatStatus.OCCUPIED);
        }
    }

    public String generateBookingId(){
        long bookingNum = bookingCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return BOOKING_ID_PREFIX + timestamp + String.format("%06d", bookingNum);
    }

    public synchronized void confirmBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        if(booking != null && booking.getBookingStatus() == BookingStatus.PENDING){
            booking.setBookingStatus(BookingStatus.CONFIRMED);
        }
    }

    public synchronized void cancelBooking(String bookingId){
        Booking booking = bookings.get(bookingId);
        if(booking != null && booking.getBookingStatus() == BookingStatus.PENDING){
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            markSeatedSeats(booking.getShow(), booking.getSeats());
        }
    }

    public void markSeatsAvailable(Show show, List<Seat> seats){
        for(Seat seat : seats){
            Seat showSeat = show.getSeats().get(seat.getId());
            showSeat.setSeatStatus(SeatStatus.AVAILABLE);
        }
    }





}
