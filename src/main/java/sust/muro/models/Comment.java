package sust.muro.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="comments")

public class Comment {

    @Id
    @GeneratedValue
    Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    String content;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date created;

    @Column()
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date updated;

    @PrePersist
    protected void onCreate() {
    this.created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    this.updated = new Date();
    } 

    @ManyToOne
    @JoinColumn(name="message_id", referencedColumnName = "id")
    Message message;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    String creatorsName;
}


