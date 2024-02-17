package org.example;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.controllers.AuthenticationController;
import org.example.data.UserRepository;
import org.example.models.ChildUser;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements org.springframework.web.servlet.HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css", "/images");

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot) || path.equalsIgnoreCase("/") || path.endsWith(".css")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/#login");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        if (user instanceof ChildUser) {
            if (model == null) {
                model = new ModelAndView("childUser");
            }
            model.addObject("childUser", "childUser");
        }
        if (user != null) {
            if (model == null) {
                model = new ModelAndView("loggedInUser");
            }
            model.addObject("loggedInUser","loggedInUser");
        }
    }
}