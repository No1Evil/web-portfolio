package dev.tsumakov.infrastructure.profile.contact.web.dto.response;

public record UserContactResponse(
    Integer id,
    String title,
    String redirectUrl,
    String iconUrl
) {

}