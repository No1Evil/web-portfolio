package dev.tsumakov.application.profile.summary.dto.in;

import java.util.Map;

public record CreateUserSummaryDto(
    String firstName,
    String lastName,
    String proficiency,
    Map<String, String> description,
    String heroImageUrl
) {

}