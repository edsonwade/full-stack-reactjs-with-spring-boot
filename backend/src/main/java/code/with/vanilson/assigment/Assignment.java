package code.with.vanilson.assigment;

import code.with.vanilson.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_assignments")
@Getter
@Setter
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("id")
    @Column(name = "assignment_id")
    private Long assignmentId;
    private String status;
    @Column(name = "github_url")
    private String githubUrl;
    private String branch;
    @Column(name = "code_review")
    private String codeReviewVideoUrl;
    @ManyToOne(optional = false)
    private User assignedTo;

    public Assignment() {
        //default constructors
    }

    public Assignment(Long assignmentId, String status, String githubUrl, String branch, String codeReviewVideoUrl,
                      User assignedTo) {
        this.assignmentId = assignmentId;
        this.status = status;
        this.githubUrl = githubUrl;
        this.branch = branch;
        this.codeReviewVideoUrl = codeReviewVideoUrl;
        this.assignedTo = assignedTo;
    }

}
