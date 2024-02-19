package com.raven.framework.util;


import com.raven.framework.enums.CommonCodeEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * jwt工具类
 *
 * @author Raven
 * @since 2020-02-12 15:55
 */
public class JwtUtil {

    private static final String SECRET = "raven";

    /**
     * 生成token
     *
     * @param user SpringSecurity中user
     * @return String
     */
    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("password", user.getPassword());
        claims.put("roles", "ROLE_" + user.getAuthorities().parallelStream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        long millisecond = Long.parseLong(CommonCodeEnum.LOGIN_TIMEOUT.getValue()) * 60 * 1000;
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                //创建时间
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + millisecond))
                //签名，通过密钥保证安全性
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token header中的token
     * @return User
     */
    public static User parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        String password = (String) claims.get("password");
        String roles = (String) claims.get("roles");
        return new User(username, password, Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
    }
}
