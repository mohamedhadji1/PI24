package tn.esprit.piproject.Config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
     /*   registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Autorise les requêtes provenant de l'URL d'Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorise les méthodes HTTP nécessaires
                .allowedHeaders("*")
                .allowCredentials(true); // Autorise tous les en-têtes
    }*/
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Remplacez par l'URL de votre application Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
    }
