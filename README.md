# Spring-Boot-JWT-Tutorial

JWT

-   RFC 7519 웹 표준
-   : JSON 객체를 사용해서 토큰 자체에 정보들을 저장하고 있는 WEB TOKEN
-   구성 요소
    1.  Header : Signature를 hashing 하기 위한 Algorithm 정보
    2.  Payload : Server to Client, 시스템에서 실제로 사용될 정보의 내용
    3.  Signature : 토큰의 유효성을 검증하기 위한 문자열
-   장점
    -   중앙의 인증 서버, 데이터 스토어에 대한 의존성이 없음
    -   시스템 수평 확장에 유리함
    -   URI, Cookie, Header에 모두 사용 가능(Base64 URL Safe Encoding)
-   단점
    -   Payload 정보가 많아지면 네트워크 사용량이 급증하게 된다. 설계 시 고려 필요
    -   서버에서 클라이언트의 토큰을 조작 할 수 없음(토큰이 클라이언트에 저장되기 떄문)

---

### Chapter01

-   프로젝트 생성, REST API 테스트

### Chapter02

-   401 unauthorized 해결을 위한 Security 설정 >> [sourceCode](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/jwt/JwtAuthenticationEntryPoint.java)
-   Datasource, JPA 설정 >> [sourceCode](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/config/SecurityConfig.java)
-   Entity 생성 >> [package](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/entity/)
-   H2 Console 결과 확인
    -   localhost:8080/h2-console/ => connect
    -   SELECT \* FROM "USER"

### Chapter03

-   JWT 설정 추가
-   JWT 관련 코드 개발 >> [jwt](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/jwt/)
-   Security 설정 추가 >> [SecurityConfig](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/config/SecurityConfig.java)

### Chapter04

-   외부와의 통신에 사용할 DTO 클래스 생성 >> [dto](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/dto/)
-   Repository 관련 코드 생성 >> [repository](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/repository/)
-   로그인 API, 관련 로직 생성 >> [AuthController](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/controller/AuthController.java), [CustomUserDetailsService](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/service/CustomUserDetailsService.java)

### Chapter05

-   회원가입 API 생성 >> [UserService](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/service/UserService.java), [UserController](/jwt_tutorial/src/main/java/me/sungwon/jwt_tutorial/controller/UserController.java)
-   권한검증 확인
    -   GET : /api/user/{username}
    -   Authorization : {{jwt_tutorial_token}}

![image](/jwt_tutorial/packageStructure.png)

---

5 Lectures, 43 Minutes  
[강의 바로가기](https://www.inflearn.com/course/스프링부트-jwt/dashboard)
