package com.gg.backend.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("jwtService")
public class JwtServiceImpl implements JwtService{

    private String secretKey = "aerhgb46ase58(*&)gbh463D!@$adsGSDe84tbhg65a8s4fedtr6b5as84e1dtgn6DSAsr43$@d6hjn5s4@$rf5$@S#$tg4j1nFDF3sFr4h3r6eagfdv2@!@#s58GtgbF4h685dr4th7698sr47hnb";
    @Override
    public String getToken(String key, Object value) {

        Date exptime = new Date();
        exptime.setTime(exptime.getTime() + 1000 * 60 * 5);
        byte[] secretByKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByKey, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(exptime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token)){
            try{
                byte[] secretByKey = DatatypeConverter.parseBase64Binary(secretKey);
                Key signKey = new SecretKeySpec(secretByKey, SignatureAlgorithm.HS256.getJcaName());
                return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
            } catch (ExpiredJwtException e) {
                //JWT 만료됨
            } catch (JwtException e) {
                //JWT 유효하지 않음
            }
        }
        return null;
    }

}
