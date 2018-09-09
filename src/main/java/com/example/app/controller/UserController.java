package com.example.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.constants.ConstantsIf;
import com.example.app.dto.ResponseMessage;
import com.example.app.dto.UserDto;
import com.example.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<UserDto> getUserList() {
		List<UserDto> users = service.getUserList();
		return users;
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseMessage addUser(@RequestBody UserDto user) {
		try {
			service.addUser(user);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.SAVE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.SAVE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseMessage deleteUser(@PathVariable(name = "id") int id) {
		try {
			service.deleteUser(id);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.DELETE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.DELETE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseMessage updateUser(@PathVariable(name = "id") int id, @RequestBody UserDto user) {
		try {
			service.updateUser(user, id);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.UPDATE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.UPDATE_SUCCESS));
	}

	@RequestMapping(path = "/search/{searchText}", method = RequestMethod.GET)
	public List<UserDto> searchUser(@PathVariable(name = "searchText") String searchText) {
		return service.searchUser(searchText);
	}

	@RequestMapping(path = "/sort/{sortBy}", method = RequestMethod.GET)
	public List<UserDto> sortUsers(@PathVariable(name = "sortBy") String sortBy) {
		return service.sortUsers(sortBy);
	}
}
