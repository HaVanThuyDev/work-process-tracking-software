package com.example.vn2_ht_student.repository.custome;

public interface GroupMemberCustomRepository {
    int findMaxParticipationByGroupId(Long groupId);
}
