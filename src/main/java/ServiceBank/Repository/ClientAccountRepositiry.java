package ServiceBank.Repository;

import ServiceBank.Model.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ClientAccountRepositiry extends JpaRepository <ClientAccount,Integer>{
ClientAccount findByOrNumbers(String number);
    ClientAccount findByOrEmails(String email);
    List<ClientAccount> findByDataGreaterThan(Date date);
    List<ClientAccount> findByNameLike(String param);
    Optional<ClientAccount> findByUsername(String n);
}
