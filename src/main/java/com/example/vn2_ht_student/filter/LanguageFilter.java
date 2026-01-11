//package com.example.vn2_ht_student.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.LocaleResolver;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Locale;
//import java.util.Optional;
//
//@Component
//@Slf4j
//public class LanguageFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private LocaleResolver localeResolver;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain
//    ) throws ServletException, IOException {
//
//        String lang = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
//
//        Locale locale = (lang != null && !lang.isBlank())
//                ? Locale.forLanguageTag(lang)
//                : new Locale("vi", "VN");
//
//        localeResolver.setLocale(request, response, locale);
//        chain.doFilter(request, response);
//    }
//
//}
//
