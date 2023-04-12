package com.example.guestbook.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.guestbook.entity.ClubMember;
import com.example.guestbook.repository.ClubMemberRepository;
import com.example.guestbook.security.dto.ClubAuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {

  private final ClubMemberRepository clubMemberRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("ClubUserDetailsService loadUserByUsername " + username);

    Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);

    if(result.isPresent()){
      throw new UsernameNotFoundException("Check Email or Social");
    }

    ClubMember clubMember = result.get();

    log.info("--------------------------");
    log.info(clubMember);

    ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
      clubMember.getEmail(),
      clubMember.getPassword(),
      clubMember.isFromSocial(),
      clubMember.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet())
    );
    clubAuthMember.setName(clubMember.getName());
    clubAuthMember.setFromSocial(clubMember.isFromSocial());
    
    return clubAuthMember;
  }
}
