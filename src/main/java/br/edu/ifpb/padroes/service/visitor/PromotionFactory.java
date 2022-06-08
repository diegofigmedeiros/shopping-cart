package br.edu.ifpb.padroes.service.visitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PromotionFactory {

    @Value("${application.activePromotion}")
    private String activePromotion;

    @Bean
    public Visitor getActivePromotion() {
        return activePromotion.equals("xmas") ? new XmasPromotion() : new RegularPromotion();
    }

}
