package repository;

import java.util.List;
import java.util.UUID;
import zwiebelbuch.buch.Buch;
import zwiebelbuch.dto.KapitelDTO;

public interface BuchRepository {
  Buch getBuch(UUID id);
  void kapitelFreigeben(String uuid);
  List<KapitelDTO> findKapitelListByBuchId(UUID buchId);
  List<Buch> findAll();
  void save();

}
