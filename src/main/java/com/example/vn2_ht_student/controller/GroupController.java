package com.example.vn2_ht_student.controller;
import com.example.vn2_ht_student.Utils.Constants;
import com.example.vn2_ht_student.model.dto.reponse.GroupResponseDto;
import com.example.vn2_ht_student.model.dto.reponse.ResponseDTO;
import com.example.vn2_ht_student.model.dto.request.GroupRequestDto;
import com.example.vn2_ht_student.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/list")
    public List<GroupResponseDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create ( @RequestBody GroupRequestDto request){
        try {
            groupService.create(request);
            return ResponseEntity.ok("successfully created");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("error"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("system error has occurred!");
        }
    }
        @PostMapping("/update{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody GroupRequestDto request) {
        try {
            groupService.update(id,request);
            return ResponseEntity.ok("successfully created");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("error"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("system error has occurred!");
        }
    }
    @PostMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.ok("Xóa nhóm thành công");
    }

}
