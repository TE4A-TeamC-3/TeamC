import org.springframework.boot.web.servlet.error.ErrorController; // ErrorControllerをインポート
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController { // ErrorControllerを実装

    @GetMapping("/error")
    public String accessDenied() {
        System.out.println("ErrorController");
        return "access-denied";  // access-denied.htmlを返す
    }

    @Override
    public String getErrorPath() {
        return "/error"; // これをオーバーライドしてエラーハンドリングのパスを指定
    }
}
