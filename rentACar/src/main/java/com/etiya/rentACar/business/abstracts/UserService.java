package com.etiya.rentACar.business.abstracts;

import java.util.List;

import com.etiya.rentACar.business.dtos.UserSearchListDto;
import com.etiya.rentACar.business.request.CreateUserRequest;
import com.etiya.rentACar.business.request.DeleteUserRequest;
import com.etiya.rentACar.business.request.UpdateUserRequest;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.entities.User;

public interface UserService {
	DataResult<List<UserSearchListDto>> getAll();
	Result delete(DeleteUserRequest deleteUserRequest);
	Result update(UpdateUserRequest updateUserRequest);
	Result existsByEmail(String email);
	DataResult<User> getByEmail(String email);
	User getById(int userId);


}
