package ServiceBank.SecuritiService;

import ServiceBank.Model.ClientAccount;
import ServiceBank.Repository.ClientAccountRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RegistrationService {
    private final ClientAccountRepositiry clientAccountRepositiry;
    private final PasswordEncoder passwordEncoder;
@Autowired
public RegistrationService(ClientAccountRepositiry clientAccountRepositiry, PasswordEncoder passwordEncoder) {
        this.clientAccountRepositiry=clientAccountRepositiry;
        this.passwordEncoder = passwordEncoder;
        }

@Transactional
public void register(ClientAccount clientAccount) {
        clientAccount.setPass(passwordEncoder.encode(clientAccount.getPass()));
        clientAccount.setRole("ROLE_USER");
        clientAccountRepositiry.save(clientAccount);
        }
}