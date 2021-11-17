package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.User;
import az.netx.heroes.model.request.UserRequest;
import az.netx.heroes.model.response.UserResponse;
import az.netx.heroes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
//            userBuilder.disabled(!user.isActive());
            userBuilder.password(user.getPassword());

            String[] authoritiesArr = {"ADMIN"};
            userBuilder.authorities(authoritiesArr);
        } else {
            throw new UsernameNotFoundException("User not found !!!");
        }
        return userBuilder.build();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updatePassword(String oldPass, String newPass) {
        User user = userRepository.getOne(1L);
        if (passwordEncoder.matches(oldPass, user.getPassword())) {
            User newUser = new User();
            newUser.setId(1L);
            newUser.setPassword(passwordEncoder.encode(newPass));
            newUser.setUsername(user.getUsername());
            userRepository.save(newUser);
        } else {
            System.out.println("err");
        }
    }

    public void updateUsername(String oldUsername, String newUsername) {
        User user = userRepository.getOne(1L);
        if (oldUsername.equals(user.getUsername())) {
            User newUser = new User();
            newUser.setId(1L);
            newUser.setPassword(user.getPassword());
            newUser.setUsername(newUsername);
            userRepository.save(newUser);
        } else {
            System.out.println("err");
        }
    }

    public String activateUser(UserRequest request) {
        if (request.getPassword1().equals(request.getPassword2())) {
            if (Objects.nonNull(userRepository.findByUsername(request.getUsername())))
                return "USERNAME_ALREADY_EXIST";
            User entity = objectMapper.R2E(request);
            entity.setStatus("ACTIVATED");
            entity.setPassword(passwordEncoder.encode(request.getPassword1()));
            userRepository.save(entity);
            return "SUCCESS";
        } else
            return "INVALID_PASS";
    }

    public List<UserResponse> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }
}