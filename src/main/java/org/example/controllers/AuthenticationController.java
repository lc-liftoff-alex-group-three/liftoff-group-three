package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.data.ChildRepository;
import org.example.data.ParentRepository;
import org.example.data.UserRepository;
import org.example.models.*;
import org.example.models.dto.LoginFormDTO;
import org.example.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParentRepository parentRepository;
    @Autowired
    ChildRepository childRepository;
    @Autowired
    EmailVerification emailVerification;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    public Parent getParentFromSession(HttpSession session) {
        Integer parentId = (Integer) session.getAttribute(userSessionKey);
        if (parentId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(parentId);

        if (user.isEmpty()) {
            return null;
        }
        if (user.get() instanceof ParentUser) {
            ParentUser parentUser = (ParentUser) user.get();
            return parentUser.getParent();
        }
        return null;
    }

    public Child getChildFromSession(HttpSession session) {
        Integer childId = (Integer) session.getAttribute(userSessionKey);
        if (childId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(childId);

        if (user.isEmpty()) {
            return null;
        }
        if (user.get() instanceof ChildUser) {
            ChildUser childUser = (ChildUser) user.get();
            return childUser.getChild();
        }
        return null;
    }



    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }


    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO, Errors errors, HttpServletRequest request, Model model) {
        String emailAddress = registerFormDTO.getEmail();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        if (!emailVerification.verifyEmail(emailAddress)) {
            errors.rejectValue("email", "email.invalid", "Invalid email address");
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        String hashed = BCrypt.hashpw(registerFormDTO.getPassword(), BCrypt.gensalt());

        ParentUser newParentUser = new ParentUser(registerFormDTO.getUsername(), hashed);
        userRepository.save(newParentUser);
//        setUserInSession(request.getSession(), newParentUser);
        Parent newParent = new Parent(registerFormDTO.getFirstName(), registerFormDTO.getLastName(), newParentUser);
        parentRepository.save(newParent);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "login.html";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors, HttpServletRequest request, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());


        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!BCrypt.checkpw(password, theUser.getPwHash())) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        if (userRepository.findByUsername(loginFormDTO.getUsername()) instanceof ParentUser) {
            ParentUser theParentUser = (ParentUser) userRepository.findByUsername(loginFormDTO.getUsername());
            setUserInSession(request.getSession(), theParentUser);
            return "redirect:/parent_dashboard";
        } else if (userRepository.findByUsername(loginFormDTO.getUsername()) instanceof ChildUser)  {
            ChildUser theChildUser = (ChildUser) userRepository.findByUsername(loginFormDTO.getUsername());
            setUserInSession(request.getSession(), theChildUser);
            return "redirect:/child_dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }


    @GetMapping("/child_dashboard")
    public String showChildDashboard(Model model, HttpSession session) {
        model.addAttribute("title", "Child Dashboard");
        Child child = getChildFromSession(session);
        return "childDashboard";
    }

    @GetMapping("parent_dashboard")
    public String showParentDashboard(Model model, HttpSession session) {
        model.addAttribute("title", "Parent Dashboard");
        Parent parent = getParentFromSession(session);
        model.addAttribute("children", childRepository.findAllByParent(parent));
        return "parentDashboard";

    }
}