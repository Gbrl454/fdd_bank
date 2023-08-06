package dtec.bank.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dtec.bank.api.entity.Usuario;
import dtec.bank.api.utils.BankLocateResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String issuer = "API DtecBank";
    @Autowired
    BankLocateResolver locateResolver;
    @Autowired
    MessageSource messageSource;
    @Autowired
    HttpServletRequest request;
    @Value("${api.security.token.secret}")
    private String secret;

    private String get(String key) {
        return messageSource.getMessage(key, null, locateResolver.resolveLocale(request));
    }

    public String gerarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer(issuer).withSubject(usuario.getLogin()).withExpiresAt(dataExpiracao()).sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException(get("error.generating.tokenjwt"), e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer(issuer).build().verify(tokenJWT).getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException(get("error.invalid.tokenjwt"));
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
