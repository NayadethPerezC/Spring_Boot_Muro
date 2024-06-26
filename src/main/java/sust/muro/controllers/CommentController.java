package sust.muro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sust.muro.daos.CommentDao;
import sust.muro.models.CommentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CommentController {

    @Autowired
    CommentRepository comentRepo;

    @Autowired
    CommentDao commentDao;

    @PostMapping("/comments")
    public String postMethodName(@RequestParam Long message_id, @RequestParam Long user_id, @RequestParam String content) {
        commentDao.create(message_id, user_id, content);
        
        return "redirect:/";
    }
    

}
