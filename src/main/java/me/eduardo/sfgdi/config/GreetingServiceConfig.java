package me.eduardo.sfgdi.config;

import com.eduardo.sfgdi.services.DogPetService;
import com.eduardo.sfgdi.services.PetService;
import com.eduardo.sfgdi.services.PetServiceFactory;
import me.eduardo.sfgdi.repositories.EnglishGreetingRepository;
import me.eduardo.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import me.eduardo.sfgdi.services.*;
import org.springframework.context.annotation.*;

@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory factory) {
        return factory.getPetService("dog");
    }

    @Profile("cat")
    @Bean
    PetService catPetService(PetServiceFactory factory) {
        return factory.getPetService("cat");
    }

    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishService() {
        return new I18nSpanishGreetingService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }


    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

    @Bean
    PropertyGreetingService propertyGreetingService() {
        return new PropertyGreetingService();
    }

    @Bean
    SetterGreetingService setterGreetingService() {
        return new SetterGreetingService();
    }


}
