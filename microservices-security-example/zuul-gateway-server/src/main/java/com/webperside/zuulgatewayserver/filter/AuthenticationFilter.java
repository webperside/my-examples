package com.webperside.zuulgatewayserver.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.webperside.zuulgatewayserver.models.User;
import com.webperside.zuulgatewayserver.repository.UserAuthorizationRepository;
import com.webperside.zuulgatewayserver.repository.UserRepository;
import com.webperside.zuulgatewayserver.security.JwtTokenUtil;
import com.webperside.zuulgatewayserver.security.JwtUserFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends ZuulFilter {

    private final JwtTokenUtil tokenUtil;
    private final UserRepository userRepository;
    private final UserAuthorizationRepository userAuthorizationRepository;
    private final ObjectMapper objectMapper;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @SneakyThrows
    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getHeader("Authorization");

        boolean isValid;

        if(token != null && (isValid = tokenUtil.validateToken(token))){
            System.out.println(isValid);
            String username = tokenUtil.getUsernameFromToken(token);
            Optional<User> oUser = retrieveUserByUsername(username);
            if(oUser.isPresent()){
                User u = oUser.get();
                if(checkAccessTokenIsExists(u, token)){
                    ctx.addZuulRequestHeader("X-User",
                            objectMapper.writeValueAsString(JwtUserFactory.create(u)));
                }
            }
        }

        return null;
    }

    private boolean checkAccessTokenIsExists(User user, String token){
        return userAuthorizationRepository.existsByUserIdAndAccessToken(user.getId(), DigestUtils.md5DigestAsHex(token.getBytes()));
    }

    private Optional<User> retrieveUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
