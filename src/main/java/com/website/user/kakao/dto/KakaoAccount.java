package com.website.user.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class KakaoAccount {
    @JsonProperty("has_email")
    private Boolean hasEmail;
    private String email;

    private Profile profile;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
    public static class Profile {
        private String nickname;
        @JsonProperty("thumbnail_image_url")
        private String thumbnailImageUrl;
        @JsonProperty("profile_image_url")
        private String profileImageUrl;
    }
}
