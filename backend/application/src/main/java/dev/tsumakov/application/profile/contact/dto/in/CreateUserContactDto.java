package dev.tsumakov.application.profile.contact.dto.in;

public record CreateUserContactDto(
    String title,
    String redirectUrl,
    String iconUrl
) {

}
