public class Seat {
    private final String id;
    private final int row;
    private final int column;
    private SeatStatus seatStatus;
    private final double price;
    private final SeatType type;


    public Seat(String id, int row, int column, SeatStatus seatStatus, double price, SeatType type) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.seatStatus = seatStatus;
        this.price = price;
        this.type = type;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public double getPrice() {
        return price;
    }

    public SeatType getType() {
        return type;
    }
}


