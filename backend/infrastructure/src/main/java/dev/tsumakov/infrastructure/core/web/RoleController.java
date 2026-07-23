package dev.tsumakov.infrastructure.core.web;

import dev.tsumakov.application.core.port.in.role.CreateRoleUseCase;
import dev.tsumakov.application.core.port.in.role.DeleteRoleUseCase;
import dev.tsumakov.application.core.port.in.role.GetAllRolesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

  private final CreateRoleUseCase createRoleUseCase;
  private final DeleteRoleUseCase deleteRoleUseCase;
  private final GetAllRolesUseCase getAllRolesUseCase;

  @PostMapping
  public ResponseEntity<?> create() {

    return ResponseEntity.ok(new Object());
  }
}
