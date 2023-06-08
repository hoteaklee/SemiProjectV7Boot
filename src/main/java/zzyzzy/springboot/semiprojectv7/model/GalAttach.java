package zzyzzy.springboot.semiprojectv7.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "galattach")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GalAttach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gano;
    private String fname;
    private String fsize;
    private Integer gno;  // gallery의 gno와 연결 - 외래키

    @OneToOne(optional = false) // outer join을 inner join으로 변경!!
    @JoinColumn(name="gno", insertable = false,updatable = false)
    private  Gallery gallery;
}
