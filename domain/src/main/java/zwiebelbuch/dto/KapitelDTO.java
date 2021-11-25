package zwiebelbuch.dto;

import java.util.UUID;

public record KapitelDTO(UUID uuid, int nr, String text, int status) {
}
