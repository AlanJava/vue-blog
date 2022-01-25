package cn.tgkzxy.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static long time = 1000*60*60*1;
    private static String signature = "admin";

    public static String createToken(){
        JwtBuilder builder = Jwts.builder();
        String jwtToken = builder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("username", "tgk")
                .claim("role", "admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static boolean checkToken(String token){
        if (token ==null){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
