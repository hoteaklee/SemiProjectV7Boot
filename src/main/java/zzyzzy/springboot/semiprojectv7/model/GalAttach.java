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
    private Integer gno;
}
