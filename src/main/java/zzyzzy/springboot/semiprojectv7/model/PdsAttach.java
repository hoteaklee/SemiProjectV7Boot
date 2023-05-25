package zzyzzy.springboot.semiprojectv7.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pdsattach")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PdsAttach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pano;
    private String fname;
    private String ftype;
    private String fsize;
    @Column(insertable = false, updatable = false)
    private int fdown;
    private Integer pno;
}
