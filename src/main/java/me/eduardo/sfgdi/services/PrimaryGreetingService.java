package me.eduardo.sfgdi.services;

public class PrimaryGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello World - From the PRIMARY bean";
    }

}
