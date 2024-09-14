package uz.pdp.projectutils.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SecurityUtils {
    // user exist
    public  int getUser() {
        return new Random().nextInt(100);
    }
}
