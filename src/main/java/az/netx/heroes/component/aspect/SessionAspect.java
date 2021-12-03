package az.netx.heroes.component.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SessionAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(public String az.netx.heroes.controller.*.*Admin(..))")
    public void sessionControllers() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postAction() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getAction() {
    }

    @Before(value = "getAction() && sessionControllers() && (args(..,model))", argNames = "model")
    public void beforeExecuting(Model model) {
        model.addAttribute(
                "loggedUser",
                request.getSession().getAttribute("loggedUser")
        );
    }

    @Before(value = "postAction() && sessionControllers() && (args(..,redirectAttributes))", argNames = "redirectAttributes")
    public void beforePosting(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "loggedUser",
                request.getSession().getAttribute("loggedUser")
        );
    }

}
