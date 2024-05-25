package ServiceBank;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class BankApplication {
    static final Logger log= (Logger) LoggerFactory.getILoggerFactory();

    public static void main(String[] args) {
        log.info("Start");
        SpringApplication.run(BankApplication.class, args);
        log.info("hello");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
