package dev.tsumakov.application.portfolio.dto.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record UserProjectDto(
    Integer id,
    UUID userId,
    Map<String, String> title,
    Map<String, String> description,
    List<SkillDto> skills,
    Boolean isFeatured,
    String projectUrl,
    String previewImageUrl
) {

}
