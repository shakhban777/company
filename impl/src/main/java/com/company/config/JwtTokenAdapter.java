package com.company.config;

import com.company.domain.models.Employee;
import com.company.repositories.EmployeeRepository;
import com.company.security.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.util.Date;

@Component
public class JwtTokenAdapter {

    private final EmployeeRepository employeeRepository;
    private String secret = "KEMZ";

    @Value("${authkey}")
    private String secretUrlKey;

    @Autowired
    public JwtTokenAdapter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public String createToken (String username){
        Date date = new Date();
        Claims claims = Jwts.claims();
        claims.setSubject(username);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() * 100_000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(date)
//                .setExpiration(new Date(date.getTime() * 100_000))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
    }

    public Authentication authentication (String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        if (!claims.getExpiration().before(new Date())){
            Employee employee = employeeRepository.findByUsername(claims.getSubject()).orElseThrow(() -> new UsernameNotFoundException("not"));
            UserDetail detail= UserDetail.getInstance(employee);
            return new UsernamePasswordAuthenticationToken(detail, null, detail.getAuthorities());
        }
        else
            throw new UsernameNotFoundException("ТЫ НЕ ПРОЙДЕШЬ!!!... попробуй-ка еще разок");
    }
}