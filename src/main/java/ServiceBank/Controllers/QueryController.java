package ServiceBank.Controllers;

import ServiceBank.DAO.PersonValidator;
import ServiceBank.DTO.AutentificationDTO;
import ServiceBank.DTO.ClientDTO;
import ServiceBank.Model.ClientAccount;
import ServiceBank.Repository.ClientAccountRepositiry;
import ServiceBank.Security.Jwt;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userAccount")
public class QueryController {
    private final AuthenticationManager authenticationManager;
    private final  PersonValidator personValidator;
    private final Jwt jwt;
    private final ClientAccountRepositiry clientAccountRepositiry;


    public QueryController(AuthenticationManager authenticationManager, PersonValidator personValidator, Jwt jwt, ClientAccountRepositiry clientAccountRepositiry) {
        this.authenticationManager = authenticationManager;
        this.personValidator = personValidator;
        this.jwt = jwt;
        this.clientAccountRepositiry = clientAccountRepositiry;
    }
    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AutentificationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwt.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }
    @GetMapping("{number}")
   public ClientAccount findByOrNumbers(@RequestBody @Valid ClientDTO clientDTO ,String number){
       return clientAccountRepositiry.findByOrNumbers(number);
    }

    @GetMapping("{email}")
    public ClientAccount findByOrEmails(@RequestBody @Valid ClientDTO clientDT,String email){
        return clientAccountRepositiry.findByOrEmails(email);
    }

    @GetMapping("{data}")
    public List<ClientAccount> findByDataGreaterThan(@RequestBody @Valid ClientDTO clientDTO,Date date)
    {
        return clientAccountRepositiry.findByDataGreaterThan(date);
    }
    @GetMapping("{name/param}")
    public List<ClientAccount> findByNameLike(@RequestBody @Valid ClientDTO clientDTO,String param)
    {
    return     clientAccountRepositiry.findByNameLike(param);
    }
}
