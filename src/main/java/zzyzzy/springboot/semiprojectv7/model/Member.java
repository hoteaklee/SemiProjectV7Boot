package zzyzzy.springboot.semiprojectv7.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Setter @Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*자동증가*/
    private Long mbno;
    private String name;
    private String jumin1;
    private String jumin2;
    @NotBlank(message = "아이디 입력하셔야 됩니다. ")
    @Length(min=6, max = 16, message = "아이디는 최소 6자, 최대16자이어야 합니다.")
    private String userid;
    @NotBlank(message = "비빌번호 입력하셔야 됩니다. ")
    @Length(min=6, max = 16, message = "비밀번호는 최소 6자, 최대16자이어야 합니다.")
    private String passwd;

    private String zipcode;
    @NotBlank(message = "주소 입력하셔야 됩니다. ")
    private String addr1;
    @NotBlank(message = "주소 입력하셔야 됩니다. ")
    private String addr2;
    @NotBlank(message = "이메일  입력하셔야 됩니다. ")
    private String email;
    private String phone;
    @CreatedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime regdate;

    //----------------------
    @Transient  // 필드 영속성을 지원하지 않음 ( 디비와 연결되지 않음 - 테이블에는 존재하지 않음)
    @NotBlank(message = "우편번호 입력하셔야 됩니다. ")
    private String zip1;
    @Transient
    @NotBlank(message = "우편번호 입력하셔야 됩니다. ")
    private String zip2;

    @Transient
    @NotBlank(message = "이메일 입력하셔야 됩니다. ")
    private String email1;

    @Transient
    @NotBlank(message = "이메일 입력하셔야 됩니다. ")
    private String email2;

    @Transient
    @NotBlank(message = "전화번호 입력하셔야 됩니다. ")
    private String tel2;
    @Transient
    @NotBlank(message = "전화번호 입력하셔야 됩니다. ")
    private String tel3;

}
