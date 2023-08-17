package dtec.bank.api.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class BankLocateResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        if (language == null || language.isEmpty()) {
            return Locale.forLanguageTag("pt");
        }

        Locale locale = Locale.forLanguageTag(language);
        List<Locale> idiomas = new ArrayList<>(Arrays.stream(Idioma.values()).map(str -> new Locale(str.toString())).toList());

        if (idiomas.contains(locale)) {
            return locale;
        }
        return Locale.forLanguageTag("pt");
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
