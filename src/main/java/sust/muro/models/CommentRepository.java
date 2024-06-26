package sust.muro.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository <Comment, Long>  {

    public List<Comment> findByMessage(Message message);

}
