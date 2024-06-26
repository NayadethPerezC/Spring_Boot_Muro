package sust.muro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sust.muro.daos.CommentDao;
import sust.muro.models.Comment;
import sust.muro.models.CommentRepository;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    @Autowired
    CommentRepository commentRepo;

    @Autowired
    CommentDao commentDao;

    @GetMapping("/messages/{message_id}/comments")
    public List<Comment> getAll(@PathVariable Long message_id) {
    List<Comment> comments = commentDao.getCommentsFromMessage(message_id);

    return comments;
  }

}
