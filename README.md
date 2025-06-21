# 플로우 파일 확장자 차단 과제

### Tech Stack
#### Language & Framework
- Java 17
- Spring 6.2.7
- Spring Boot 3.4.6

#### Database & ORM
- MySQL
- Spring Data JPA

#### DevOps
- AWS EC2 t2.micro
- Docker
- Github Action

### 고려사항
- 커스텀 확장자 중복체크
```java
// 커스텀 확장자 중복체크
if (repository.existsByName(name)) {
    throw new BusinessException(ErrorCode.ALREADY_CREATED_EXTENSION);
}
```
- 커스텀 확장자에서 고정 확장자를 추가할 때 예외처리
```java
// 고정 확장자 추가 시도 예외처리
if (isFixedExtension(name)) {
    throw new BusinessException(ErrorCode.FORBIDDEN_FIXED_EXTENSION);
}

private boolean isFixedExtension(String name) {
    return FixedExtensionType.getNames().stream()
            .anyMatch(fixedName -> fixedName.equalsIgnoreCase(name));
}
```
- 커스텀 확장자 유효성 검사
  - 글자 수 최대 20자까지만 입력 가능 
  - 특수 문자, 공백, 마침표 등 포함 불가
  - 예) \<h1>확장자\</h1>
```java
@Size(max = 20, message = "확장자는 최대 20자까지 입력할 수 있습니다.")
@NotBlank(message = "확장자를 입력해주세요.")
@Pattern(
        regexp = "^[^\\\\/:*?\"<>|\\.\\s\\x00-\\x1F]+$",
        message = "특수 문자, 공백, 마침표 등을 포함할 수 없습니다."
)
```
- 이미 삭제한 커스텀 확장자 예외처리
```java
if (!repository.existsById(id)) {
    throw new BusinessException(ErrorCode.ALREADY_DELETED_EXTENSION);
}
```
- 커스텀 확장자 200개 이상 입력 시 예외처리
```java
// 커스텀 확장자 개수 체크
if (repository.countAllBy() >= MAX_CUSTOM_EXTENSION_COUNT) {
    throw new BusinessException(ErrorCode.EXCEED_TOTAL_CUSTOM_COUNT);
}
```
- 추후 고정 확장자가 추가될 수 있는 상황을 고려하여 Enum으로 관리
  - 고정 확장자 목록을 Enum 값을 조회하여 표시
  - Check 시 DB에 없으면 생성, 있으면 is_checked 값만 수정
  - 고정 확장자 추가 시 직접 DB에 INSERT 할 필요 없이 Enum 값만 추가해주면 됨