package uz.pdp.projectutils.exeption;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UsernameOrPasswordWrong extends RuntimeException{
    private HttpStatus status;
    public UsernameOrPasswordWrong(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
