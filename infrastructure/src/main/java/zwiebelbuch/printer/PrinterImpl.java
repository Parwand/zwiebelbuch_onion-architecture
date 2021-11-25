package zwiebelbuch.printer;


import druckservice.Printer;
import org.springframework.stereotype.Service;

@Service
public class PrinterImpl  implements Printer {



  @Override
  public void print(String text) {
    System.out.println("Druck das Buch");
    System.out.println(text);
  }
}
