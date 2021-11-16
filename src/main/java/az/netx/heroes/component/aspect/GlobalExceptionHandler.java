package az.netx.heroes.component.aspect;

import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler extends DispatcherServlet {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(WebRequest req, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("uploadingError", "YÜKLƏDİYİNİZ FAYLLARIN ÜMUMİ ÖLÇÜSÜ 30MB-DAN BÖYÜK OLMAMALIDIR");
        return "redirect:" + req.getHeader("referer").substring(21);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public String handleMissingPathVariableException(WebRequest req, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("uploadingError", "MƏLUMAT TAPILMADI");
        return "redirect:" + req.getHeader("referer").substring(21);
    }

    @ExceptionHandler(Throwable.class)
    public String handleException(WebRequest req, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("uploadingError", "DAXİLİ XƏTA");
        return "redirect:" + req.getHeader("referer").substring(21);
    }
}