package com.hzp.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter比Spring的Intercepter更早介入请求生命周期，所以可以更早的处理OPTIONS请求.
 * 这个Filter在{@CrossDomainconfig}中引入.
 *
 * @author hzp
 */
@Slf4j
public class CrossDomainFilter extends OncePerRequestFilter {

    private volatile boolean allowCrossDomain = true;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (allowCrossDomain) {
            // 重要：clientIp不能为*，否则session无法传递到服务器端.
            response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.addHeader("Access-Control-Allow-Credentials", "true");

            /**
             * 处理 Preflight 情况下的额外返回数据:
             * https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS#Preflighted_requests
             * 需要确认 Preflight 是有效的请求，而不是直接进行的OPTIONS操作.
             */
            if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equalsIgnoreCase(request.getMethod())) {
                response.addHeader("Access-Control-Allow-Methods", "GET, POST");
                response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Cookie,Authorization,tk");
            }
            ServletRequest requestWrapper = null;
            if (request instanceof HttpServletRequest && request.getMethod().equalsIgnoreCase("post") && StringUtils.isNotBlank(request.getContentType()) && request.getContentType().contains("application/json")) {
                requestWrapper = new RepeatedlyReadRequestWrapper(request);
            }
            if (null == requestWrapper) {
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(requestWrapper, response);
            }
        }
    }

    public void setAllowCrossDomain(boolean allowCrossDomain) {
        this.allowCrossDomain = allowCrossDomain;
    }
}
