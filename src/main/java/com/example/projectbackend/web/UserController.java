package com.example.projectbackend.web;

import com.example.projectbackend.entity.User;
import com.example.projectbackend.exception.http.ForbiddenException;
import com.example.projectbackend.exception.http.NotFoundException;
import com.example.projectbackend.helper.AuthHelper;
import com.example.projectbackend.service.AuthService;
import com.example.projectbackend.service.UserService;
import com.example.projectbackend.web.model.UserRole;
import com.example.projectbackend.web.model.request.CreateUserRequest;
import com.example.projectbackend.web.model.request.UpdateUserRequest;
import com.example.projectbackend.web.model.response.UserInformationResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController extends BaseController {
  private final AuthService authService;
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<UserInformationResponse>> getAllUsers(
      @RequestHeader HttpHeaders headers) {
    authService.authenticateAdmin(AuthHelper.getBearerTokenFromHeaders(headers));
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(
        users.stream().map(user -> new UserInformationResponse(user)).toList());
  }

  @GetMapping("/user")
  public ResponseEntity<UserInformationResponse> getUserById(
      @RequestHeader HttpHeaders headers,
      @RequestParam("id") @Min(value = 1, message = "id should be 1 or higher") Long id)
      throws NotFoundException {
    authService.authenticateAdmin(AuthHelper.getBearerTokenFromHeaders(headers));
    User user = userService.getUserById(id);
    return ResponseEntity.ok(new UserInformationResponse(user));
  }

  @PostMapping("/user")
  public ResponseEntity<User> createUser(
      @RequestHeader HttpHeaders headers, @RequestBody @Valid CreateUserRequest createUserRequest) {
    User user =
        userService.createUser(
            createUserRequest.getUsername(),
            createUserRequest.getPassword(),
            UserRole.from(createUserRequest.getRole()));
    return ResponseEntity.ok(user);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<String> updateUser(
      @RequestHeader HttpHeaders headers,
      @PathVariable @NotNull long id,
      @RequestBody @Valid UpdateUserRequest updateUserRequest) {
    System.out.println("hello");
    validateRequestMakerIsTargetUser(headers, id);
    userService.updateUser(id, updateUserRequest.getUsername(), updateUserRequest.getPassword());
    return ResponseEntity.ok("successfully updated the user");
  }

  @DeleteMapping("/user")
  public ResponseEntity<String> deleteUser(
      @RequestHeader HttpHeaders headers, @PathVariable @NotNull long id) {
    validateRequestMakerIsTargetUser(headers, id);
    userService.deleteUserById(id);
    return ResponseEntity.ok("successfully deleted the user");
  }

  private void validateRequestMakerIsTargetUser(HttpHeaders headers, long id) {
    if (authService.authenticate(AuthHelper.getBearerTokenFromHeaders(headers)).getId() != id) {
      throw new ForbiddenException("you need to be the real user to make this request");
    }
  }
}
