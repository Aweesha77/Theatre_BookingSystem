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