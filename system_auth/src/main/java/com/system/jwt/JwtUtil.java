package com.system.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static String secret = "QW87ER4QWR4QWE56WQER21WQER3";
    private static int expire = 1;

    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法  私匙使用用户密码
     *
     * @param jwtInfo      登录成功的user对象
     * @return
     */
    public static String createJWT(JwtInfo jwtInfo) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        //生成JWT的时间
        long ttlMillis = expire * 60 * 60 * 1000;
        Date now = new Date(nowMillis);

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", jwtInfo.getId());
        claims.put("username", jwtInfo.getUserName());
        claims.put("password", jwtInfo.getPassword());

        //生成签发人
        String subject = jwtInfo.getUserName();



        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, JwtUtil.secret);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            //设置过期时间
            builder.setExpiration(exp);
        }
        return builder.compact();
    }


    /**
     * Token的解密
     * @param token 加密后的token
     * @return
     */
    public static Claims parseJWT(String token) throws Exception{

        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(JwtUtil.secret)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }


    /**
     * 校验token
     * 在这里可以使用官方的校验，我这里校验的是token中携带的密码于数据库一致的话就校验通过
     * @param token
     * @return
     */
    public static Boolean isVerify(String token){

        try {
            //得到DefaultJwtParser
            Claims claims = Jwts.parser()
                    //设置签名的秘钥
                    .setSigningKey(JwtUtil.secret)
                    //设置需要解析的jwt
                    .parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            logger.debug("用户登录过期");
            return false;
        }catch (Exception e){
            logger.debug("token有误");
            return false;
        }

        return true;
    }

    public static void main(String [] args){
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId("1");
        jwtInfo.setUserName("yzz");
        jwtInfo.setPassword("123456");
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5enoiLCJwYXNzd29yZCI6IjEyMzQ1NiIsImlkIjoiMSIsImV4cCI6MTU0MjE2NDgzMywiaWF0IjoxNTQyMTYxMjMzLCJqdGkiOiI1MGQ2MjdhMy1kYTQzLTQ4MDgtOWE4YS00YWQ4ZjlkNmI0NWEiLCJ1c2VybmFtZSI6Inl6eiJ9.O5_59Z6B-0pkaooMeoYuBvD452dEU7Yh7gMvaARGfXw";
        //System.out.println(token);
        //System.out.println(JwtUtil.isVerify(token));
        System.out.println(JwtUtil.createJWT(jwtInfo));
    }

}
