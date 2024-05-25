package ServiceBank.Controllers;

import ServiceBank.DAO.PersonValidator;
import ServiceBank.DTO.AutentificationDTO;
import ServiceBank.DTO.ClientDTO;
import ServiceBank.Model.ClientAccount;
import ServiceBank.SecuritiService.RegistrationService;
import ServiceBank.Security.Jwt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final Jwt jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    @Autowired
    public RegistrationController(RegistrationService registrationService, PersonValidator personValidator,
                                  Jwt jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid ClientDTO clientDTO,
                                                   BindingResult bindingResult) {
        ClientAccount clientAccount = convertToPerson(clientDTO);

        personValidator.validate(clientAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "Ошибка!");
        }

        registrationService.register(clientAccount);

        String token = jwtUtil.generateToken(clientAccount.getName());
        return Map.of("jwt-token", token);
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

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    public ClientAccount convertToPerson(ClientDTO clientDTO) {
        return this.modelMapper.map((Object) clientDTO, (Type) ClientDTO.class);
    }
}
