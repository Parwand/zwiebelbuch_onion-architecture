package zwiebelbuch.buch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import zwiebelbuch.dto.BuchDTO;

public class Buch {

  private final UUID id;
  private String titel;
  private List<Kapitel> kapitel;

  public Buch(BuchDTO dto) {
    this.id = dto.id();
    this.titel = dto.titel();
    this.kapitel = new ArrayList<>();
    dto.kapitel().stream().map(Kapitel::new).forEachOrdered(kapitel::add);
  }

  public List<Kapitel> getKapitels() {
    return kapitel;
  }

  public void umbenennen(String titel) {
    this.titel = titel;
  }

  public int kapitelHinzufuegen() {
    kapitel.add(new Kapitel());
    return kapitel.size() - 1;
  }

  public String getKapitelText(int nr) {
    validateKapitel(nr);
    return kapitel.get(nr).getText();
  }

  public void kapitelAendern(int nr, String text) {
    validateKapitel(nr);
    kapitel.get(nr).editieren(text);
  }

  Kapitel getKapitel(int nr) {
    validateKapitel(nr);
    return kapitel.get(nr);
  }

  public void kapitelWeiterreichen(int nr) {
    validateKapitel(nr);
    kapitel.get(nr).naechsterStatus();
  }

  public int anzahlKapitel() {
    return kapitel.size();
  }

  public boolean bereit() {
    return kapitel.stream().allMatch(Kapitel::bereit);
  }

  private void validateKapitel(int nr) {
    if (nr < 0 || nr >= kapitel.size()) {
      throw new IllegalArgumentException("Kein Kapitel mit der Nummer vorhanden");
    }
  }

  public String getText() {
    return kapitel.stream().map(Kapitel::getText).collect(Collectors.joining());
  }

  public UUID getId(){
    return  this.id;
  }

  public String getTitel(){
    return  this.titel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Buch buch = (Buch) o;
    return id.equals(buch.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
