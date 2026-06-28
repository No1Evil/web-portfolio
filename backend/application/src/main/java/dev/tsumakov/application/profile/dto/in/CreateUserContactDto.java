package dev.tsumakov.application.profile.dto.in;

import java.util.Map;
import java.util.UUID;

public record CreateUserContactDto(
    UUID userId,
    Map<String, String> title,
    Map<String, String> subtitle,
    String redirectUrl,
    String iconUrl
) {

}
