import java.util.Date;

public class Booking {
    private int userId;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;
    private int totalCost;
    private RoomType roomTypeAtBooking;
    private int roomPriceAtBooking;
    private int userBalanceAtBooking;

    public Booking(int userId, int roomNumber, Date checkIn, Date checkOut, 
                   int totalCost, RoomType roomTypeAtBooking, int roomPriceAtBooking, 
                   int userBalanceAtBooking) {
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalCost = totalCost;
        this.roomTypeAtBooking = roomTypeAtBooking;
        this.roomPriceAtBooking = roomPriceAtBooking;
        this.userBalanceAtBooking = userBalanceAtBooking;
    }

    // Getters
    public int getUserId() { return userId; }
    public int getRoomNumber() { return roomNumber; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }
    public int getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return "Booking{" +
                "userId=" + userId +
                ", roomNumber=" + roomNumber +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalCost=" + totalCost +
                ", roomType=" + roomTypeAtBooking +
                ", roomPrice=" + roomPriceAtBooking +
                ", userBalance=" + userBalanceAtBooking +
                '}';
    }
}
