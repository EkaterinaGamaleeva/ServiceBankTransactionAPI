package ServiceBank.DTO;

import ServiceBank.Model.BankAccount;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class ClientDTO {
        @NotEmpty(message = "Имя не должно быть пустым")
        @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
        private String username;
    @NotEmpty

        private int yearOfBirth;

        private String password;
 @NotEmpty
    private List<String> numbers;
    @NotEmpty
    private List<String>  emails;
    @NotEmpty
    @Min(value = 1)
    private BankAccount bankAccount;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public void setYearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

