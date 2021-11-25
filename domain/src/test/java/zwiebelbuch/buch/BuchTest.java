package zwiebelbuch.buch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zwiebelbuch.dto.BuchDTO;
import zwiebelbuch.dto.KapitelDTO;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BuchTest {

    @Test
    @DisplayName("Das title kann umbenannt werden")
    void test_1() {
        // Arrange
        BuchDTO buchDTO = new BuchDTO(
                UUID.randomUUID(),
                "Buch1",
                List.of(new KapitelDTO(UUID.randomUUID(),
                        1,
                        "Das Text von Kapitel 1",
                        2)));
        Buch buch = new Buch(buchDTO);
        // Act
        buch.umbenennen("Buch 2");
        //Assert
        assertThat(buch.getTitel()).isEqualTo("Buch 2");

    }

    @Test
    @DisplayName("Das Buch ist noch nicht bereit")
    void test_2() {
        // Arrange
        BuchDTO buchDTO = new BuchDTO(UUID.randomUUID(), "Buch1",
                List.of(new KapitelDTO(UUID.randomUUID(), 1, "Das Text von Kapitel 1", 2),
                        new KapitelDTO(UUID.randomUUID(), 1, "Das Text von Kapitel 2", 0),
                        new KapitelDTO(UUID.randomUUID(), 1, "Das Text von Kapitel 2", 1)));
        Buch buch = new Buch(buchDTO);
        // Act
        boolean bereit = buch.bereit();
        //Assert
        assertThat(bereit).isFalse();
    }
}
