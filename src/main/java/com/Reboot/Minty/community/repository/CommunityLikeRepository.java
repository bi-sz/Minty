package com.Reboot.Minty.community.repository;

import com.Reboot.Minty.community.entity.Community;
import com.Reboot.Minty.community.entity.CommunityLike;
import com.Reboot.Minty.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike, Long> {
    Optional<CommunityLike> findByUserAndCommunity(User user, Community community);

    List<CommunityLike> findByCommunity(Community community);
}