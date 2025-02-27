package models;

public class Admin extends User {
    public Admin(String username, String password, String name, String lastname, int age, String gender) {
        super(username, password, name, lastname, age, gender);
    }
}