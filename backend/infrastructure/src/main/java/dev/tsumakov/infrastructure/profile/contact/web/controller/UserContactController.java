package dev.tsumakov.infrastructure.profile.contact.web.controller;

import dev.tsumakov.application.profile.contact.port.in.GetAllUserContactsUseCase;
import dev.tsumakov.application.profile.contact.port.in.GetUserContactByIdUseCase;
import dev.tsumakov.infrastructure.profile.contact.web.dto.response.UserContactResponse;
import dev.tsumakov.infrastructure.profile.contact.web.mapper.UserContactWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile/contacts")
@RequiredArgsConstructor
public class UserContactController {

  private final GetAllUserContactsUseCase getAllUserContactsUseCase;
  private final GetUserContactByIdUseCase getUserContactByIdUseCase;
  private final UserContactWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserContactsUser")
  public ResponseEntity<List<UserContactResponse>> getAll() {
    var contacts = getAllUserContactsUseCase.execute();
    var response = contacts.stream().map(mapper::toResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getUserContactByIdUser")
  public ResponseEntity<UserContactResponse> getById(@PathVariable Integer id) {
    var contact = getUserContactByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toResponse(contact));
  }
}