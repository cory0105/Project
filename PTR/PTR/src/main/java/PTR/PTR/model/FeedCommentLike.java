package PTR.PTR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="feed_comment_like")
public class FeedCommentLike {
    @Id
    @ManyToOne
    @JoinColumn(name = "feed_comment_id")
    private FeedComment feedComment;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
