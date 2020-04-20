package se.lexicon.vxo.presence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import se.lexicon.vxo.presence.dto.app_user.AppUserFormDto;
import se.lexicon.vxo.presence.dto.app_user.AppUserUpdateForm;
import se.lexicon.vxo.presence.entity.role.UserRole;
import se.lexicon.vxo.presence.entity.user.AppUser;
import se.lexicon.vxo.presence.security.AppUserPrincipal;
import se.lexicon.vxo.presence.service.user.AppUserService;

import javax.validation.Valid;

import static se.lexicon.vxo.presence.exception.Exceptions.appResourceNotFoundException;
import static se.lexicon.vxo.presence.text.ExceptionMessages.ACCESS_DENIED_MSG;

@Controller
public class AppUserController {


    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/users/register")
    public String getRegister(Model model){
        AppUserFormDto form = new AppUserFormDto();
        model.addAttribute("form", form);
        return "user/register";
    }

    @PostMapping("/users/process")
    public String processForm(@Valid @ModelAttribute("form") AppUserFormDto form, BindingResult bindingResult){

        if(appUserService.findByEmail(form.getEmail()).isPresent()){
            FieldError error = new FieldError("form", "email", "Upptagen email adress");
            bindingResult.addError(error);
        }

        if(!form.getPassword().equals(form.getPasswordConfirmation())){
            FieldError error = new FieldError("form", "passwordConfirmation", "Din bekräftelse matchar inte lösenordet du angav");
            bindingResult.addError(error);
        }

        if(bindingResult.hasErrors()){
            return "user/register";
        }

        appUserService.registerNew(form);

        return "redirect:/users/login/";
    }

    @GetMapping("/users/login")
    public String getLoginForm(){
        return "login/login-form";
    }

    @GetMapping("users/{email}")
    public String findByUsername(@PathVariable("email") String email, @RequestParam(name = "action", defaultValue = "details") String action, @AuthenticationPrincipal AppUserPrincipal principal, Model model){
        if(principal == null) throw new AccessDeniedException(ACCESS_DENIED_MSG);
        if(email.equals(principal.getUsername()) || isAdmin(principal)){
            switch (action){
                case "details":
                    model.addAttribute("user", appUserService.findByEmail(email).orElseThrow(appResourceNotFoundException()));
                    return "/user/user-details";
                case "update":
                    model.addAttribute("form", buildForm(appUserService.findByEmail(email).orElseThrow(appResourceNotFoundException())));
                    return "/user/user-update-form";
                default:
                    throw new IllegalArgumentException("Invalid call action: "+action);
            }
        }else {
            throw new AccessDeniedException(ACCESS_DENIED_MSG);
        }
    }

    @PostMapping("users/{email}")
    public String processUpdate(
            @Valid @ModelAttribute("form") AppUserUpdateForm form,
            BindingResult bindingResult,
            @PathVariable("email") String email,
            @RequestParam(name = "action", defaultValue = "update") String action,
            @AuthenticationPrincipal AppUserPrincipal caller)
    {
        if(caller == null) throw new AccessDeniedException(ACCESS_DENIED_MSG);
        if(email.equals(caller.getUsername()) || isAdmin(caller)){
            if(!email.equals(form.getEmail())){
                if(appUserService.findByEmail(form.getEmail()).isPresent()){
                    FieldError error = new FieldError("form","email", "Upptagen email adress");
                    bindingResult.addError(error);
                }
            }

            if(bindingResult.hasErrors()){
                System.err.println("Has errors");
                bindingResult.getAllErrors().forEach(System.err::println);
                return "/user/user-update-form";
            }
            AppUser updated = appUserService.update(form);
            if (email.equals(caller.getUsername())) caller.setUserName(appUserService.update(form).getEmail());
            else appUserService.update(form);

            return "redirect:/users/"+updated.getEmail();

        }else {
            throw new AccessDeniedException(ACCESS_DENIED_MSG);
        }

    }

    private boolean isAdmin(AppUserPrincipal appUserPrincipal){
        boolean isAdmin = false;
        for(GrantedAuthority authority : appUserPrincipal.getAuthorities()){
            if(authority.getAuthority().equals(UserRole.APP_ADMIN.name())){
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }

    private AppUserUpdateForm buildForm(AppUser appUser){
        AppUserUpdateForm form = new AppUserUpdateForm();
        form.setAppUserId(appUser.getAppUserId());
        form.setEmail(appUser.getEmail());
        form.setFirstName(appUser.getFirstName());
        form.setLastName(appUser.getLastName());
        form.setStreet(appUser.getContactInformation().getStreet());
        form.setZipCode(appUser.getContactInformation().getZipCode());
        form.setCity(appUser.getContactInformation().getCity());
        form.setHomeNumber(appUser.getContactInformation().getHomeNumber());
        form.setMobileNumber(appUser.getContactInformation().getMobileNumber());
        form.setGitHubLink(appUser.getContactInformation().getGitHubLink());
        form.setLinkedInUrl(appUser.getContactInformation().getLinkedInURL());
        return form;
    }



}
