package telran.forum.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.forum.dto.UserProfileDto;
import telran.forum.dto.UserRegisterDto;
import telran.forum.dto.UserUpdateDto;
import telran.forum.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountManagmentController {
	@Autowired
	AccountService accountService;
	
	@PostMapping("/register")
	public UserProfileDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return accountService.addUser(userRegisterDto);
	}
	
	@PutMapping
	public UserProfileDto edit(@RequestBody UserUpdateDto userUpdateDto, Principal principal) {
		return accountService.editUser(userUpdateDto, principal.getName());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("#id == authentication.name")
	public UserProfileDto remove(@PathVariable String id) {
		return accountService.removeUser(id);
	}
	
	@PutMapping("/{id}/{role}")
	@PreAuthorize("hasRole('ADMIN')")
	public Set<String> addRole(@PathVariable String id, @PathVariable String role) {
		return accountService.addRole(id, role);
	}
	
	@DeleteMapping("/{id}/{role}")
	@PreAuthorize("hasRole('ADMIN')")
	public Set<String> removeRole(@PathVariable String id, @PathVariable String role) {
		return accountService.removeRole(id, role);
	}
	
	@PutMapping("/password")
	public void changePassword(@RequestHeader(value = "X-Password") String password, Principal principal) {
		accountService.changePassword(password, principal.getName());
	}

}
