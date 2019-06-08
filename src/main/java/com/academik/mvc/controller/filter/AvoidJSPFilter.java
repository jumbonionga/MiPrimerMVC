/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.mvc.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando
 */

@WebFilter("/*")
public class AvoidJSPFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        httpReq.setCharacterEncoding("UTF-8");
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String path = httpReq.getRequestURI();
        /*if(path.endsWith(".jsp")) {
        httpResp.sendRedirect(httpReq.getContextPath());
        chain.doFilter(request, response);
        return;
        }*/
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {
    }
    
    
}
