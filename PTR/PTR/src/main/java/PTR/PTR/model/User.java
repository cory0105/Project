package PTR.PTR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="password")
    private String password;
    @Column(name="user_name")
    private String userName;
    @Column(name="email")
    private String email;
    @Column(name = "birthday")
    private LocalDateTime birthday;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "profile_img")
    private String profileImg;
    @Column(name = "profile_text")
    private String profileText;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    @Column
    private int coin;
    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;
}
