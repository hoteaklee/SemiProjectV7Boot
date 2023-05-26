package zzyzzy.springboot.semiprojectv7.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "pdsreply")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PdsReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rpno;
    @NotBlank(message = "댓글은 필수항목입니다.")
    private String reply;
    @NotBlank(message = "작성자는 필수항목입니다.")
    private String userid;
    private int pno;
    private int refno;
    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;
}
