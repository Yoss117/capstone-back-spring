package com.website.user;

import com.website.entity.User;
import com.website.repository.UserRepository;
import com.website.security.jwt.JWTUtil;
import com.website.user.kakao.dto.KakaoAccount;
import com.website.user.kakao.dto.KakaoUserResponse;
import com.website.user.dto.SignupRequestDto;
import com.website.user.kakao.Kakao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 회원 가입 메서드
     * @param req 회원 가입 필요 정보
     * @return 클라이언트에 return할 메세지
     */
    public String signup(SignupRequestDto req) {
        if(req.getUserId()==null || req.getNickname()==null || req.getPassword()==null){
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
        if(userRepository.existsByUserId(req.getUserId())){
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        if(userRepository.existsByNickname(req.getNickname())){
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        User newUser = new User(
                req.getUserId(),
                req.getNickname(),
                bCryptPasswordEncoder.encode(req.getPassword()),
                "ROLE_USER",
                LocalDateTime.now(),
                true
        );
        userRepository.save(newUser);
        return "회원가입에 성공했습니다!";
    }
    @Transactional
    public String socialKaKaoLogin(String code) {
        Kakao kakao = new Kakao(code);
        KakaoUserResponse user = kakao.getUser();
        User loginUser = checkUser(
        "kakao",
                user.getId(),
                user.getKakaoAccount().getProfile().getNickname(),
                user.getKakaoAccount().getProfile().getProfileImageUrl());
        return jwtUtil.createJwt(loginUser.getUserCode(), loginUser.getName(), loginUser.getRole(), 1000 * 60 * 60 * 10L);
    }
    private User checkUser(String provider, Long userCode, String name, String profileImage) {
        return userRepository.findByOauthId(userCode)
                .orElseGet(()-> {
                    User u = new User();
                    u.setOauthId(userCode);
                    u.setName(name);
                    u.setProfileImage(profileImage);
                    u.setOauthProvider(provider);
                    u.setRole("ROLE_USER");
                    u.setEnable(true);
                    u.setCreateAt(LocalDateTime.now());
                    return userRepository.save(u);
                });
    }
}
