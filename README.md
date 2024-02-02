# [개발자] 김기윤 과제 설명

## 사용 기술
- Java
- Spring
- Spring Boot
- Spring Data JPA
- JSP
- JSTL
- JavaScript
- JQuery
- AWS EC2 Ubuntu Server
- AWS RDS MySQL

## 고려 사항
### 1. 커스텀 확장자 중복체크
이미 차단된 확장자를 다시 차단하려 할때 EXTENSION_DUPLICATE_EXCEPTION 에러 발생

### 2. 삭제 null 체크
저장되어 있지 않은 데이터를 삭제하려고 할때 EXTENSION_NOT_EXIST_EXCEPTION 에러 발생

### 3. DB 구조
고정 확장자와 커스텀 확장자 테이블을 분리하는 것을 고려했지만 테이블을 나눌 경우 조회 시 select 쿼리가 두번 나가게 되고 만약 하나의 테이블에서 관리하여 한번에 모든 차단 확장자 데이터를 조회해와도 커스텀 확장자 최대 갯수가 200개 이므로 성능에 문제가 없다고 판단.<br/>
고정 확장자와 커스텀 확장자를 하나의 테이블에서 관리하고 isFixExtension 컬럼으로 구분  

### 4. 로그 테이블 생성
차단 확장자 설정 기록을 저장하기 위해 로그 테이블을 생성.<br/>
로그 데이터를 저장하는 것은 비즈니스 로직에 영향을 미치지 않기 때문에 예외가 발생해도 차단 확장자는 DB에 저장이 되어야 함.<br/>
로그 데이터 저장 트랜잭션의 전파(propagation) 속성을 REQUIRES_NEW로 설정하여 기존의 차단 확장자 저장 트랜잭션에 종속 되지않고 새로운 트랜잭션을 생성하도록 구현.<br/>
즉, 로그 데이터 저장에 실패해도 차단 확장자 데이터는 롤백되지 않음