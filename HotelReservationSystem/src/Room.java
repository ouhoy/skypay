public class Room {
    private int roomNumber;
    private RoomType roomType;
    private int roomPricePerNight;

    public Room(int roomNumber, RoomType roomType, int roomPricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPricePerNight = roomPricePerNight;
    }

    // Getters and setters
    public int getRoomNumber() { return roomNumber; }
    public RoomType getRoomType() { return roomType; }
    public int getRoomPricePerNight() { return roomPricePerNight; }
    
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public void setRoomPricePerNight(int roomPricePerNight) { this.roomPricePerNight = roomPricePerNight; }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomType=" + roomType +
                ", roomPricePerNight=" + roomPricePerNight +
                '}';
    }
}
