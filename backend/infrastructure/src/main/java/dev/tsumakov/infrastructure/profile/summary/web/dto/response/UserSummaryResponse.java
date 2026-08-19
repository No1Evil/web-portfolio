package dev.tsumakov.infrastructure.profile.summary.web.dto.response;

import java.util.Map;

public record UserSummaryResponse(
    Integer id,
    String firstName,
    String lastName,
    String proficiency,
    Map<String, String> description,
    String heroImageUrl
) {

}