package az.netx.heroes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "context")
    private String context;

    @Column(name = "header")
    private String header;

    @Column(name = "status")
    private String status;

    @Column(name = "received_ts")
    private LocalDateTime receivedTs;

    @Column(name = "read_ts")
    private LocalDateTime readTs;

    // file

    @PrePersist
    public void onCreate() {
        this.receivedTs = LocalDateTime.now();
    }
}
