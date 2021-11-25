package zwiebelbuch;

import druckservice.BuchService;
import druckservice.Printer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.BuchRepository;

@Configuration
public class ZwiebelBuchConfig {

  @Bean
  BuchService druckService(BuchRepository buecher, Printer printer) {
    return new BuchService(buecher, printer);
  }

}
