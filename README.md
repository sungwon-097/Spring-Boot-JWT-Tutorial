# Spring-Boot-JWT-Tutorial

JWT

-   RFC 7519 웹 표준
-   : JSON 객체를 사용해서 토근 자체에 정보들을 저장하고 있는 WEB TOKEN
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
