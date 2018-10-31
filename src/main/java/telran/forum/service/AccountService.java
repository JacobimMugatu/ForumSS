package telran.forum.service;

import java.util.Set;

import telran.forum.dto.UserProfileDto;
import telran.forum.dto.UserRegisterDto;
import telran.forum.dto.UserUpdateDto;

public interface AccountService {

	public UserProfileDto addUser(UserRegisterDto userRegDto);

	public UserProfileDto editUser(UserUpdateDto userUpdateDto, String login);

	public UserProfileDto removeUser(String id);
	
	public Set<String> addRole(String id, String role);
	
	public Set<String> removeRole(String id, String role);
	
	public void changePassword(String password, String login);

}
