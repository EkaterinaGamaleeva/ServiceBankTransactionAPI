package ServiceBank.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BankAccount")
public class BankAccount {
    @Id
    @OneToOne
    @JoinColumn(name = "bank_account_id",referencedColumnName = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBankAccount;
    @NotEmpty
    @Min(value = 1,message = "Пополните счет")
    @Column(name = "amount")
    private double amount;

    public BankAccount(double amount) {
        this.amount = amount;
    }
}
