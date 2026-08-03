package dev.tsumakov.infrastructure.core.web.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateRoleRequest(
    @NotBlank(message = "Role name cannot be empty")
    @Size(min = 3, max = 20)
    String name
) {

}
