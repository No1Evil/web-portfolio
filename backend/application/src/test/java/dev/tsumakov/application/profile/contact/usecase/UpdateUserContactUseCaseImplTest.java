package dev.tsumakov.application.profile.contact.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.contact.dto.in.UpdateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.exception.UserContactNotFoundException;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserContactUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UserContactRepository repository;
  @Mock
  private UserContactDtoMapper mapper;

  private UpdateUserContactUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateUserContactUseCaseImpl(repository, mapper);
  }

  private UserContact currentContact() {
    return new UserContact(1, "Email", "mailto:a@b.c", "mail.svg", NOW, NOW, 3L);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenContactMissing() {
    when(repository.findById(99)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(new UpdateUserContactDto(99, null, null, null)))
        .isInstanceOf(UserContactNotFoundException.class)
        .hasMessage("User contact with id 99 not found");

    verify(repository, never()).update(any());
  }

  @Test
  void shouldUpdateAllProvidedFieldsWithIncrementedVersion() {
    var command = new UpdateUserContactDto(1, "GitHub", "https://github.com", "github.svg");
    var saved = new UserContact(1, "GitHub", "https://github.com", "github.svg", NOW, NOW, 3L);
    var expectedDto = new UserContactDto(1, "GitHub", "https://github.com", "github.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(currentContact()));
    when(repository.update(any())).thenReturn(saved);
    when(mapper.toDto(saved)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);

    ArgumentCaptor<UserContact> captor = ArgumentCaptor.forClass(UserContact.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.title()).isEqualTo("GitHub");
    assertThat(updated.redirectUrl()).isEqualTo("https://github.com");
    assertThat(updated.iconUrl()).isEqualTo("github.svg");
    assertThat(updated.version()).isEqualTo(3L);
  }

  @Test
  void shouldNotCallUpdateWhenNoFieldProvided() {
    var command = new UpdateUserContactDto(1, null, null, null);
    var current = currentContact();
    var expectedDto = new UserContactDto(1, "Email", "mailto:a@b.c", "mail.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(mapper.toDto(current)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(repository, never()).update(any());
  }

  @Test
  void shouldPartiallyUpdateOnlyProvidedField() {
    var command = new UpdateUserContactDto(1, "LinkedIn", null, null);
    var current = currentContact();

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(current.updateTitle("LinkedIn"));
    when(mapper.toDto(any())).thenReturn(new UserContactDto(1, "LinkedIn", "mailto:a@b.c", "mail.svg", NOW, NOW));

    useCase.execute(command);

    ArgumentCaptor<UserContact> captor = ArgumentCaptor.forClass(UserContact.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.title()).isEqualTo("LinkedIn");
    assertThat(updated.redirectUrl()).isEqualTo("mailto:a@b.c");
    assertThat(updated.iconUrl()).isEqualTo("mail.svg");
    assertThat(updated.version()).isEqualTo(3L);
  }

  @Test
  void shouldPropagateDomainValidationExceptionWhenTitleIsBlank() {
    var command = new UpdateUserContactDto(1, "  ", null, null);

    when(repository.findById(1)).thenReturn(Optional.of(currentContact()));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class);

    verify(repository, never()).update(any());
  }
}