package ServiceBank.DAO;

import ServiceBank.Model.ClientAccount;
import ServiceBank.SecuritiService.ClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.validation.Validator;

@Component
public abstract class PersonValidator implements Validator {

    private final ClientDetailsService clientDetailsService;


@Autowired
    public PersonValidator(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public boolean supports(Class<?> aClass) {
        return ClientAccount.class.equals(aClass);
    }

    public void validate(Object o, BindingResult errors) {
        ClientAccount clientAccount = (ClientAccount) o;

        try {
            clientDetailsService.loadUserByUsername(clientAccount.getName());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

          errors.getFieldError("username");
    }
}