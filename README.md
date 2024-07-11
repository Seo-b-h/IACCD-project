# 도로, 도보에 대한 정점, 간선 편집 도구

- 마우스 조작으로 지도 상의 도로, 도보에 대해 위도와 경도로 표현된 정점과 정점의 아이디로 구성된 간선 데이터를 mysql 테이블에 저장할 수 있습니다.

- 해당 프로젝트를 실행시키기 위해
  1. mysql에서 '0_길찾기서비스테이블_쿼리문(2024_0520_1845).txt'의 쿼리문을 실행하여 map database를 만듭니다.
  2. application.yml 파일을 추가하여 다음과 같이 작성합니다.
 ```
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/map?serverTimezone=UTC&characterEncoding=UTF-8
      username: {mysql username}
      password: {mysql password}
      driver-class-name: com.mysql.cj.jdbc.Driver

    messages:
      basename: messages

    jpa:
      hibernate:
        #ddl=auto: create
        ddl-auto: none

      properties:
        hibernate:
          format_sql: true

    thymeleaf:
      cache: false

  logging:
    level:
      #root: warn
      org.hibernate.SQL: debug

  #server:
  #  servlet:
  #    session:
  #      tracking-modes: cookie

  api-key:
    kakao-map-api: //dapi.kakao.com/v2/maps/sdk.js?appkey={카카오 맵 API javascript 키 값}
```
  3. '{mysql username}'을 지우고 mysql에서 사용하는 username을 입력합니다.
  4. '{mysql password}'을 지우고 mysql에서 사용하는 password를 입력합니다.
  5. '{카카오 맵 API javascript 키 값}'을 지우고 카카오 맵 api에서 발급받은 javascript 키 값을 입력합니다.
  6. eclips나 intellij등의 ide를 통해 해당 프로젝트를 실행합니다.
  7. 브라우저에 'localhost:8080'으로 접속하면 도로, 도보에 대한 정점, 간선 편집 도구를 사용할 수 있습니다.
