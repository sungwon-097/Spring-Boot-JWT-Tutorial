package me.sungwon.jwt_tutorial.jwt;

import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Key;

public class JwtFilter extends GenericFilter {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtFilter.class);

    public static final String AUTHORITIES_HEADER = "Authorization";

    private TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }
    @Override
    public void doFilter // 토큰의 인증 정보를 SecurityContext 에 저장하는 역할
            (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                String jwt = resolveToken(httpServletRequest);
                String requestURI = httpServletRequest.getRequestURI();

                Authentication authentication = null;
                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                    authentication = tokenProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.debug("Security Context에 '{}'인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
                } else {
                    logger.debug("유효한 jwt 토큰이 없습니다, uri: {}", requestURI);
                }
                filterChain.doFilter(servletRequest, servletResponse);
            }

    private String resolveToken(HttpServletRequest request){ // requestHeader 에서 토큰 정보를 꺼내오기 위함
        String bearerToken = request.getHeader(AUTHORITIES_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
