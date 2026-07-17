package dev.tsumakov.application.portfolio.dto.in;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record CreateUserProjectDto(
    UUID userId,
    Map<String, String> title,
    Map<String, String> description,
    Set<Integer> skillIds,
    Boolean isFeatured,
    String projectUrl,
    String previewImageUrl
) {

}
