package zzyzzy.springboot.semiprojectv7.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component("pdsUtils")  //다른 클래스를 보조하는 역활
public class PdsUtils {
    public String makeUUID() {
        String uuid = LocalDate.now() + "" +LocalTime.now();
        uuid = uuid.replace("-", "")
                .replace(":", "").replace(".", "");
        return uuid;
    }
}
