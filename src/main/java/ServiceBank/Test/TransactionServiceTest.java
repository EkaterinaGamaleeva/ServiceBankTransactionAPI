package ServiceBank.Test;


import ServiceBank.Model.BankAccount;
import ServiceBank.Model.ClientAccount;
import ServiceBank.Repository.ClientAccountRepositiry;
import ServiceBank.Service.TransactionService;
import org.junit.Test;

import static org.junit.Assert.*;

class TransactionServiceTest {

    BankAccount bankAccount = new BankAccount(10);
    BankAccount bankAccount2=new BankAccount(100);
    TransactionService transactionService= new TransactionService();

ClientAccount clientAccount=new ClientAccount("89959898265","kkjshhbcs@gmail.com","KatyaGamaleeva","10.03.2000","asjcnakscn","ascsz",bankAccount);
    ClientAccount transactionClientAccount=new ClientAccount("89901239770","kkjcCAcAc@gmail.com","DenisGamaleeva","16.05.2000","asjcnaksCszccn","ascszscz",bankAccount2);;
    @Test
    void takeOffDeposit() {

    transactionService.takeOffDeposit(10,clientAccount.getIdClient());
    assertFalse(false);
    transactionService.takeOffDeposit(10,transactionClientAccount.getIdClient());
    assertTrue(true);
   double amount= clientAccount.getBankAccount().getAmount();
   double check=90;
   assertEquals(check,amount);
    }

@Test
    void transferDeposit() {
    transactionService.transferDeposit(transactionClientAccount.getIdClient(),clientAccount.getIdClient(),100);
    assertFalse(false);
    transactionService.transferDeposit(transactionClientAccount.getIdClient(),clientAccount.getIdClient(),5);

    double amountClient= clientAccount.getBankAccount().getAmount();
    double amountTransactionClient= clientAccount.getBankAccount().getAmount();
    double checkClient=5;
    double checkTransactionClient=105;
    assertEquals(checkClient,amountClient);
    assertEquals(checkTransactionClient,amountTransactionClient);
    }

@Test
    void addDeposit() {
        transactionService.addDeposit(clientAccount.getIdClient(),100);
    double amountClient= clientAccount.getBankAccount().getAmount();;
    double checkClient=110;
    assertEquals(checkClient,amountClient);
    transactionService.addDeposit(clientAccount.getIdClient(),-100);
    assertFalse(false);
    }
}