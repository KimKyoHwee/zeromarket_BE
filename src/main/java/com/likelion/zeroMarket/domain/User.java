package com.likelion.zeroMarket.domain;


import com.likelion.zeroMarket.dto.UserSignUpRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    //외래키를 User가 가짐으로써 User가 Store에 접근 가능
    //CascadeType.ALL 로 이 엔티티의 변경사항을 상대 엔티티에도 전달
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="store_id")
    private Store store;

    @Column
    private String ids;

    @Column
    private String password;


    @Column
    private String nickname;  //~~동으로 쇼부

    private Double longitude;
    private Double latitude;


    public static User from(UserSignUpRequestDto userDto){
        return User.builder()
                .ids(userDto.getIds())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .build();
    }


}
