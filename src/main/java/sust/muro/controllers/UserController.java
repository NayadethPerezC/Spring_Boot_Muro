package sust.muro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import sust.muro.daos.UserDao;
import sust.muro.models.User;
import sust.muro.models.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserDao userDao;

    @GetMapping("/login")
    public String loginForm() {
      System.out.println("qwerty\n\n");
        return "login.html";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register.html";
    }
    

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String name, @RequestParam String password,
      @RequestParam String pass_confirm, RedirectAttributes redAt, HttpSession session) {
    // 1. Validamos que las contraseñas coincidan
    if (!password.equals(pass_confirm)) {
      redAt.addFlashAttribute("mal", "Las contraseñas no coinciden");
      return "redirect:/register";
    }
    
    boolean resultado = userDao.create(name, username, password, redAt, session);
    if(resultado){
      return "redirect:/";
    }
    return "redirect:/register";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {

      //1. Eliminar la sesion
    session.setAttribute("user", null);

      //2.Redirigir a la pantalla de login
    return "redirect:/login";
  }

  @PostMapping("/login")
  public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redAt, HttpSession session) {
    User u = userRepo.findByUsername(username);
    if (u == null) {
      redAt.addFlashAttribute("mal", "Usuario inexistente o contraseña incorrecta");
      return "redirect:/login";
    }

    if (!u.getPassword().equals(password)) {
        redAt.addFlashAttribute("mal", "Usuario inexistente o contraseña incorrecta");
        return "redirect:/login";
      }
    session.setAttribute("user", u); 
    return "redirect:/"; 
  }
  
    

}
