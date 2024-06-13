package TaskTracker.frontend;


import TaskTracker.businessLogic.services.UserService;
import TaskTracker.database.beans.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @Autowired
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public  String registration(@ModelAttribute("user") User user,
                                BindingResult bindingResult,
                                HttpServletRequest request){

        UserValidator userValidator = new UserValidator();
        userValidator.validate((user), bindingResult);

        if (bindingResult.hasErrors()){
            return "registration";
        }

        authWithAuthManager(request, user.getUserName(), user.getUserPassword());

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userService.insertUser(user);

        return "redirect:/tasks";
    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        final UserDetails principal = new org.springframework.security.core.userdetails.User(username, password, grantedAuths);

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);
    }
}