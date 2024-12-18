import java.util.List;

public class Theatre {

    private final String id;
    private final String name;
    private final String location;
    private final List<Seat> seats;

    public Theatre(String id, String name, String location, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.seats = seats;
    }
}
