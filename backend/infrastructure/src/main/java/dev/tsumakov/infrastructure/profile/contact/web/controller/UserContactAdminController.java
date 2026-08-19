package dev.tsumakov.infrastructure.profile.contact.web.controller;

import dev.tsumakov.application.profile.contact.port.in.CreateUserContactUseCase;
import dev.tsumakov.application.profile.contact.port.in.DeleteUserContactUseCase;
import dev.tsumakov.application.profile.contact.port.in.GetAllUserContactsUseCase;
import dev.tsumakov.application.profile.contact.port.in.GetUserContactByIdUseCase;
import dev.tsumakov.application.profile.contact.port.in.UpdateUserContactUseCase;
import dev.tsumakov.infrastructure.profile.contact.web.dto.request.CreateUserContactRequest;
import dev.tsumakov.infrastructure.profile.contact.web.dto.request.UpdateUserContactRequest;
import dev.tsumakov.infrastructure.profile.contact.web.dto.response.UserContactAdminResponse;
import dev.tsumakov.infrastructure.profile.contact.web.mapper.UserContactWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/profile/contacts")
@RequiredArgsConstructor
public class UserContactAdminController {

  private final GetAllUserContactsUseCase getAllUserContactsUseCase;
  private final GetUserContactByIdUseCase getUserContactByIdUseCase;
  private final CreateUserContactUseCase createUserContactUseCase;
  private final UpdateUserContactUseCase updateUserContactUseCase;
  private final DeleteUserContactUseCase deleteUserContactUseCase;
  private final UserContactWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserContactsAdmin")
  public ResponseEntity<List<UserContactAdminResponse>> getAll() {
    var contacts = getAllUserContactsUseCase.execute();
    var response = contacts.stream().map(mapper::toAdminResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getUserContactByIdAdmin")
  public ResponseEntity<UserContactAdminResponse> getById(@PathVariable Integer id) {
    var contact = getUserContactByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toAdminResponse(contact));
  }

  @PostMapping
  @Operation(operationId = "createUserContact")
  public ResponseEntity<UserContactAdminResponse> create(
      @Valid @RequestBody CreateUserContactRequest request) {
    var command = mapper.toDto(request);
    var contact = createUserContactUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAdminResponse(contact));
  }

  @PatchMapping("/{id}")
  @Operation(operationId = "updateUserContact")
  public ResponseEntity<UserContactAdminResponse> update(@PathVariable Integer id,
      @Valid @RequestBody UpdateUserContactRequest request) {
    var command = mapper.toDto(id, request);
    var contact = updateUserContactUseCase.execute(command);
    return ResponseEntity.ok(mapper.toAdminResponse(contact));
  }

  @DeleteMapping("/{id}")
  @Operation(operationId = "deleteUserContact")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    deleteUserContactUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}