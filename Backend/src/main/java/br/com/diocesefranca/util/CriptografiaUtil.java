package br.com.diocesefranca.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CriptografiaUtil {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String criptografar(String senhaPura) {
        return encoder.encode(senhaPura);
    }

    public static boolean verificar(String senhaPura, String senhaCriptografada) {
        return encoder.matches(senhaPura, senhaCriptografada);
    }
}
