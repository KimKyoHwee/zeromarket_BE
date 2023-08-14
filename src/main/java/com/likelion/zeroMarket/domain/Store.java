package com.likelion.zeroMarket.domain;


import com.likelion.zeroMarket.dto.StoreCreateRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @OneToMany(mappedBy = "store")
    private final List<Product> members=new ArrayList<>();
    //이거 final붙이는게 맞아?

    @OneToOne(mappedBy = "store")  
    //JPA에서는 일대일 대응은 무조건 양방향이여야 됨 즉, 양쪽 엔티티에서 OneToOne 해줘야됨
    private User user;

    @OneToMany(mappedBy = "store")
    private final List<Review> reviews=new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private final List<Sell> Sells=new ArrayList<>();

    // 자꾸 오류뜸 ㅠㅠ
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private Long id;

    @Column
    private String picture;

    @Column
    private String name;

    @Column
    private String explanation;


    @Column
    private Double latitude;  //위도

    @Column
    private Double longitude;  //경도
    
    @Column
    private String address;  //~~동

    @Column
    private String bank;

    @Column
    private String account;
    public static Store from(StoreCreateRequestDto store) {
        return Store.builder()
                .picture(store.getPicture())
                .name(store.getName())
                .explanation(store.getExplanation())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .bank(store.getBank())
                .account(store.getAccount())
                .build();
    }
}


