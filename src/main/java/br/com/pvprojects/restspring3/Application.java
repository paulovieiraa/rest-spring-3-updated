package br.com.pvprojects.restspring3;

import br.com.pvprojects.restspring3.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(Util.TIME_ZONE));
        SpringApplication.run(Application.class, args);
    }
}
