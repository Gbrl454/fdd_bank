package dtec.bank.api.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;

public class Msm {
    BankLocateResolver locateResolver;
    MessageSource messageSource;
    HttpServletRequest request;

    public Msm(BankLocateResolver locateResolver, MessageSource messageSource, HttpServletRequest request) {
        this.locateResolver = locateResolver;
        this.messageSource = messageSource;
        this.request = request;
    }

    public String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }
}
