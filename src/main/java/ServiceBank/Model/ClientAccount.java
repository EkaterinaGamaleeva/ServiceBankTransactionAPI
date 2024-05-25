package ServiceBank.Model;

import ServiceBank.DAO.CheckDataClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ClientAccount")
public class ClientAccount {
    @Id
    @Column(name = "id_client")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClient;
    @NotEmpty
    @Column(name = "name")
    private String name;
    @NotEmpty
    @Column(name = "date")
    private String data;
    @Column(name = "login")
    private String login;
    @NotEmpty
    @Column(name = "phone")
    private List<String> numders;
    @NotEmpty
    @Column(name = "emails")
    private List<String> emails;
    @Column(name = "pass")
    private  String pass;
    @Column(name = "role")
    private String role;
    @NotEmpty
    @OneToOne(mappedBy = "idBankAccount;")
    private BankAccount bankAccount;
    @Autowired
private CheckDataClient checkDataClient;
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<String> getNumders() {
        return numders;
    }

    public void setNumders(List<String> numders) {
        this.numders = numders;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public ClientAccount(String number, String email, String name, String data, String login, String pass, BankAccount bankAccount) {
        this.name = name;
        this.data = data;
        this.login = login;
        emails.add(email);
        numders.add(number);
        this.pass = pass;
        this.bankAccount = bankAccount;
        this.login = login;
        if (bankAccount.getAmount() <= 0) {
            throw new IllegalArgumentException("Пополните счет и попробуйте снова");

        }
        if (checkDataClient.checkNumber(number) == false) {
            throw new IllegalArgumentException("Такой номер уже существует");
            }
        if (checkDataClient.checkEmail(email) == false) {
            throw new IllegalArgumentException("Такой Email уже существует");
        }
        if (checkDataClient.checkLogin(login) == false) {
            throw new IllegalArgumentException("Такой Логин уже существует");
        }
        System.out.println("Регестрация клиента прошла успешно");
    }

}