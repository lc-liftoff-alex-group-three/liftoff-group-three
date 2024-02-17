package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.data.ChildRepository;
import org.example.data.ParentRepository;
import org.example.data.UserRepository;
import org.example.models.*;
import org.example.models.dto.AddChildFormDTO;
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
public class ChildController {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParentRepository parentRepository;

    private static final String userSessionKey = "user";

    public ParentUser getParentFromSession(HttpSession session) {
        Integer parentId = (Integer) session.getAttribute(userSessionKey);
        if (parentId == null) {
            return null;
        }

        Optional<User> parent = userRepository.findById(parentId);

        if (parent.isEmpty()) {
            return null;
        }

        return (ParentUser) parent.get();
    }

        @GetMapping("new/child")
        public String showNewChildForm (Model model){
            model.addAttribute("title", "Add a Child");
            model.addAttribute(new AddChildFormDTO());
            return "newChild";
        }

        @PostMapping("new/child")
        public String processNewChildForm (@ModelAttribute @Valid AddChildFormDTO addChildFormDTO, Errors
        errors, HttpServletRequest request, Model model){
            if (errors.hasErrors()) {
                model.addAttribute("title", "Add a Child");
                return "newChild";
            }

            User existingUser = userRepository.findByUsername(addChildFormDTO.getUsername());

            if (existingUser != null) {
                errors.rejectValue("username", "username.alreadyexists", "A child with that username already exists");
                model.addAttribute("title", "Add a Child");
                return "newChild";
            }

            String password = addChildFormDTO.getPassword();
            String verifyPassword = addChildFormDTO.getVerifyPassword();
            if (!password.equals(verifyPassword)) {
                errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
                model.addAttribute("title", "Add Child");
                return "newChild";
            }

            String hashed = BCrypt.hashpw(addChildFormDTO.getPassword(), BCrypt.gensalt());

            ParentUser parentUser = getParentFromSession(request.getSession());
            Parent parent = parentUser.getParent();
            ChildUser newChildUser = new ChildUser(addChildFormDTO.getUsername(), hashed);
            userRepository.save(newChildUser);
            Child newChild = new Child(addChildFormDTO.getFirstName(), addChildFormDTO.getLastName(), parent, newChildUser);
            childRepository.save(newChild);
            parentRepository.save(parent);
            return "redirect:/parent_dashboard";
        }

    }

