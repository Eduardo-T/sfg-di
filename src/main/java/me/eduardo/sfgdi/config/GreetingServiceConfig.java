package me.eduardo.sfgdi.config;

import com.eduardo.sfgdi.services.DogPetService;
import com.eduardo.sfgdi.services.PetService;
import com.eduardo.sfgdi.services.PetServiceFactory;
import me.eduardo.sfgdi.datasource.FakeDataSource;
import me.eduardo.sfgdi.repositories.EnglishGreetingRepository;
import me.eduardo.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import me.eduardo.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {

    @Bean
    FakeDataSource fakeDataSource(@Value("${guru.username}") String username, @Value("${guru.password}") String password, @Value("${guru.jdbc.url}") String jdbcUrl) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcUrl(jdbcUrl);

        return fakeDataSource;
    }

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
