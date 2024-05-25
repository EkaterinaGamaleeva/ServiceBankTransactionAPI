package ServiceBank.DAO;

import ServiceBank.Model.ClientAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class CheckDataClient {
    private ClientAccount client;
    private static List<String> phoneBook=new ArrayList<>();

    private static List<String> emailBook=new ArrayList<>();;

    private static List<String> loginBook=new ArrayList<>();;
    private static final Pattern patternNumber = Pattern.compile("^\\+?[1-9][0-9]{7,14}$");
    private static final Pattern patternEmail = Pattern.compile("^(.+)@(\\S+)$");

    public CheckDataClient(ClientAccount client) {
        this.client = client;
        for (int i = 0; i < client.getNumders().size(); i++) {
            phoneBook.add(client.getNumders().get(i));
        }
        for (int i = 0; i < client.getEmails().size(); i++) {
            emailBook.add(client.getEmails().get(i));
        }

    }

    public boolean checkNumber(String number) {
        Matcher matcherNumber = patternNumber.matcher(number);
        if (matcherNumber.matches()==false) {
            System.out.println("Номер введен не корректно");
            return false;
        }
        if (phoneBook.isEmpty()) {
        phoneBook.add(number);
        return true;
        }
            for (int i = 0; i < phoneBook.size(); i++) {
                if (phoneBook.contains(number)) {
                    return false;
                }
            }
            phoneBook.add(number);
            return true;
    }

    public boolean checkEmail(String email) {
        Matcher matcherEmail = patternEmail.matcher(email);
       if (matcherEmail.matches()==false) {
           System.out.println("Email введен не корректно");
           return false;
       }
           if (emailBook.isEmpty()) {
               emailBook.add(email);
               return true;
           }
           for (int i = 0; i < emailBook.size(); i++) {
               if (emailBook.contains(email)) {
                   return false;
               }
           }
           emailBook.add(email);
           return true;
    }

    public boolean checkLogin(String login) {
        if (loginBook.isEmpty()){
            loginBook.add(login);
            return  true;
        }
        for (int i = 0; i < loginBook.size(); i++) {
            if (loginBook.contains(login)) {
                return false;
            }
        }
        loginBook.add(login);
        return true;
    }
}
