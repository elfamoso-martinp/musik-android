package fr.martinp.musik;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {

    public static boolean verifyMail(String email){
        if(email.length() > 5) {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    public static boolean verifyNames(String name){
        if (name.length() > 1){
            String regex = "^[\\p{L}\\s.’\\-,]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        }
        return false;
    }

    public static boolean verifyPostalCode(int postalCode){
        String regex = "(?:0[1-9]|[13-8][0-9]|2[ab1-9]|9[0-5])(?:[0-9]{3})?|9[78][1-9](?:[0-9]{2})?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Integer.toString(postalCode));
        return matcher.matches();
    }

    public static boolean verifyAddress(String address){
        if(address.length() > 3){
            return true;
        }
        return false;
    }

    public static boolean verifyPassword(String password){
            if(password.length() > 3){
                return true;
            }
            return false;

    }

}
