import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String accessDenied() {
        System.out.println("ErrorController");
        return "access-denied";
    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException() {
        return "error";
}
