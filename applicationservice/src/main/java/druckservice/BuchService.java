package druckservice;

import repository.BuchRepository;
import zwiebelbuch.buch.Buch;

import java.util.List;
import java.util.UUID;

public class BuchService {

  private final BuchRepository buecher;
  private final Printer printer;

  public BuchService(BuchRepository buecher, Printer printer) {
    this.buecher = buecher;
    this.printer = printer;
  }

  public boolean drucken(UUID id) {
    Buch buch = buecher.getBuch(id);
    if (!buch.bereit()) return false;
    printer.print(buch.getText());
    return true;
  }

  public void freigeben(String uuid){
    buecher.kapitelFreigeben(uuid);
  }

  public List<Buch> findAll() {
    return buecher.findAll();
  }

  public Buch getBuch(UUID buchId){
    return buecher.getBuch(buchId);
  }
}
