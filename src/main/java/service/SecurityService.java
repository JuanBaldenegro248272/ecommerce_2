/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

/**
 *
 * @author jrasc
 */
public class SecurityService {

    private static final String SECRET_KEY = "mi_clave_secreta_super_segura_ecommerce_123";
    private static final String ISSUER = "ecommerce_2_api";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    public static String generateToken(String correo, boolean esAdmin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(correo)
                    .withClaim("admin", esAdmin)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Error al generar token JWT", exception);
        }
    }

    public static DecodedJWT validateToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }
}
