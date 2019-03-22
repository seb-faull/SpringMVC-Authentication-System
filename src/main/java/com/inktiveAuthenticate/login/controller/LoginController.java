package com.inktiveAuthenticate.login.controller;

import javax.validation.Valid;

import com.inktiveAuthenticate.login.model.Artist;
import com.inktiveAuthenticate.login.model.User;
import com.inktiveAuthenticate.login.repository.ArtistRepository;
import com.inktiveAuthenticate.login.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User getEmail = userService.findUserByEmail(user.getEmail());
        User getUsername = userService.findUserByUsername(user.getUsername());

        if (getEmail != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (getUsername != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Artist> findAllArtists = artistRepository.findAll();

        modelAndView.addObject("userName",
                "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");

        modelAndView.addObject("artists", findAllArtists);

        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");

        modelAndView.setViewName("admin/home");

        return modelAndView;
    }

}
