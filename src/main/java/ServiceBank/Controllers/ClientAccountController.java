package ServiceBank.Controllers;

import ServiceBank.Model.ClientAccount;
import ServiceBank.SecuritiService.AdminService;
import ServiceBank.Security.ClientDetails;
import ServiceBank.Service.DeffaultServiceClientAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/client")
public class ClientAccountController {
    private final AdminService adminService;


    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDetails personDetails = (ClientDetails) authentication.getPrincipal();

        return personDetails.getUsername();
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "admin";
    }
        private final DeffaultServiceClientAccount clientService;
@Autowired
    public ClientAccountController(AdminService adminService, DeffaultServiceClientAccount clientService) {
    this.adminService = adminService;
    this.clientService = clientService;
    }

    @GetMapping()
        public String index(Model model) {
            model.addAttribute("client", clientService.findAll());
            return "client/index";
        }

        @GetMapping("/{id}")
        public String show(@PathVariable("id") int id, Model model) {
            model.addAttribute("client", clientService.findOne(id));
            return "client/show";
        }

        @GetMapping("/new")
        public String newPerson(@ModelAttribute("client") ClientAccount clientAccount) {
            return "client/new";
        }

        @PostMapping()
        public String create(@ModelAttribute("client") @Valid ClientAccount clientAccount,
                             BindingResult bindingResult) {
            if (bindingResult.hasErrors())
                return "client/new";

            clientService.save(clientAccount);
            return "redirect:/client";
        }

        @GetMapping("/{id}/edit")
        public String edit(Model model, @PathVariable("id") int id) {
            model.addAttribute("client", clientService.findOne(id));
            return "client/edit";
        }

        @PatchMapping("/{id}")
        public String update(@ModelAttribute("client") @Valid ClientAccount clientAccount, BindingResult bindingResult,
                             @PathVariable("id") int id) {
            if (bindingResult.hasErrors())
                return "client/edit";

            clientService.update(id, clientAccount);
            return "redirect:/client";
        }

        @DeleteMapping("/{id}")
        public String delete(@PathVariable("id") int id) {
            clientService.delete(id);
            return "redirect:/client";
        }
    }
