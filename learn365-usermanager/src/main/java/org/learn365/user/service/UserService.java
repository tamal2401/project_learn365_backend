package org.learn365.user.service;

import org.learn365.modal.user.UserData;
import org.learn365.modal.user.UserRegisterStatus;
import org.learn365.modal.user.UserRequest;

public interface UserService {

    public UserRegisterStatus registerUser(UserRequest userRequest);

    public UserData updateUser(Long id, UserRequest userRequest);

    public Boolean deleteUser(Long id);

    public UserData fetchUser(Long userId);

    public UserData fetchUserByEmailId(String EmailID);

}
