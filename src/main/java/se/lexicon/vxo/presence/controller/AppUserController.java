package se.lexicon.vxo.presence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.dto.app_user.AppUserFormDto;

import javax.validation.Valid;

@Controller
public class AppUserController {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/users/register")
    public String getRegister(Model model){
        AppUserFormDto form = new AppUserFormDto();
        model.addAttribute("form", form);
        return "register";
    }

    @PostMapping("/users/process")
    public String processForm(@Valid @ModelAttribute("form") AppUserFormDto form, BindingResult bindingResult){

        if(appUserRepository.findByEmailIgnoreCase(form.getEmail()).isPresent()){
            FieldError error = new FieldError("form", "email", "Upptagen email adress");
            bindingResult.addError(error);
        }

        if(!form.getPassword().equals(form.getPasswordConfirmation())){
            FieldError error = new FieldError("form", "passwordConfirmation", "Din bekräftelse matchar inte lösenordet du angav");
            bindingResult.addError(error);
        }

        if(bindingResult.hasErrors()){
            return "register";
        }



        return "index";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "login-form";
    }

}
