package dev.tsumakov.application.profile.contact.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.contact.dto.in.CreateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.domain.profile.contact.factory.UserContactFactory;
import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserContactUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UserContactFactory factory;
  @Mock
  private UserContactRepository repository;
  @Mock
  private UserContactDtoMapper mapper;

  private CreateUserContactUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateUserContactUseCaseImpl(factory, repository, mapper);
  }

  @Test
  void shouldCreateContactAndReturnDto() {
    var command = new CreateUserContactDto("Email", "mailto:user@example.com", "mail.svg");
    var freshContact = new UserContact(null, "Email", "mailto:user@example.com", "mail.svg", NOW, NOW, 1L);
    var savedContact = new UserContact(1, "Email", "mailto:user@example.com", "mail.svg", NOW, NOW, 1L);
    var expectedDto = new UserContactDto(1, "Email", "mailto:user@example.com", "mail.svg", NOW, NOW);

    when(factory.createNew(command.title(), command.redirectUrl(), command.iconUrl()))
        .thenReturn(freshContact);
    when(repository.create(freshContact)).thenReturn(savedContact);
    when(mapper.toDto(savedContact)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(factory).createNew("Email", "mailto:user@example.com", "mail.svg");
    verify(repository).create(freshContact);
    verify(mapper).toDto(savedContact);
  }
}