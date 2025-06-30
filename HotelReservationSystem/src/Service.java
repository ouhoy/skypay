import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;

public class Service {
    ArrayList<Room> rooms;
    ArrayList<User> users;
    ArrayList<Booking> bookings;

    public Service() {
        rooms = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        Room existingRoom = findRoomByNumber(roomNumber);
        if (existingRoom != null) {
            existingRoom.setRoomType(roomType);
            existingRoom.setRoomPricePerNight(roomPricePerNight);
        } else {
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        }
    }

    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        try {
            // Validate dates
            if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
                throw new IllegalArgumentException("Invalid date range: check-out must be after check-in");
            }

            User user = findUserById(userId);
            Room room = findRoomByNumber(roomNumber);

            if (user == null) {
                throw new IllegalArgumentException("User not found: " + userId);
            }
            if (room == null) {
                throw new IllegalArgumentException("Room not found: " + roomNumber);
            }

            // Check if room is available for the period
            if (!isRoomAvailable(roomNumber, checkIn, checkOut)) {
                throw new IllegalArgumentException("Room is not available for the specified period");
            }

            // Calculate cost
            long diffInMillies = checkOut.getTime() - checkIn.getTime();
            int nights = (int) (diffInMillies / (1000 * 60 * 60 * 24));
            int totalCost = nights * room.getRoomPricePerNight();

            // Check user balance
            if (user.getBalance() < totalCost) {
                throw new IllegalArgumentException("Insufficient balance for booking");
            }

            // Create booking and deduct balance
            Booking booking = new Booking(userId, roomNumber, checkIn, checkOut, 
                                        totalCost, room.getRoomType(), room.getRoomPricePerNight(), 
                                        user.getBalance());
            bookings.add(booking);
            user.deductBalance(totalCost);

            System.out.println("Booking successful: " + booking);
        } catch (Exception e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    void printAll() {
        System.out.println("=== ALL ROOMS (Latest to Oldest) ===");
        ArrayList<Room> reversedRooms = new ArrayList<>(rooms);
        Collections.reverse(reversedRooms);
        for (Room room : reversedRooms) {
            System.out.println(room);
        }

        System.out.println("\n=== ALL BOOKINGS (Latest to Oldest) ===");
        ArrayList<Booking> reversedBookings = new ArrayList<>(bookings);
        Collections.reverse(reversedBookings);
        for (Booking booking : reversedBookings) {
            System.out.println(booking);
        }
    }

    void setUser(int userId, int balance) {
        User existingUser = findUserById(userId);
        if (existingUser != null) {
            existingUser.setBalance(balance);
        } else {
            users.add(new User(userId, balance));
        }
    }

    void printAllUsers() {
        System.out.println("=== ALL USERS (Latest to Oldest) ===");
        ArrayList<User> reversedUsers = new ArrayList<>(users);
        Collections.reverse(reversedUsers);
        for (User user : reversedUsers) {
            System.out.println(user);
        }
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private boolean isRoomAvailable(int roomNumber, Date checkIn, Date checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber() == roomNumber) {
                if (!(checkOut.before(booking.getCheckIn()) || checkIn.after(booking.getCheckOut()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
