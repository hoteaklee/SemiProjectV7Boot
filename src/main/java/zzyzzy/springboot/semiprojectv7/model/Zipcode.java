package zzyzzy.springboot.semiprojectv7.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name ="zipcode")
public class Zipcode {

    private String zipcode;
    private String sido;
    private String gugun;
    private String dong;
    private String ri;
    private String bunji;
    @Id
    private Long seq;

}
