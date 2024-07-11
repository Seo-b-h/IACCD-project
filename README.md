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

### 사용 예시
- 다음과 같이 카카오 지도 위에서 정점과 간선을 추가하고, 이를 시각화하여 볼 수 있습니다.
![image](https://github.com/Seo-b-h/IACCD-project/assets/123562354/b25ec933-9d16-4092-9416-9c54c06ddba0)
- 정점, 간선, 입력한 건물이나 랜드마크 데이터는 다음과 같이 mysql이나 mysql workbench를 사용해 map database에서 확인할 수 있습니다.
![image](https://github.com/Seo-b-h/IACCD-project/assets/123562354/deb351b0-c538-4172-ae22-3815503ef465)
![image](https://github.com/Seo-b-h/IACCD-project/assets/123562354/d545a86d-c5b6-4707-aa73-295a602f3109)
![image](https://github.com/Seo-b-h/IACCD-project/assets/123562354/dc47b53c-1eb3-4c9e-b44d-c49dd1ad37b2)


