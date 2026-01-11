package com.example.vn2_ht_student.security.permission;

import com.example.vn2_ht_student.model.enums.Action;
import com.example.vn2_ht_student.model.enums.ResourceType;
import com.example.vn2_ht_student.model.enums.Role;
import com.example.vn2_ht_student.model.enums.Scope;
import com.example.vn2_ht_student.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Policy {

    private final TaskRepository taskRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final PeerReviewRepository peerReviewRepository;
    private final ContributionRepository contributionRepository;
    private final ActivityLogRepository activityLogRepository;
    public boolean allow(Role resource, Action action, Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (hasAuthority(auth, resource + "." + action + Scope.ALL)) {
            return true;
        }
        if (hasAuthority(auth, resource + "." + action + Scope.OWN)) {
            return true;
        }
        if (hasAuthority(auth, resource + "." + action + Scope.GROUP)) {
            return true;
        }
        if (hasAuthority(auth, resource + "." + action + Scope.MANAGED_GROUP)) {
            return true;
        }
        if (hasAuthority(auth, resource + "." + action + Scope.TASK)) {
            return true;
        }
        return false;
    }
    private boolean isOwner(ResourceType resource, Long resourceId, Long userId) {
        return switch (resource) {

            case USER -> resourceId.equals(userId);

            case TASK -> taskRepository.findById(resourceId)
                            .map(t -> t.getCreatedBy().getId().equals(userId))
                            .orElse(false);

            case PEER_REVIEW -> peerReviewRepository.findById(resourceId)
                            .map(r -> r.getEvaluated().getId().equals(userId))
                            .orElse(false);

            case CONTRIBUTION_SCORE -> contributionRepository.findById(resourceId)
                            .map(c -> c.getUser().getId().equals(userId))
                            .orElse(false);

            case GROUP_MEMBER -> groupMemberRepository.findById(resourceId)
                            .map(m -> m.getUser().getId().equals(userId))
                            .orElse(false);

            default -> false;
        };
    }
    private Long resolveGroupId(ResourceType resource, Long resourceId) {
        return switch (resource) {

            case GROUP ->
                    resourceId;

            case TASK -> taskRepository.findById(resourceId)
                            .map(t -> t.getGroup().getId())
                            .orElse(null);

            case ACTIVITY_LOG -> activityLogRepository.findById(resourceId)
                            .map(l -> l.getGroup().getId())
                            .orElse(null);

            case PEER_REVIEW -> peerReviewRepository.findById(resourceId)
                            .map(r -> r.getGroup().getId())
                            .orElse(null);

            case CONTRIBUTION_SCORE -> contributionRepository.findById(resourceId)
                            .map(c -> c.getGroup().getId())
                            .orElse(null);

            default -> null;
        };
    }
    private boolean hasAuthority(Authentication auth, String authority) {
        return auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }
}
