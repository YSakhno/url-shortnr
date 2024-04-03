package io.ysakhno.urlshortnr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application's main class.
 *
 * @author Yuri Sakhno
 */
@SpringBootApplication
public class UrlShortenerApplication {

    /**
     * Application's main entry point.
     *
     * @param args arguments passed to the application on the command line (if any).
     */
    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}
