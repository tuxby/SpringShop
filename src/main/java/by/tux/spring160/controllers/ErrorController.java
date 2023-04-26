package by.tux.spring160.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final MessageSource messageSource;

    @Autowired
    public ErrorController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping("/error")
    public String handleError(
            Locale locale,
            Model model,
            HttpServletRequest request,
            Exception ex
    ) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            Map<String, String> metaData = new HashMap<>();
                // 403
            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // do somthing
            }
            // 404
            else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // do somthing
            }
            // 405
            else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // do somthing
            }
            // 500
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // do somthing
            }
        }
        return "/error";
    }


}