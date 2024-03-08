import java.io.*;
import java.util.*;
class Theatre {
    private static final int ROW1 = 12;                  //create the variables only once by using private static final.
    private static final int ROW2 = 16;                  //https://stackoverflow.com/questions/1415955/private-final-static-attribute-vs-private-final-attribute
    private static final int ROW3 = 20;
    private static final int[] row_1 = new int[ROW1];    //create arrays called row_1,row_2,row_3 and make them final because seats are not changing in the program.
    private static final int[] row_2 = new int[ROW2];
    private static final int[] row_3 = new int[ROW3];


    public static void main(String[] args) {
        System.out.format("%40s", "WELCOME TO THE NEW THEATRE\n\n");           //https://stackoverflow.com/questions/16629476/how-to-center-a-print-statement-text
        Scanner input = new Scanner(System.in);

        ArrayList<String> tickets_array = new ArrayList<>();                //create an array list called tickets_array


        for (int i = 0; i < ROW1; i++) {               //assigning 0 to each index of the array as when starts all seats should be availables
            row_1[i] = 0;
        }
        for (int i = 0; i < ROW2; i++) {
            row_2[i] = 0;
        }
        for (int i = 0; i < ROW3; i++) {
            row_3[i] = 0;
        }


        int option = -9;
        while (option != 0) {

            System.out.println("\n---------------------MENU----------------------------\n");
            System.out.println("Please select an option: ");
            System.out.println("1.) Buy a ticket");
            System.out.println("2.) Print seating area");
            System.out.println("3.) Cancel ticket");
            System.out.println("4.) List available seats");       //printing menu within while loop.so whenever getting a new option we can see the menu along it
            System.out.println("5.) Save to file");
            System.out.println("6.) Load from file");
            System.out.println("7.) Print ticket information and total price");
            System.out.println("8.) Sort tickets by price");
            System.out.println("    0.) Quit");
            System.out.println("------------------------------------------------------\n");

            try {

                System.out.print("Enter option: ");          //get number as an input of the option
                option = input.nextInt();

                switch (option) {
                    case 1 -> buy_ticket(tickets_array);                 //https://www.geeksforgeeks.org/enhancements-for-switch-statement-in-java-13/
                    case 2 -> print_seating_area();
                    case 3 -> cancel_ticket(tickets_array);
                    case 4 -> show_available();
                    case 5 -> save(row_1, row_2, row_3);
                    case 6 -> load();
                    case 7 -> show_tickets_info(tickets_array);
                    case 8 -> sort_tickets(tickets_array);
                    case 0 -> System.out.println("Quitting from NEW THEATRE....");
                    default -> System.out.println("Entered option number is out of range.Please try again!!\n");       //validation for out of range in option
                }

            } catch (InputMismatchException e) {
                System.out.println("Your entered option is not a number.please enter the valid option number.\n");     //validate type the option which user enter(number or not)
                String junk = input.nextLine();
                continue;
            }

        }
    }




    private static void buy_ticket(ArrayList<String> tickets_array) {                                  //passing array list to buy_ticket
        Scanner input = new Scanner(System.in);                                                        //https://www.programiz.com/java-programming/examples/pass-arraylist-as-function-argument
        System.out.println("\n-----------------BUY TICKETS---------------------------\n");

        String choice = "yes";
        while (true) {

            if(choice.equalsIgnoreCase("yes")){
                try {

                    System.out.print("Enter your name : ");
                    String name = input.next();

                    boolean name_check = true;
                    for (int i = 0; i < name.length(); i++) {                             //https://www.tutorialspoint.com/how-can-a-string-be-validated-for-alphabets-in-java#:~:text=To%20validate%20a%20string%20for,)%20or%2C%20use%20regular%20expressions.
                        char ch = name.charAt(i);                                         //check the name in alphabetical
                        if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
                            name_check = false;
                        }
                    }
                    if (name_check) {
                        System.out.print("Enter your surname : ");
                        String surname = input.next();

                        boolean surname_check = true;
                        for (int i = 0; i < surname.length(); i++) {
                            char ch = surname.charAt(i);                                            //using charAt(i) check whether it's string one by one
                            if (!(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')) {
                                surname_check = false;
                            }
                        }
                        if (surname_check) {

                            System.out.print("Enter your email : ");
                            String email = input.next();                                           //validate the email. at least has "@" and "."
                            if (email.contains("@") && email.contains(".")) {                     //https://www.javatpoint.com/java-email-validation

                                double price;
                                System.out.print("Enter the row number which you prefer to reserve your seat(1-3): ");
                                int row = input.nextInt();

                                int seat;
                                if (row >= 1 && row <= 3) {                                        //check the row number is within the range
                                    if (row == 1) {
                                        try {                                         //validating the seat number(type)
                                            System.out.print("Enter the seat number you prefer to reserve(1-12): ");
                                            seat = input.nextInt();
                                            if (seat >= 1 && seat <= 12) {          //checking the seat range

                                                try{                                //checking the price of the tickets
                                                    System.out.print("Enter the ticket price: \u20AC");
                                                    price = input.nextDouble();
                                                    System.out.println();
                                                    if(price>0 && price<=20){        //check the price range(between 0, 20 euros)
                                                        Ticket myTicket = new Ticket(row, seat, name, surname, email, price);     //add row,seat,name,surname,email and price myticket

                                                        seat = seat - 1;           //substract 1 from the seat because we going to call for arrays(index is starting from 0)

                                                        if (row_1[seat] == 0) {     //if row_1[seat] equals to 0 means the seat is available.book the ticket and equals it to 0.
                                                            System.out.print("You purchased the ticket successfully for the seat " + (seat + 1) + " in row " + row + ". Enjoy the movie!!!\n");
                                                            row_1[seat] = 1;

                                                            tickets_array.add(myTicket.person.getName());                  //add the details of the above object to the array one by one
                                                            tickets_array.add(myTicket.person.getSurname());
                                                            tickets_array.add(myTicket.person.getEmail());
                                                            tickets_array.add(String.valueOf(myTicket.getRow()));
                                                            tickets_array.add(String.valueOf(myTicket.getSeat()));
                                                            tickets_array.add(String.valueOf(myTicket.getPrice()));

                                                        } else {
                                                            System.out.print("The seat is already reserved.Try another one.\n");
                                                        }

                                                    }else{
                                                        System.out.println("Price of the ticket is out of range.\n");
                                                    }

                                                }catch (Exception e){
                                                    System.out.println("Invalid ticket price.Recheck again.");
                                                    String junk = input.nextLine();
                                                }

                                            } else {
                                                System.out.println("The seat number is out of range.Row 1 has only 12 seats.Try again.\n");
                                            }
                                        }
                                        catch (Exception e){
                                            System.out.println("Given input is not a number.Please enter the seat number you want to reserve.\n");
                                            String junk = input.nextLine();

                                        }