package com.vlms.stock;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimitingFilter implements Filter {
    private final Map<String, AtomicInteger> requestCountsPerIPAddress = new ConcurrentHashMap<>();

    private static final int MAX_REQUESTS_PER_MINUTE = 5;
    private static boolean reset = true;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String clientIPAddress = httpServletRequest.getRemoteAddr();

        requestCountsPerIPAddress.putIfAbsent(clientIPAddress, new AtomicInteger(0));
        AtomicInteger requestCount = requestCountsPerIPAddress.get(clientIPAddress);

        int requests = requestCount.incrementAndGet();

        if(requests > MAX_REQUESTS_PER_MINUTE){
            httpServletResponse.setStatus(429);
            httpServletResponse.getWriter().write("Too many requests. Please try again later");
            return;
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
//        try {
//            Thread.sleep(15000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                requestCountsPerIPAddress.clear();
//            }
//        };
//        t.start();
//        System.out.println("reset1 "+reset);
//        if(reset)
        CompletableFuture.supplyAsync(()->{
            try {
                System.out.println("reset2 "+reset);
                Thread.sleep(15000);
//                reset = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            requestCountsPerIPAddress.clear();
            return null;
        }).thenAccept(res->{
            System.out.println("Successfully reset Requests counts");
        });

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
