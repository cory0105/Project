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
@Table(name="feed_like")
public class FeedLike {
    @Id
    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
