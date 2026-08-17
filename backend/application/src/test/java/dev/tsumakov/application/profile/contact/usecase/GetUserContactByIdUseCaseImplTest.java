package dev.tsumakov.application.profile.contact.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.exception.UserContactNotFoundException;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserContactByIdUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UserContactRepository repository;
  @Mock
  private UserContactDtoMapper mapper;

  private GetUserContactByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserContactByIdUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnContactDtoWhenFound() {
    var contact = new UserContact(1, "Email", "mailto:a@b.c", "mail.svg", NOW, NOW, 1L);
    var expectedDto = new UserContactDto(1, "Email", "mailto:a@b.c", "mail.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(contact));
    when(mapper.toDto(contact)).thenReturn(expectedDto);

    var result = useCase.execute(1);

    assertThat(result).isEqualTo(expectedDto);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenContactMissing() {
    when(repository.findById(99)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(99))
        .isInstanceOf(UserContactNotFoundException.class)
        .hasMessage("User contact with id 99 not found");

    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}