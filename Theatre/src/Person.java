public class Person {
    private String name;               //https://www.tutorialspoint.com/java/java_object_classes.htm
    private String surname;
    private String email;
    private double price;


    public Person(String name, String surname, String email,double price) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.price=price;
    }

    public String getName() {
        return name;
    }                                                        //https://www.geeksforgeeks.org/classes-objects-java/
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPrice() {
        return name;
    }
    public String setPrice() {
        return name;
    }





}