package zwiebelbuch.dto;

import java.util.List;
import java.util.UUID;

public record BuchDTO(UUID id, String titel, List<KapitelDTO> kapitel) {
}
