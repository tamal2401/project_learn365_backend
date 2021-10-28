package org.learn365.user.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.learn365.exception.RoleNotFoundException;
import org.learn365.exception.UserNotCreatedException;
import org.learn365.exception.UserNotFoundException;
import org.learn365.modal.user.UserData;
import org.learn365.modal.user.UserRegisterStatus;
import org.learn365.modal.user.UserRequest;
import org.learn365.modal.user.entity.Learn365User;
import org.learn365.modal.user.entity.Role;
import org.learn365.modal.user.entity.UserOtp;
import org.learn365.user.repository.Learn365UserRepository;
import org.learn365.user.service.OtpService;
import org.learn365.user.service.RoleService;
import org.learn365.user.service.UserService;
import org.learn365.user.web.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    private static Logger log = LoggerFactory.getLogger(
            UserServiceImpl.class);

    private Learn365UserRepository userRepository;
    private RoleService roleService;
    private OtpService otpservice;

    UserServiceImpl(Learn365UserRepository userRepository,
                    RoleService roleService, OtpService otpservice)
    {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.otpservice = otpservice;
    }

    @Override
    public UserRegisterStatus registerUser(UserRequest userRequest)
    {
        if (null == userRequest || userRequest.getRole()
                .isEmpty() || StringUtils.isEmpty(userRequest.getDeviceId()))
        {
            log.info("Request is not valid for user :{}",
                    userRequest.getUserName());
            throw new IllegalArgumentException(
                    "Request is not valid either role or device Id is missing");
        }
        UserRegisterStatus status = new UserRegisterStatus();

        Optional<Learn365User> learnUser = userRepository.findByDeviceId(
                userRequest.getDeviceId());
        if (learnUser.isPresent() && !learnUser.get().isOtpverified())
        {
            status.setEmail(learnUser.get().getEmail());
            status.setOtpVerified(false);
            status.setNewUser(false);
            return status;
        }
        if (learnUser.isPresent())
        {
            log.info("Device is already register for user :{}",
                    userRequest.getUserName());
            throw new UserNotCreatedException(
                    "User is already registed with this Device");
        }
        Set<Role> userRole = userRequest.getRole().stream()
                .map(roleService::roleWithAliasAvailable)
                .collect(Collectors.toSet());

        if (null == userRole || userRole.isEmpty())
        {
            log.info("No role mapping available for user :{}",
                    userRequest.getUserName());
            throw new RoleNotFoundException("No mapping Role is available");
        }

        Learn365User learn365user = new Learn365User();
        BeanUtils.copyProperties(userRequest, learn365user);
        learn365user.setRole(userRole);
        learn365user.setActive(false);
        learn365user.setDeleted(false);


        try
        {
            Learn365User savedUser = userRepository.save(learn365user);
            UserOtp otp = new UserOtp();
            otp.setEmailid(savedUser.getEmail());
            otpservice.addUserOtp(otp);
            status.setEmail(savedUser.getEmail());
            status.setOtpVerified(savedUser.isOtpverified());
            status.setNewUser(true);

        } catch (Exception ex)
        {
            throw new UserNotCreatedException(ex.getMessage());
        }
        return status;
    }

    @Override
    public UserData updateUser(Long id, UserRequest userRequest)
    {
        Optional<Learn365User> dbUser = userRepository.findById(id);
        Learn365User learnUser = dbUser.orElseThrow(
                () -> new UserNotFoundException("User Not found"));
        try
        {
            userRepository.save(updateUserInformation(userRequest, learnUser));
        } catch (Exception ex)
        {
            throw new UserNotCreatedException(
                    "User not updated successfully :" + ex.getMessage());
        }
        return fetchUser(id);
    }

    @Override
    public Boolean deleteUser(Long id)
    {
        boolean isDeleted = false;
        Optional<Learn365User> dbUser = userRepository.findById(id);
        Learn365User learnUser = dbUser.orElseThrow(
                () -> new UserNotFoundException("User Not found"));
        learnUser.setActive(false);
        learnUser.setDeleted(true);
        try
        {
            userRepository.save(learnUser);
            isDeleted = true;
        } catch (Exception e)
        {

        }
        return isDeleted;
    }

    @Override
    public UserData fetchUser(Long userId)
    {
        Optional<Learn365User> dbUser = userRepository.findById(userId);
        return getUserData(dbUser);
    }

    @Override
    public UserData fetchUserByEmailId(String EmailID)
    {
        System.out.println(
                "Secrity context holder" + SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal());
        Optional<Learn365User> dbUser = userRepository.findByEmail(EmailID);
        return getUserData(dbUser);
    }

    private UserData getUserData(Optional<Learn365User> dbUser)
    {
        Learn365User learnUser = dbUser.orElseThrow(
                () -> new UserNotFoundException("User Not found"));
        if (!learnUser.isActive())
        {
            throw new UserNotFoundException("User is Not Active");
        }
        if (learnUser.isDeleted())
        {
            throw new UserNotFoundException("User is Deleted");
        }
        UserData user = new UserData();
        BeanUtils.copyProperties(learnUser, user);
        return user;
    }

    private Learn365User updateUserInformation(UserRequest userRequest,
                                               Learn365User learn365user)
    {
        if (null == userRequest)
        {
            throw new IllegalArgumentException("request is not valid");
        }

        /**
         * UserName Update
         */
        if (StringUtils.isNotEmpty(userRequest.getUserName())
                && !(StringUtils.equalsIgnoreCase(userRequest.getUserName(),
                learn365user.getUserName())))
        {
            learn365user.setUserName(userRequest.getUserName());
        }

        /**
         * Email Update
         */

        if (StringUtils.isNotEmpty(userRequest.getEmail())
                && !(StringUtils.equalsIgnoreCase(userRequest.getEmail(),
                learn365user.getEmail())))
        {
            learn365user.setEmail(userRequest.getEmail());
        }

        /**
         * Mobile Update
         */

        if (StringUtils.isNotEmpty(userRequest.getMobileNumber())
                && !(StringUtils.equalsIgnoreCase(userRequest.getMobileNumber(),
                learn365user.getMobileNumber())))
        {
            learn365user.setMobileNumber(userRequest.getMobileNumber());
        }

        /**
         * Grade update
         */

        if (StringUtils.isNotEmpty(userRequest.getGrade())
                && !(StringUtils.equalsIgnoreCase(userRequest.getGrade(),
                learn365user.getGrade())))
        {
            learn365user.setGrade(userRequest.getGrade());
        }

        /**
         * profile pic update
         */

        if (StringUtils.isNotEmpty(
                userRequest.getProfilepicturepath()) && !(StringUtils
                .equalsIgnoreCase(userRequest.getProfilepicturepath(),
                        learn365user.getProfilepicturepath())))
        {
            learn365user.setProfilepicturepath(
                    userRequest.getProfilepicturepath());
        }

        return learn365user;
    }

}
