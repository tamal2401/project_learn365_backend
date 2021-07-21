package org.learn365.user.web;

import javax.validation.Valid;

import org.learn365.modal.user.UserData;
import org.learn365.modal.user.UserRequest;
import org.learn365.user.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
@Validated
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = { "register",
			"service/register" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserData registerUser(@Valid @RequestBody UserRequest userRequest) {
		return userService.registerUser(userRequest);
	}

	@PutMapping(value = {"update/{userid}",
			"service/update/{userid}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserData updateUser(@Valid @RequestBody UserRequest userRequest,
			@PathVariable(name = "userid", required = true) Long userId) {
		return userService.updateUser(userId, userRequest);
	}

	@DeleteMapping(value ={ "delete/{userid}","service/delete/{userid}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteUser(@PathVariable(name = "userid", required = true) Long userId) {
		return userService.deleteUser(userId);
	}

	@GetMapping(value = { "fetch/{userid}", "service/fetch/{userid}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserData getUserDetails(@PathVariable(name = "userid", required = true) Long userId) {
		return userService.fetchUser(userId);
	}

	@GetMapping(value = { "get/{email}", "service/fetch/{email}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserData getUserDetailsByEmailId(@PathVariable(name = "email", required = true) String email) {
		return userService.fetchUserByEmailId(email);
	}

}
