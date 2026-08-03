package dev.tsumakov.infrastructure.core.web;

import dev.tsumakov.application.core.dto.api.RoleDto;
import dev.tsumakov.application.core.dto.in.CreateRoleDto;
import dev.tsumakov.application.core.port.in.role.CreateRoleUseCase;
import dev.tsumakov.application.core.port.in.role.DeleteRoleUseCase;
import dev.tsumakov.application.core.port.in.role.GetAllRolesUseCase;
import dev.tsumakov.infrastructure.core.web.dto.role.CreateRoleRequest;
import dev.tsumakov.infrastructure.core.web.dto.role.RoleCreatedResponse;
import dev.tsumakov.infrastructure.core.web.dto.role.RoleResponse;
import dev.tsumakov.infrastructure.core.web.mapper.RoleWebMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

  private final RoleWebMapper roleWebMapper;
  private final CreateRoleUseCase createRoleUseCase;
  private final DeleteRoleUseCase deleteRoleUseCase;
  private final GetAllRolesUseCase getAllRolesUseCase;

  @PostMapping
  public ResponseEntity<RoleCreatedResponse> create(
      @Valid @RequestBody CreateRoleRequest request
  ) {
    CreateRoleDto dto = roleWebMapper.toDto(request);
    RoleDto createdRole = createRoleUseCase.execute(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(roleWebMapper.toResponse(createdRole));
  }

  @GetMapping
  public ResponseEntity<List<RoleResponse>> get() {
    List<RoleDto> roleDtoList = getAllRolesUseCase.execute();
    return ResponseEntity.ok(roleWebMapper.toResponseList(roleDtoList));
  }

  @DeleteMapping("/{name}")
  public ResponseEntity<Void> delete(@PathVariable String name) {
    deleteRoleUseCase.execute(name);
    return ResponseEntity.noContent().build();
  }

}
