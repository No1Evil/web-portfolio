package dev.tsumakov.application.profile.contact.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllUserContactsUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UserContactRepository repository;
  @Mock
  private UserContactDtoMapper mapper;

  private GetAllUserContactsUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllUserContactsUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnAllContactsMapped() {
    var email = new UserContact(1, "Email", "mailto:a@b.c", "mail.svg", NOW, NOW, 1L);
    var github = new UserContact(2, "GitHub", "https://github.com", null, NOW, NOW, 1L);
    var emailDto = new UserContactDto(1, "Email", "mailto:a@b.c", "mail.svg", NOW, NOW);
    var githubDto = new UserContactDto(2, "GitHub", "https://github.com", null, NOW, NOW);

    when(repository.findAll()).thenReturn(List.of(email, github));
    when(mapper.toDto(email)).thenReturn(emailDto);
    when(mapper.toDto(github)).thenReturn(githubDto);

    var result = useCase.execute();

    assertThat(result).containsExactly(emailDto, githubDto);
    verify(mapper).toDto(email);
    verify(mapper).toDto(github);
  }

  @Test
  void shouldReturnEmptyListWhenRepositoryEmpty() {
    when(repository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}