package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistException;
import com.example.demo.exception.NoSuchCustomerExistsException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userDao;
    private final ModelMapper mapper;
    public User addUser(UserDto userDto){
        userDao.findByEmail(userDto.getEmail()).ifPresent(existingUser -> {
            throw new EmailAlreadyExistException("Email: '" + existingUser.getEmail() + "' already exists!");
        });
        return userDao.save(mapper.map(userDto, User.class));
    }

    public User getUserById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new NoSuchCustomerExistsException("No Such Customer exists!!"));
    }
}
