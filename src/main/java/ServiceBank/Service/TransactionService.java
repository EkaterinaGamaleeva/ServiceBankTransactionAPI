package ServiceBank.Service;

import ServiceBank.Repository.ClientAccountRepositiry;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionService extends Thread {
    public static final ILoggerFactory log=LoggerFactory.getILoggerFactory();
    @Autowired
    public ClientAccountRepositiry clientAccountRepositiry;



    public synchronized boolean takeOffDeposit(double amount, int id) {
        double deposit= clientAccountRepositiry.findById(id).get().getBankAccount().getAmount();
    if ((deposit-amount)<=0){
        log.getLogger("Ошибка").debug("Операция приостановлена, не хватает средст на счете");
        System.out.println("Операция приостановлена, не хватает средст на счете");
        return false;
    }
        clientAccountRepositiry.findById(id).get().getBankAccount().setAmount(deposit-amount);
    log.getLogger("Вы успешно сняли деньги");
        System.out.println("Вы успешно сняли деньги");
        return true;
    }

    public synchronized boolean transferDeposit(int transferClientId, int clientId,double amount) {
        double deposit= clientAccountRepositiry.findById(clientId).get().getBankAccount().getAmount();
        double transferDeposit = clientAccountRepositiry.findById(transferClientId).get().getBankAccount().getAmount();
        if ((deposit-amount)<=0){
            log.getLogger("Ошибка").debug("Операция приостановлена");
            System.out.println("Операция приостановлена");
            return false;
        }
        clientAccountRepositiry.findById(transferClientId).get().getBankAccount().setAmount(deposit+amount);
        clientAccountRepositiry.findById(clientId).get().getBankAccount().setAmount(deposit-amount);
        log.getLogger("Перевод выполнин успешно");
        System.out.println("Перевод выполнин успешно");
        return true;
    }

    public synchronized boolean addDeposit(int id,double amount) {
        if (amount<0){
            log.getLogger("Errors").debug("НЕ верно указанны сумма пополнения");
            throw new IllegalArgumentException("НЕ верно указанны сумма пополнения");
        }
    double deposit= clientAccountRepositiry.findById(id).get().getBankAccount().getAmount();
        clientAccountRepositiry.findById(id).get().getBankAccount().setAmount(deposit+amount);
        log.getLogger("Депозит пополнен");
        System.out.println("Депозит пополнен");
    return true;
    }
}