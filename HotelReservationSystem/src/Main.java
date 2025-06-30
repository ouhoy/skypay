import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        // Create 3 rooms
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        // Create 2 users
        service.setUser(1, 5000);
        service.setUser(2, 10000);

        // Create dates
        Calendar cal = Calendar.getInstance();
        
        // 30/06/2026
        cal.set(2026, Calendar.JUNE, 30);
        Date date1 = cal.getTime();
        
        // 07/07/2026
        cal.set(2026, Calendar.JULY, 7);
        Date date2 = cal.getTime();
        
        // 08/07/2026
        cal.set(2026, Calendar.JULY, 8);
        Date date3 = cal.getTime();
        
        // 09/07/2026
        cal.set(2026, Calendar.JULY, 9);
        Date date4 = cal.getTime();

        // Test bookings
        System.out.println("=== BOOKING ATTEMPTS ===");
        service.bookRoom(1, 2, date1, date2); // User 1 books Room 2 (7 nights)
        service.bookRoom(1, 2, date2, date1); // Invalid date range
        service.bookRoom(1, 1, date2, date3); // User 1 books Room 1 (1 night)
        service.bookRoom(2, 1, date2, date4); // User 2 tries Room 1 (conflict)
        service.bookRoom(2, 3, date2, date3); // User 2 books Room 3 (1 night)

        // Update room 1
        service.setRoom(1, RoomType.SUITE, 10000);

        System.out.println("\n=== FINAL RESULTS ===");
        service.printAll();
        System.out.println();
        service.printAllUsers();
    }
}