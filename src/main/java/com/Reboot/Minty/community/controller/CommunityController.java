package com.Reboot.Minty.community.controller;

import com.Reboot.Minty.community.entity.Community;
import com.Reboot.Minty.community.repository.CommunityRepository;
import com.Reboot.Minty.community.service.CommunityService;
import com.Reboot.Minty.member.entity.User;
import com.Reboot.Minty.member.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommunityController {

    private final CommunityService communityService;

    private final UserRepository userRepository;

    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityController(CommunityService communityService, UserRepository userRepository, CommunityRepository communityRepository) {
        this.communityService = communityService;
        this.userRepository = userRepository;
        this.communityRepository = communityRepository;
    }

    @GetMapping("/communityDetail/{postId}")
    public String communityDetail(@PathVariable("postId") Long id, Model model, HttpServletRequest request) {
        Community community = communityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        User user = community.getUser();

        model.addAttribute("community", community);
        model.addAttribute("user", user);

        return "/community/communityDetail";
    }

    @PostMapping("/api/posts")
    public ResponseEntity<Community> addPost(@RequestBody Community community, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        community.setUser(user);

        Community savedCommunity = communityService.addPost(community);

        return ResponseEntity.ok(savedCommunity);
    }

    @GetMapping("/communityList")
    public String communityList(Model model) {
        List<Community> communities = communityRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("communities", communities);
        return "/community/communityList";
    }



}
