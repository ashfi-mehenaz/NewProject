
package Logic;

public class Verification {

    String username = "Mr.XXX";
    String password = "08090605";

    public boolean checkLogin(String login, String pass) {
        if (login.equals(username) && pass.equals(password)) {
            return true;
        } else {
            System.out.println(username + " != " + login + " | " + password + " != " + pass);
            return false;
        }

    }

}