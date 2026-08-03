package dev.tsumakov.application.portfolio.usecase.userproject;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import dev.tsumakov.application.portfolio.dto.in.CreateUserProjectDto;
import dev.tsumakov.application.portfolio.mapper.UserProjectDtoMapper;
import dev.tsumakov.application.portfolio.port.in.userproject.CreateUserProjectUseCase;
import dev.tsumakov.application.shared.exception.ApplicationException;
import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.portfolio.model.UserProject;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import dev.tsumakov.domain.portfolio.repository.UserProjectRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateUserProjectUseCaseImpl implements CreateUserProjectUseCase {

  private final UserProjectRepository userProjectRepository;
  private final SkillRepository skillRepository;
  private final UserProjectDtoMapper userProjectDtoMapper;

  public CreateUserProjectUseCaseImpl(
      UserProjectRepository userProjectRepository,
      SkillRepository skillRepository,
      UserProjectDtoMapper userProjectDtoMapper
  ) {
    this.userProjectRepository = userProjectRepository;
    this.skillRepository = skillRepository;
    this.userProjectDtoMapper = userProjectDtoMapper;
  }

  @Override
  public UserProjectDto execute(CreateUserProjectDto command) {
    var skills = collectSkills(command);
    var project = createUserProject(command, skills);

    userProjectRepository.save(project);
    return userProjectDtoMapper.toDto(project);
  }

  private Set<Skill> collectSkills(CreateUserProjectDto command) {
    var ids = command.skillIds();

    return ids.stream()
        .map(id ->
            skillRepository.findById(id)
            .orElseThrow(() -> new ApplicationException("Skill not found: " + id)))
        .collect(Collectors.toSet());
  }

  private UserProject createUserProject(CreateUserProjectDto command, Set<Skill> skills) {
    return UserProject.createNew(
        command.userId(),
        command.title(),
        command.description(),
        skills,
        command.isFeatured(),
        command.projectUrl(),
        command.previewImageUrl()
    );
  }
}
