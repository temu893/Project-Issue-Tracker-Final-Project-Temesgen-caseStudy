package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.dto.UserDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.exception.UserNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    @Override
//    public List<UserDTO> getAllUsers() {
//        return userRepository.findAll().stream().map(User::toDTO).toList();
//    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(User::toDTO).collect(Collectors.toList());
    }

    public Long getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return user.toDTO().getId();
    }
}
