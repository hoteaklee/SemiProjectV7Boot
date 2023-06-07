package zzyzzy.springboot.semiprojectv7.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "gallery")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;
    @NotBlank(message = "제목은 필수항목입니다.")
    private String title;
    @NotBlank(message = "작성자는 필수항목입니다.")
    private String userid;
    @Column(insertable = false, updatable = false)
    private int thumbs;
    @Column(insertable = false, updatable = false)
    private int views;
    @NotBlank(message = "본문은 필수항목입니다.")
    private String content;
    private String uuid;
    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;
}
