/**
 * @Author: Maximilian Schiedermeier
 * @Date: April 2019
 */
package eu.kartoffelquadrat.tokenservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class powers up Spring and ensures the annotated controllers are detected.
 */
@SpringBootApplication
public class TokenServiceLauncher {
    public static void main(String[] args) {

        SpringApplication.run(TokenServiceLauncher.class, args);

    }
}

