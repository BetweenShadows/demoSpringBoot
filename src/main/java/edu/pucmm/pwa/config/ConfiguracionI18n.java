package edu.pucmm.pwa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class ConfiguracionI18n implements WebMvcConfigurer {

    /*
    * Comentar para usar estrategia accept local header...
    * De lo contrario, usamos otra estrategia
    * */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    /*
    * En configuracion XML seria...
    *  <bean id="localeChangeInterceptor" class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
    *    <property name="paramName" value="siteLanguage"/>
    *  </bean>
    *  Ver documentación: https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-localeresolver-interceptor
    * */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); // especificar el nombre del request param
        return lci;
    }

    // Para saber más
    // https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // activar el interceptor para un path en especifico...
        // Seria solamente pruebai18n
        registry.addInterceptor(this.localeChangeInterceptor()).addPathPatterns("/freemarker/pruebai18n");
    }
}
