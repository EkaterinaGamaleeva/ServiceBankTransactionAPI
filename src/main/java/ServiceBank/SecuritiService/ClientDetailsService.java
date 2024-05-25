package ServiceBank.SecuritiService;

import ServiceBank.Model.ClientAccount;
import ServiceBank.Repository.ClientAccountRepositiry;
import ServiceBank.Security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDetailsService implements UserDetailsService {

    private final ClientAccountRepositiry clientAccountRepositiry;

    @Autowired
    public ClientDetailsService(ClientAccountRepositiry clientAccountRepositiry) {
        this.clientAccountRepositiry = clientAccountRepositiry;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<ClientAccount> clientAccountOptional = clientAccountRepositiry.findByUsername(s);

        if (clientAccountOptional.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new ClientDetails(clientAccountOptional.get());
    }
}
