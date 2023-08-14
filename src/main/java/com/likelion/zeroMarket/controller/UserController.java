package com.likelion.zeroMarket.controller;

import com.likelion.zeroMarket.domain.User;
import com.likelion.zeroMarket.dto.StoreCreateRequestDto;
import com.likelion.zeroMarket.dto.UserSignUpRequestDto;
import com.likelion.zeroMarket.dto.UserStoreDto;
import com.likelion.zeroMarket.exception.DataNotFoundException;
import com.likelion.zeroMarket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor  //final붙은놈 자동 주입
@RequestMapping("/user")
@Tag(name = "User", description = "가입하는 사용자 모델")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원 가입", description = "회원 정보, 회원의 가게정보 다 넘겨줘야되는데, 좀 복잡하게 받아요")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공!"),
            @ApiResponse(responseCode="500", description = "회원이나 가게정보중 한개라도 빠지면 에러(아이디 겹쳐도 에러안뜸..)")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserStoreDto userStoreDto){
        //ResponseEntity는 상태 전달 엔티티, RequestBody는 한개밖에 못써서 여러개 받으려면 따로 클래스 지정
        UserSignUpRequestDto userDto=userStoreDto.getUserSignUpRequestDto();
        StoreCreateRequestDto storeDto=userStoreDto.getStoreCreateRequestDto();
        System.out.println("userDto의 name = " + userDto.getNickname());
        //형님 여기서 받는 2개 Dto 모두 필드들이 NULL로 넘어와..
        userService.signUp(userDto, storeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "비번찾기",description = "아이디와 계좌번호 받아서, 일치하면 비번 반환")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비번찾기 성공"),
            @ApiResponse(responseCode = "500", description = "아이디나 계좌번호중 한개이상 틀림")
    })
    @GetMapping("/{ids}/{nickname}")  //비번찾기(아이디랑 닉네임으로)
    public ResponseEntity<?> findPw(@PathVariable("ids") String ids,
                                    @PathVariable("nickname") String nickname){
        try{
            User user=userService.getPassword(ids, nickname);
            System.out.println("password:"+user.getPassword());
            System.out.println(user.getPassword());
            return ResponseEntity.ok(user.getPassword());
        }catch(DataNotFoundException e){
            return ResponseEntity.notFound().build();
            //.build로 내용 없이 ResponseEntity 객체 완성해서 return
        }
    }

    @Operation(summary = "로그인", description = "아이디 비번 일치하면 회원ID 반환, 그거로 그 회원의 가게 접근")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공(회원ID 반환)"),
            @ApiResponse(responseCode="500", description = "로그인 실패. 아이디나 패스워드중 한개 오류")
    })
    @PostMapping("/{ids}/{password}")  //로그인하면 UserId(PK) 리턴
    public ResponseEntity<Long> logIn(@PathVariable("ids") String ids,
                                      @PathVariable("password") String password){
        try{
            User user=userService.getUserId(ids, password);
            return ResponseEntity.ok(user.getId());
        }catch(DataNotFoundException e) {
            System.out.println("error");
            return ResponseEntity.notFound().build();
            //.build로 내용 없이 ResponseEntity 객체 완성해서 return
        }
    }
}
