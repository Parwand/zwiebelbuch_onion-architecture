package zwiebelbuch.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import repository.BuchRepository;
import zwiebelbuch.buch.Buch;
import zwiebelbuch.dto.BuchDTO;
import zwiebelbuch.dto.KapitelDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class BuchRepositoryImpl implements BuchRepository {

  private final JdbcTemplate db;

  public BuchRepositoryImpl(JdbcTemplate db){
    this.db = db;
  }

  private static KapitelDTO KapitelMapRow(ResultSet res, int index) throws SQLException {
    String uuid = res.getString("uuid");
    int nr = res.getInt("nr");
    String text = res.getString("text");
    int status = res.getInt("status");
    return new KapitelDTO(UUID.fromString(uuid), nr, text, status);
  }

  @Override
  public Buch getBuch(UUID id) {
    List<KapitelDTO> kapitelDTOList = findKapitelListByBuchId(id);
    return db.query("""
            SELECT * FROM buch WHERE uuid = ?
            """, (res, row)->{
      UUID uuid = UUID.fromString(res.getString("uuid"));
      String title = res.getString("titel");
      BuchDTO buchDTO = new BuchDTO(uuid, title, kapitelDTOList);
      return new Buch(buchDTO);
    }, id.toString()).get(0);
  }
  @Override
  public List<KapitelDTO> findKapitelListByBuchId(UUID buchId){
    return db.query("""
            SELECT uuid, nr, text, status FROM kapitel WHERE buch = ?
            """, BuchRepositoryImpl::KapitelMapRow, buchId.toString());
  }


  @Override
  public List<Buch> findAll() {
    return db.query("SELECT uuid, titel FROM buch", (res, row)->{
      String uuid = res.getString(1);
      String titel = res.getString(2);
      BuchDTO buchDTO = new BuchDTO(UUID.fromString(uuid), titel, findKapitelListByBuchId(UUID.fromString(uuid)));
      return new Buch(buchDTO);
    });
  }

  @Override
  public void kapitelFreigeben(String uuid) {
    db.update("""
  UPDATE kapitel SET status = 2 WHERE uuid = ?
      """, uuid);
  }

  @Override
  public void save() {
    // Meh
  }
}
