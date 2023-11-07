package com.example.demo.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private ServletContext context;
    private final static List<String> listUri = List.of("demo/saveServlet",
            "demo/viewByIDServlet",
            "demo/loginServlet",
            "demo/viewServlet");

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log(">>> AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        UriRepository.initUriMap();
        this.context.log("Requested Resource::http://localhost:8080" + uri);
        HttpSession session = req.getSession(false);
        if (session == null && !(UriRepository.checkUriMap(uri))) {
            this.context.log("<<< Unauthorized access request");
            PrintWriter out = res.getWriter();
            out.println("No access!!!");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        //close any resources here
    }

}
