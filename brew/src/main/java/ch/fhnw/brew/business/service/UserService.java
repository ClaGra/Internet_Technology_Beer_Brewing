package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.User;
import ch.fhnw.brew.data.repository.UserRepository;
import ch.fhnw.brew.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new IllegalArgumentException("User already exists with username: " + user.getUserName());
        }
        return userRepository.save(user);
    }

    public User editUser(Integer id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setUserName(updatedUser.getUserName());
        user.setPassword(updatedUser.getPassword());
        user.setRole(updatedUser.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    public boolean changePassword(Integer id, String oldPw, String newPw) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.getPassword().equals(oldPw)) {
            return false;
        }

        user.setPassword(newPw);
        userRepository.save(user);
        return true;
    }

    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
