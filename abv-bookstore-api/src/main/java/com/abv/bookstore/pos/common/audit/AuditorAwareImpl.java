package com.abv.bookstore.pos.common.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Replace with actual user from Spring Security or manually
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return Optional.ofNullable(auth != null ? auth.getName() : "system");
        return Optional.of("system-admin");
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
