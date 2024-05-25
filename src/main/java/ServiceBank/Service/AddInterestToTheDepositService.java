package ServiceBank.Service;

import ServiceBank.Model.BankAccount;
import ServiceBank.Repository.BankAccountRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AddInterestToTheDepositService {

    @Service
    @Transactional
    public class BankAccountLifeCycleService extends Thread {
        private static final Logger log= (Logger) LoggerFactory.getILoggerFactory();
        @Autowired
        private BankAccount bankAccount;
        private static final double percent =0.05;
        private static final double limitPresent=2.07;

        private static double limit;

        public double getLimitPresent(){
            double amount= bankAccount.getAmount();
            double limit=(amount+(amount*limitPresent));
            return this.limit=limit;
        }
        public synchronized double deposit(){
            double amount=bankAccount.getAmount();
            if (amount<limit){
                amount= (amount+(amount*percent));
                bankAccount.setAmount(amount);
                try {
                    System.out.println(bankAccount.getAmount());
                    Thread.sleep(600000);
                    deposit();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println(bankAccount.getAmount());
                log.log(Logger.getGlobal().getLevel(),"Привешен лимит начисления процента на депозит, начисление процента приостановлено");
                System.out.println("Привешен лимит начисления процента на депозит, начисление процента приостановлено");
                throw new RuntimeException("ошибка депо");
            }
            return amount;
        }

    }

}
