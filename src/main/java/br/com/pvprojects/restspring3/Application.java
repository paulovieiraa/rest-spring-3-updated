package br.com.pvprojects.restspring3;

import br.com.pvprojects.restspring3.util.Util;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone(Util.TIME_ZONE));
    SpringApplication.run(Application.class, args);
  }
}
