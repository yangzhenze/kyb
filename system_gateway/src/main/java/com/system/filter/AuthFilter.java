package com.system.filter;

import com.system.feign.IAuth;
import com.system.util.util.Ret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class AuthFilter implements GlobalFilter {

    @Autowired
    private IAuth auth;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().pathWithinApplication().value();
        String token = exchange.getRequest().getHeaders().getFirst("X-Token");
        if(this.isStartWith(requestUrl,startWith) || this.isStartWith(requestUrl,"/static/")){
            return chain.filter(exchange);
        }

        // 判断token是否合法
        if(auth.verify(token)){
            // 获取用户信息
            if(requestUrl.contains("admin/info")){
                String id = auth.getId(token);
                if(null != id){
                    //向headers中放文件，记得build
                    ServerHttpRequest host = exchange.getRequest().mutate().header("admin-id", id).build();
                    //将现在的request 变成 change对象
                    ServerWebExchange build = exchange.mutate().request(host).build();
                    return chain.filter(build);
                }
            }
            return chain.filter(exchange);
        }



        return this.getVoidMono(exchange,Ret.msgLoginErr());

    }

    /**
     * 网关抛异常
     *
     * @param
     */
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange,String msg) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        serverWebExchange.getResponse().getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(msg.getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri,String exclude) {
        boolean flag = false;
        if (requestUri.startsWith(exclude)) {
            return true;
        }
        return flag;
    }

    public static void main(String [] args){
        String ss = "/auth/login";
        for (String s : ss.split(",")) {
            System.out.println(s);
        }
    }
}
