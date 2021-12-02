package az.netx.heroes.component.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
public class SessionAspect {

    @Autowired
    HttpSession session;

    @Pointcut("execution(public String az.netx.heroes.controller.*.*Admin(..))")
    public void sessionControllers() {

    }

    @Before(value = "sessionControllers() && (args(..,model) || args(model))", argNames = "model")
    public void beforeExecuting(Model model) {
        model.addAttribute("loggedUser", session.getAttribute("loggedUser"));
    }

    @Before(value = "sessionControllers() && (args(..,redirectAttributes))", argNames = "redirectAttributes")
    public void beforeExecuting(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loggedUser", session.getAttribute("loggedUser"));
    }

}
