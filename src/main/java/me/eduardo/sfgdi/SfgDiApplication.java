package me.eduardo.sfgdi;

import me.eduardo.sfgdi.controllers.*;
import com.eduardo.sfgdi.services.PetService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.eduardo.sfgdi.services", "me.eduardo.sfgdi"})
@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SfgDiApplication.class, args);

		PetService petService = context.getBean(PetService.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petService.getPetType());

		I18nController i18nController = context.getBean(I18nController.class);
		System.out.println(i18nController.sayHello());

		MyController myController = (MyController) context.getBean("myController");
		System.out.println("----- Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("----- Property");
		var propertyInjectedController = (PropertyInjectedController) context.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("----- Setter");
		var setterInjectedController = context.getBean(SetterInjectedController.class);
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("----- Constructor");
		var constructorInjectedController = context.getBean(ConstructorInjectedController.class);
		System.out.println(constructorInjectedController.getGreeting());
	}

}
