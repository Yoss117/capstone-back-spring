package com.website.user.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class KakaoUserResponse {
    private Long id;

    @JsonProperty("has_sign_up")
    // 자동 연결 설정을 비활성화 한 경우만 존재
    // 수동 연결 API의 호출 완료 여부
    // false: 연결 대기 상태
    // true: 연결 상태
    private Boolean hasSignUp;
    @JsonProperty("connected_at")
    //서비스에 연결 완료된 시각 UTC
    private LocalDateTime connectedAt;
    @JsonProperty("synched_at")
    // 카카오싱크 간편 가입으로 로그인한 시각
    private LocalDateTime synchedAt;
    // 사용자 프로퍼티
    private KakaoProperties properties;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;


}
