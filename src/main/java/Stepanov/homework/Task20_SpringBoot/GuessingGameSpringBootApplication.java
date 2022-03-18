package Stepanov.homework.Task20_SpringBoot;

import Stepanov.homework.Task20_SpringBoot.service.MyEventListener;
import Stepanov.homework.Task20_SpringBoot.service.MyEventPublisher;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class GuessingGameSpringBootApplication implements ApplicationContextAware {

    public static int randomNumber = new Random().nextInt(1000);
    public static Locale locale = Locale.getDefault();

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(GuessingGameSpringBootApplication.class, args);
        MyEventPublisher publisher = context.getBean(MyEventPublisher.class);
        MyEventListener listener = context.getBean((MyEventListener.class));
        Scanner scanner = new Scanner(System.in);

        System.out.println(context.getMessage("hello", null, locale));
        while (!listener.getMessage().equals("right")) {
            try {
                publisher.publishEvent(scanner.nextInt());
            } catch (InputMismatchException e) {
                System.out.println(context.getMessage("notAnInteger", null, locale));
                scanner.next();
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
