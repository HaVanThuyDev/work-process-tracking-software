package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.UserDto;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.dto.reponse.LoginReponseDto;
import com.example.vn2_ht_student.model.dto.reponse.UserReponseDto;
import com.example.vn2_ht_student.model.dto.request.RegisterRequest;
import com.example.vn2_ht_student.model.dto.request.UserRequestDto;
import com.example.vn2_ht_student.repository.RolePermissionScopeRepository;
import com.example.vn2_ht_student.repository.UserRepository;
import com.example.vn2_ht_student.security.JwtProvider;
import com.example.vn2_ht_student.service.MediaStorageService;
import com.example.vn2_ht_student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final MediaStorageService mediaStorageService;
    private  final  UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final   PasswordEncoder passwordEncoder;
    private final RolePermissionScopeRepository rolePermissionScopeRepository;

    @Override
    public LoginReponseDto login(String gmail, String passwords) {
        User user= userRepository.findByGmail(gmail).orElseThrow(()-> new BadCredentialsException("gmail is incorrect"));
        if (!passwordEncoder.matches(passwords,user.getPassword())){
           throw new BadCredentialsException("password is incorrect");
       }
       if(user.getRole()==null){
           throw new BadCredentialsException("role is incorrect");
       }
        Role role =user.getRole();
        List<String> permissions = rolePermissionScopeRepository.findPermissionsByRole(role).stream().map(p ->
        p.getResource().trim() + "." + p.getAction().toString() + ":" + p.getScope().name().trim()).distinct().toList();
        String token = jwtProvider.generateToken(user.getId(), user.getGmail(), role, permissions);
        return new LoginReponseDto(
                user.getFullName(),
                user.getRole(),
                token
        );
    }

    @Override
    public User register(RegisterRequest request) {
        if(userRepository.findByGmail(request.getGmail()).isPresent()){
            throw new BadCredentialsException("gmail is incorrect");
        }
        User user = new User();
        user.setMsv(request.getMsv());
        user.setFullName(request.getFullName());
        user.setGmail(request.getGmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public UserDto update(Long id, UserRequestDto request) {
        User user = userRepository.findById(id).orElseThrow(()-> new BadCredentialsException("user not found"));
        if(request.getFullName() !=null && request.getFullName().isBlank())user.setFullName(request.getFullName());
        if (request.getMsv() !=null && request.getMsv().isBlank())user.setMsv(request.getMsv());
        if(request.getPassword() !=null && request.getPassword().isBlank())user.setPassword(request.getPassword());
        if (request.getImg() != null) {
            Long imageId = mediaStorageService.saveBase64ImageAndReturnId(request.getImg());
            user.setImg(imageId.toString());
        }
        if(request.getImg() !=null && request.getImg().isBlank())user.setImg(request.getImg());
        User User = userRepository.save(user);
        return new UserDto(User);
    }

    @Override
    public List<UserReponseDto> getAllUsers() {
       return userRepository.findAll().stream().map(user -> {
           UserReponseDto list=new UserReponseDto(user);
           return list;
       } ).toList();
    }

    @Override
    public List<UserReponseDto> delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new BadCredentialsException("user not found"));
        userRepository.delete(user);
        return new ArrayList<>();
    }

    @Override
    public List<UserReponseDto> search(String fullName, String msv) {
       List<User> users = userRepository.searchBy(fullName, msv);
        return  users.stream().map(UserReponseDto :: new).toList();
    }

    @Override
    public UserReponseDto GetDetail(Long id) {
       User user = userRepository.findById(id).orElseThrow(()-> new BadCredentialsException("user not found"));
       UserReponseDto userReponseDto = new UserReponseDto(user);
       return userReponseDto;
    }
}
