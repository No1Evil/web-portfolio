package dev.tsumakov.application.profile.contact.dto.in;

public record UpdateUserContactDto(
    Integer userContactId,
    String title,
    String redirectUrl,
    String iconUrl
) {

}
