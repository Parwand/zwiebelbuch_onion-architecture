package zwiebelbuch.buch;

import zwiebelbuch.dto.KapitelDTO;

import java.util.UUID;

class Kapitel {

  private UUID uuid;
  private int nr;
  private String text;
  private Status status;

  public UUID getUuid() {
    return uuid;
  }

  public int getNr() {
    return nr;
  }

  public Status getStatus() {
    return status;
  }


  Kapitel(KapitelDTO dto) {
    this.uuid = dto.uuid();
    this.nr = dto.nr();
    this.text = dto.text();
    this.status = Status.values()[dto.status()];
  }

  public Kapitel() {
    this.text = "";
    this.status = Status.IN_ARBEIT;
  }

  public void editieren(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void naechsterStatus() {
    if (status != Status.FERTIG) {
      status = Status.values()[status.ordinal()+1];
    }
  }

  void setStatus(Status status) {
    this.status = status;
  }



  public boolean bereit() {
    return status == Status.FERTIG;
  }

}
