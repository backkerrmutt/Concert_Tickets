/**
 *
 * @author Anuphong_PC
 */
package models;
public class User {
    protected  String username, password;
    public String name;
    protected String lastname;
    protected String gender;
    protected int age;

    public User(String username, String password, String name, String lastname, int age, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

   
}
