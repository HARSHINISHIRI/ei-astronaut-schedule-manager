import java.util.*;

public class AstronautScheduleManager {
    private static final Map<String, List<String>> schedules = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nAstronaut Schedule Manager");
            System.out.println("1. Add new event");
            System.out.println("2. View schedule");
            System.out.println("3. Edit event");
            System.out.println("4. Delete event");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addEvent();
                    break;
                case "2":
                    viewSchedule();
                    break;
                case "3":
                    editEvent();
                    break;
                case "4":
                    deleteEvent();
                    break;
                case "5":
                    System.out.println("Exiting the scheduler. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addEvent() {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter the event description: ");
        String event = scanner.nextLine();

        schedules.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
        System.out.println("Event added to " + date + ": " + event);
    }

    private static void viewSchedule() {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        List<String> events = schedules.get(date);
        if (events == null || events.isEmpty()) {
            System.out.println("No events scheduled for " + date + ".");
        } else {
            System.out.println("Schedule for " + date + ":");
            for (int i = 0; i < events.size(); i++) {
                System.out.println((i + 1) + ". " + events.get(i));
            }
        }
    }

    private static void editEvent() {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        viewSchedule();
        
        List<String> events = schedules.get(date);
        if (events != null && !events.isEmpty()) {
            System.out.print("Enter the event number to edit: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < events.size()) {
                System.out.print("Enter the new event description: ");
                String newEvent = scanner.nextLine();
                String oldEvent = events.set(index, newEvent);
                System.out.println("Event updated from '" + oldEvent + "' to '" + newEvent + "' on " + date + ".");
            } else {
                System.out.println("Invalid event number. Event not found.");
            }
        }
    }

    private static void deleteEvent() {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        viewSchedule();

        List<String> events = schedules.get(date);
        if (events != null && !events.isEmpty()) {
            System.out.print("Enter the event number to delete: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < events.size()) {
                String removedEvent = events.remove(index);
                System.out.println("Event '" + removedEvent + "' removed from " + date + ".");
                if (events.isEmpty()) {
                    schedules.remove(date);
                }
            } else {
                System.out.println("Invalid event number. Event not found.");
            }
        }
    }
}