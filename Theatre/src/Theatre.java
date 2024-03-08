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
                                    } else if (row == 2) {
                                        try {
                                            System.out.print("Enter the seat number you prefer to reserve(1-16): ");
                                            seat = input.nextInt();
                                            if (seat >= 1 && seat <= 16) {            //check the seat is in the range
                                                try {
                                                    System.out.print("Enter the ticket price: \u20AC");
                                                    price = input.nextDouble();
                                                    if(price>0 && price<=20){
                                                        Ticket myTicket = new Ticket(row, seat, name, surname, email, price);

                                                        seat = seat - 1;
                                                        if (row_2[seat] == 0) {
                                                            System.out.print("You purchased the ticket successfully for the seat " + (seat + 1) + " in row " + row + ". Enjoy the movie!!!\n");
                                                            row_2[seat] = 1;

                                                            tickets_array.add(myTicket.person.getName());                    //https://www.javatpoint.com/add-elements-to-array-in-java#:~:text=(newArr))%3B%20%7D%20%7D-,Using%20ArrayList,using%20the%20toArray()%20method.
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


                                                }catch (Exception e){                        //https://www.w3schools.com/java/java_try_catch.asp
                                                    System.out.println("Invalid ticket price.Recheck again.");
                                                    String junk = input.nextLine();
                                                }
                                            } else {
                                                System.out.println("The seat number is out of range.Row 2 has only 16 seats.Try again.\n");     //without in the seat range pop out this
                                            }
                                        }
                                        catch (Exception e){
                                            System.out.println("Given input is not a number.Please enter the seat number you want to reserve.\n");
                                            String junk = input.nextLine();

                                        }

                                    } else {

                                        try {
                                            System.out.print("Enter the seat number you prefer to reserve(1-20): ");
                                            seat = input.nextInt();


                                            if (seat >= 1 && seat <= 20) {
                                                try {
                                                    System.out.print("Enter the ticket price: \u20AC");
                                                    price = input.nextDouble();
                                                    if(price>0 && price<=20){
                                                        Ticket myTicket = new Ticket(row, seat, name, surname, email, price);

                                                        seat = seat - 1;

                                                        if (row_3[seat] == 0) {
                                                            System.out.println("You purchased the ticket successfully for the seat "+(seat+1)+" in row "+row+". Enjoy the movie!!!\n");
                                                            row_3[seat] = 1;

                                                            tickets_array.add(myTicket.person.getName());
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


                                                }catch (Exception e) {
                                                    System.out.println("Invalid ticket price.Recheck again.");
                                                    String junk = input.nextLine();
                                                }

                                            } else {
                                                System.out.println("The seat number is out of range.Row 3 has only 20 seats.Try again.\n");
                                            }

                                        }
                                        catch (Exception e){
                                            System.out.println("Given input is not a number.Please enter the seat number you want to reserve.\n");
                                            String junk = input.nextLine();

                                        }

                                    }
                                } else {
                                    System.out.println("The row number is out of range,please check your row number and try again.\n");
                                }
                            } else {
                                System.out.println("Given email is not a proper email.Please check your email and try it again.");
                            }
                        } else {
                            System.out.println("Given surname is not a proper string.Please check your surname and try it again.");
                        }

                    } else {
                        System.out.println("Given name is not a proper string.Please check your name and try it again.");

                    }

                } catch (Exception e) {
                    System.out.println("Given input is not a number.Please enter the row number of the seat you want to reserve.\n");
                    String junk = input.nextLine();
                }

            }

            System.out.println("\n");
            System.out.print("Do you want to reserve another seat(yes/no): ");            //if choice input is equals to "yes" again pop out name,if it "no" breaks the loop and prints the menu.if it is another input prints something went wrong message.
            choice = input.next();
            if (choice.equalsIgnoreCase("no")) {
                break;
            }else if(choice.equalsIgnoreCase("yes")){
                continue;

            }else{
                System.out.println("Something went wrong.Enter \"yes\" to continue and \"no\" to return to the main menu \n");
            }
        }
    }
    

    private static void print_seating_area() {
        System.out.println("\n----------SEATING AREA--------------------------------\n");

        System.out.println("\n");
        System.out.format("%25s", "* * * * * * * *\n");
        System.out.format("%25s", "     STAGE     \n");                   //https://stackoverflow.com/questions/16629476/how-to-center-a-print-statement-text
        System.out.format("%25s", "* * * * * * * *\n");                   //ude print format to make it center
        System.out.println("\n");

        System.out.format("%10s", " ");
        for (int i = 0; i <= ROW1 - 1; i++) {

            if (row_1[i] == 0) {
                System.out.print("O");                                    //if i=0,prints 0 and i=1,prints X
            } else {
                System.out.print("X");
            }
            if (i == 5) {                                                 //to print the space
                System.out.print(" ");

            }
        }

        System.out.println("\n");
        System.out.format("%8s", " ");
        for (int i = 0; i <= ROW2 - 1; i++) {
            if (row_2[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if (i == 7) {
                System.out.print(" ");

            }
        }

        System.out.println("\n");
        System.out.format("%6s", " ");
        for (int i = 0; i <= ROW3 - 1; i++) {
            if (row_3[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if (i == 9) {
                System.out.print(" ");

            }
        }
        System.out.println("\n");

    }



    private static void cancel_ticket(ArrayList<String> ticket_array) {
        Scanner input = new Scanner(System.in);                                                //opening a Scanner library to store inputs
        System.out.println("\n-----------------CANCEL TICKETS-----------------------\n");
        while (true) {
            System.out.print("Enter your email which you entered when you reserved the seat: ");

            String email_cancel = input.next();
            boolean validate = false;
            if (validate == false) {
                for (int k = 0; k < ticket_array.size(); k += 6) {                          //checking the user input email is includes in the ticket array
                    if ((ticket_array.get(k + 2).equals(email_cancel))) {
                        validate = true;
                    }

                }
            }

            if (validate == false) {
                System.out.println("The entered email is invalid.Please try again\n");      //if user input email is not in the array prints invalid email
                System.out.print("Do you want to cancel seat(yes/no): ");
                String choice = input.next();       //ask
                if (choice.equalsIgnoreCase("yes")) {                          //if user input something except yes or no to choice it will direct to main menu
                    continue;
                } else {
                    System.out.println("Invalid input.Again to the Main Menu\n");
                    break;

                }
            }

            try{

                if (validate == true) {                           //if user input email in the array execute the below code

                    System.out.print("Enter row number(1-3): ");
                    int row = input.nextInt();
                    if (row > 0 && row < 4) {

                        if (row == 1) {
                            try{
                                System.out.print("Enter seat number(1-12): ");
                                int seat = input.nextInt();
                                if (seat >= 1 && seat <= 12) {
                                    if (row_1[seat - 1] == 1) {


                                        for (int i = 0; i < ticket_array.size(); i += 6) {

                                            if ((ticket_array.get(i + 2).equals(email_cancel))){     //check the user input email is correct
                                                if (Objects.equals(ticket_array.get(i + 3), String.valueOf(row)) && Objects.equals(ticket_array.get(i + 4), String.valueOf(seat)) ){    //check the row and seat number is correct under this email
                                                    for (int j = 6; j > 0; j--) {
                                                        ticket_array.remove(i);                  //to remove from the arraylist

                                                    }
                                                        System.out.println("Cancelled the Ticket successfully in row "+row+" seat "+seat+".\n");
                                                        row_1[seat - 1] = 0;                     //if we remove the elements make seats available
                                                }
                                                else{
                                                    System.out.println("This seat is not reserved under this email\n");
                                                }

                                            }
                                                //https://examples.javacodegeeks.com/java-not-equal-example/
                                        }
                                    } else {
                                        System.out.println("Check the seat and row number again.This seat is not reserved\n");
                                    }
                                } else {
                                    System.out.println("The seat number is out of range.Row 1 has only 12 seats.Try again.\n");
                                }
                            }
                            catch (Exception e){
                                System.out.println("Given input is not a number.Please enter the seat number you want to cancel.\n");
                                String junk = input.nextLine();

                            }


                        } else if (row == 2) {
                            try{
                                System.out.print("Enter seat number(1-16): ");
                                int seat = input.nextInt();
                                if (seat >= 1 && seat <= 16) {
                                    if (row_2[seat - 1] == 1) {


                                        //to remove from the arraylist
                                        for (int i = 0; i < ticket_array.size(); i += 6) {
                                            if ((ticket_array.get(i + 2).equals(email_cancel))){
                                                if (Objects.equals(ticket_array.get(i + 3), String.valueOf(row)) && Objects.equals(ticket_array.get(i + 4), String.valueOf(seat)) ){
                                                    for (int j = 6; j > 0; j--) {
                                                        ticket_array.remove(i);

                                                    }
                                                    System.out.println("Cancelled the Ticket successfully in row "+row+" seat "+seat+".\n");
                                                    row_1[seat - 1] = 0;
                                                }
                                                else{
                                                    System.out.println("This seat is not reserved under this email\n");
                                                }

                                            }
                                        }


                                    } else {
                                        System.out.println("Check the seat and row number again.This seat is not reserved\n");
                                    }
                                } else {
                                    System.out.println("The seat number is out of range.Row 2 has only 16 seats.Try again.\n");
                                }
                            }
                            catch (Exception e){
                                System.out.println("Given input is not a number.Please enter the seat number you want to cancel.\n");
                                String junk = input.nextLine();

                            }
