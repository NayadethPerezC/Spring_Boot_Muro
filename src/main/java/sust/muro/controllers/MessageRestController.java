package sust.muro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import sust.muro.models.Message;
import sust.muro.models.MessageRepository;


    @RestController
public class MessageRestController {

  @Autowired
  MessageRepository messageRepo;

  @PostMapping("/messages/{message_id}/likes")
  public Message addLike(@PathVariable Long message_id) {
    // 1. Recupero el mensaje en base a su ID
    Message m = messageRepo.findById(message_id).get();
    // 2. Le sumo 1 a los likes
    m.setLikes(m.getLikes() + 1);
    // 3. Guardo el resultado
    messageRepo.save(m);
    // 4. Limpiamos el mensaje
    m.setUser(null);
    m.setComments(null);
    // 5. Retorno el mensaje
    return m;
  }

}
