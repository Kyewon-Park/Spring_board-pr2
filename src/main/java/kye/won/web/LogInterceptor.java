package kye.won.web;

import kye.won.web.member.basic.BasicMemberController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(BasicMemberController.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("start method: {}, url: {}", request.getMethod(), request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("end method: {}, url: {}", request.getMethod(), request.getRequestURL());
    }
}
