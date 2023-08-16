package com.likelion.zeroMarket.dto;

import com.likelion.zeroMarket.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogInDto {
    private Long userId;
    private String nickname;
    private Double longitude;
    private Double latitude;

    public static UserLogInDto from(User user){
        return UserLogInDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .longitude(user.getLongitude())
                .latitude(user.getLatitude())
                .build();
    }
}
