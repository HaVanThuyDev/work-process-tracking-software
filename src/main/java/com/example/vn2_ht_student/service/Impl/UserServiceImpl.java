package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.UserDto;
import com.example.vn2_ht_student.model.entity.User;
import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.dto.reponse.LoginReponseDto;
import com.example.vn2_ht_student.model.dto.reponse.UserReponseDto;
import com.example.vn2_ht_student.model.dto.request.RegisterRequest;
import com.example.vn2_ht_student.model.dto.request.UserRequestDto;
import com.example.vn2_ht_student.model.enums.Status;
import com.example.vn2_ht_student.repository.RolePermissionScopeRepository;
import com.example.vn2_ht_student.repository.UserRepository;
import com.example.vn2_ht_student.security.JwtProvider;
import com.example.vn2_ht_student.security.annotation.PermissionCheck;
import com.example.vn2_ht_student.service.MediaStorageService;
import com.example.vn2_ht_student.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
//    @PermissionCheck(resource = { Role.LEADER, Role.STUDENT, Role.LECTURER }, action = Action.STUDENT_UPDATE)
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
//    @PermissionCheck(resource = {Role.LECTURER,Role.LEADER}, action = Action.STUDENT_VIEW)
    public List<UserReponseDto> getAllUsers() {
       return userRepository.findAll().stream().map(user -> {
           UserReponseDto list=new UserReponseDto(user);
           return list;
       } ).toList();
    }
//    @PermissionCheck(resource = Role.LECTURER, action = Action.STUDENT_DELETE)
    @Override
    public List<UserReponseDto> delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new BadCredentialsException("user not found"));
        userRepository.delete(user);
        return new ArrayList<>();
    }
//    @PermissionCheck(resource = {Role.LECTURER,Role.LEADER}, action = Action.STUDENT_SEARCH)
    @Override
    public List<UserReponseDto> search(String fullName, String msv) {
       List<User> users = userRepository.searchBy(fullName, msv);
        return  users.stream().map(UserReponseDto :: new).toList();
    }
//    @PermissionCheck(resource = Role.LECTURER, action = Action.STUDENT_VIEW)

    @Override
    public UserReponseDto GetDetail(Long id) {
       User user = userRepository.findById(id).orElseThrow(()-> new BadCredentialsException("user not found"));
       UserReponseDto userReponseDto = new UserReponseDto(user);
       return userReponseDto;
    }

    @Override
    public void importUsersFromExcel(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                String fullName = getCellValue(row.getCell(1));
                String msv =getCellValue(row.getCell(2));
                String classId  = getCellValue(row.getCell(4));
                String email    = getCellValue(row.getCell(5));
                if (email.isEmpty() || classId.isEmpty() || fullName.isEmpty()) {
                    continue;
                }
                if (userRepository.existsByGmail(email)) {
                    continue;
                }
                User user = new User();
                user.setFullName(fullName);
                user.setMsv(msv);
                user.setGmail(email);
                user.setClassId(classId);
                user.setPassword(passwordEncoder.encode("Ab123456@"));
                user.setRole(Role.STUDENT);
                user.setStatus(Status.ACTIVE);
                userRepository.save(user);
            }

        } catch (Exception e) {
            throw new RuntimeException("IMPORT EXCEL FAILED: " + e.getMessage());
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            default -> "";
        };
    }

}
