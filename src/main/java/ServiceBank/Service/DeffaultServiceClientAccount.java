package ServiceBank.Service;

import ServiceBank.Model.ClientAccount;
import ServiceBank.Repository.ClientAccountRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeffaultServiceClientAccount{
    private final ClientAccountRepositiry clientAccountRepositiry;

    @Autowired
        public DeffaultServiceClientAccount(ClientAccountRepositiry clientAccountRepositiry) {
            this.clientAccountRepositiry = clientAccountRepositiry;
        }

        public List<ClientAccount> findAll() {
        return clientAccountRepositiry.findAll();
    }

    public ClientAccount findOne(int id) {
        Optional<ClientAccount> foundPerson = clientAccountRepositiry.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(ClientAccount clientAccount) {
        clientAccountRepositiry.save(clientAccount);
    }

    @Transactional
    public void update(int id, ClientAccount updatedClient) {
        updatedClient.setIdClient(id);
        clientAccountRepositiry
                .save(updatedClient);
    }

    @Transactional
    public void delete(int id) {
        clientAccountRepositiry.deleteById(id);
    }
}

