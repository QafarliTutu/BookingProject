import controller.BookingController;
import controller.FlightController;
import controller.UserController;
import menu.MenuItem;
import menu.MenuOperation;
import model.City;
import model.Flight;
import model.Session;
import model.User;


import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


public class BookinApp2 {
    private FlightController flightController = new FlightController();
    private BookingController bookingController = new BookingController();
    private UserController userController = new UserController();



    void menu2() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("==================================================\n");
        sb2.append("|                       MENU                     |\n");
        sb2.append("==================================================\n");
        sb2.append("|   1. Booking.                                  |\n");
        sb2.append("|   2. Return main menu         .                |\n");
        sb2.append("==================================================\n");
        System.out.println(sb2);
    }



    public void run()  {

        Scanner scanner = new Scanner(System.in);
        boolean flag0 = true;
        while (flag0) {
            boolean flag1 = false;
            MenuItem.showMenu0();
            String menu0item = scanner.next();

            switch (menu0item) {
                case "1":
                    System.out.println("Username:");
                    String username = scanner.next();
                    System.out.println("Password:");
                    String password = scanner.next();
                    try {
                        userController.getUser(new User(username, password));
                        flag1 = true;
                    } catch (Exception e) {
                        System.out.println("User doesn't exist, please sign up!");
                        flag1 = false;
                    }
                    break;
                case "2":
                    System.out.println("Username:");
                    String usernamenew = scanner.next();
                    System.out.println("Password:");
                    String passwordnew = scanner.next();
                    userController.creatNewUser(new User(usernamenew, passwordnew));
                    break;
                case "3":
                    System.out.println("See you");
                    flag0 = false;
                    break;
                default:
                    System.out.println("Invalid menu item. Enter menu item.");
                    break;

            }


            while (flag1) {
               MenuItem.showMenu1();
                String menuItem = scanner.next();
                switch (menuItem) {

                    case "1":
                        flightController.getAll();
                        break;
                    case "2":
                        System.out.print("Enter ID of flight: ");
                        int id = scanner.nextInt();
                        flightController.getById(id);
                        break;
                    case "3":
                        boolean flag2 = true;
                        int tickets = 0;
                        try {
                            System.out.println("Enter destination city: ");
                            String city = scanner.next().toUpperCase();

                            System.out.println("Enter date(like YYYY-MM-DD:");
                            LocalDate date = LocalDate.parse(scanner.next());

                            System.out.println("Enter number of tickets: ");
                            tickets = scanner.nextInt();
                            flightController.search(new Flight(City.valueOf(city), date));
                        } catch (InputMismatchException im) {
                            System.out.println("Something went wrong");
                            flag2 = false;
                        } catch (Exception ex) {
                            System.out.println("Date format is not true!");//nese duzeldecekdim yadimdan cixdi((((((
                            flag2 = false;
                        }

                        while (flag2) {
                            menu2();
                            String press = scanner.next();
                            switch (press) {
                                case "1":
                                    bookingController.makeBooking(tickets);
                                    break;
                                case "2":
                                    flag2 = false;
                                    break;
                                default:
                                    System.out.println("Invalid menu item. Enter menu item.");
                                    break;
                            }
                        }
                        break;
                    case "4":
                        System.out.println("Enter Booking ID: ");
                        int cancelID = scanner.nextInt();
                        bookingController.cancelBooking(cancelID);
                        break;
                    case "5":
                        bookingController.showMyBookings();
                        break;
                    case "6":
                        Session.setUser(null);
                        flag1 = false;
                        break;

                    default:
                        System.out.println("Invalid menu item!");
                        break;
                }
            }
        }
    }
}