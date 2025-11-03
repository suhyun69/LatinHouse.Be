# LatinHouse Backend - Claude AI 개발 가이드

## 프로젝트 개요

**LatinHouse Backend**는 라틴댄스 하우스의 백엔드 시스템입니다.

### 기술 스택
- **언어:** Java 21
- **프레임워크:** Spring Boot 3.5.5
- **빌드 툴:** Gradle
- **데이터베이스:** H2 (개발), JPA/Hibernate
- **인증:** Spring Security + JWT
- **API 문서:** Swagger/OpenAPI (springdoc-openapi)

### 주요 설정
- **포트:** 8080
- **활성 프로파일:** local
- **DDL Auto:** create (개발 환경)
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **H2 Console:** http://localhost:8080/h2-console

---

## 아키텍처

이 프로젝트는 **헥사고날 아키텍처(Ports & Adapters)** 패턴을 따릅니다.

```
src/main/java/com/latinhouse/backend/
├── adapter/                    # 어댑터 계층
│   ├── in/web/                # Inbound: REST API 컨트롤러
│   │   ├── home/              # Home 도메인 컨트롤러
│   │   ├── signin/            # 로그인 컨트롤러
│   │   ├── signup/            # 회원가입 컨트롤러
│   │   └── my/                # 마이페이지 컨트롤러
│   └── out/persistence/       # Outbound: JPA 퍼시스턴스
│       ├── user/              # User 엔티티/리포지토리
│       ├── todo/              # Todo 엔티티/리포지토리
│       └── profile/           # Profile 엔티티/리포지토리
├── application/               # 어플리케이션 계층 (UseCase 구현)
│   ├── home/                  # Home UseCase
│   ├── signin/                # Signin UseCase
│   ├── signup/                # Signup UseCase
│   └── my/                    # My UseCase
├── domain/                    # 도메인 계층 (비즈니스 로직)
│   ├── user/                  # User 도메인
│   ├── todo/                  # Todo 도메인
│   └── profile/               # Profile 도메인
├── port/                      # 포트 인터페이스
│   ├── in/                    # Inbound 포트 (UseCase)
│   └── out/                   # Outbound 포트 (Repository)
├── common/                    # 공통 유틸리티
│   ├── exception/             # 커스텀 예외
│   └── mapper/                # 매퍼 전략 인터페이스
├── config/                    # 설정 클래스
│   ├── jwt/                   # JWT 설정
│   ├── SecurityConfig.java
│   ├── OpenApiConfig.java
│   └── AuditorConfig.java
└── util/                      # 유틸리티
```

---

## 계층별 역할 및 규칙

### 1. Domain Layer (도메인 계층)

**위치:** `domain/`

**역할:**
- 순수 비즈니스 로직을 담당
- 외부 프레임워크/라이브러리에 의존하지 않음
- 도메인 엔티티, 값 객체, 도메인 서비스 포함

**구조:**
```
domain/user/
├── User.java                  # 도메인 모델
├── Role.java                  # Enum (역할)
├── CustomUserDetails.java     # Spring Security 연동
├── command/
│   └── AddUserCommand.java    # 커맨드 객체
└── service/
    ├── UserService.java       # 도메인 서비스
    └── CustomUserDetailsService.java
```

**규칙:**
- 도메인 모델은 `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` 사용
- 비즈니스 로직은 도메인 모델 또는 도메인 서비스에 구현
- Command 객체는 `command/` 패키지에 위치
- 도메인 서비스는 `service/` 패키지에 위치

**예시:**
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private Sex sex;
    private Role role;
    private String profileId;

    public static User from(AddUserCommand command) {
        return User.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .sex(command.getSex())
                .role(command.getRole())
                .build();
    }
}
```

---

### 2. Application Layer (어플리케이션 계층)

**위치:** `application/`

**역할:**
- UseCase 구현 (비즈니스 유스케이스 조율)
- 도메인 서비스 호출 및 트랜잭션 관리
- DTO 변환 (App DTO ↔ Domain/Command)

**구조:**
```
application/home/
├── HomeUseCaseImpl.java       # UseCase 구현체
└── mapper/
    ├── TodoAppMapper.java     # 매퍼 인터페이스
    └── strategy/
        ├── AddTodoAppStrategy.java
        └── GetTodoAppStrategy.java
```

**규칙:**
- UseCase 구현체는 `{Domain}UseCaseImpl` 네이밍
- `@Service` 어노테이션 사용
- `port.in.{domain}.{Domain}UseCase` 인터페이스 구현
- 매퍼는 전략 패턴을 사용하여 DTO 변환

**예시:**
```java
@Service
@RequiredArgsConstructor
public class HomeUseCaseImpl implements HomeUseCase {

    private final TodoAppMapper todoAppMapper;
    private final TodoService todoService;

    @Override
    public AddTodoAppResponse addTodo(AddTodoAppRequest appReq) {
        Todo todo = todoService.addtodo(todoAppMapper.toCommand(appReq));
        return todoAppMapper.toAppRes(todo, AddTodoAppResponse.class);
    }

    @Override
    public GetTodoAppResponse done(DoneTodoAppRequest appReq) {
        Todo todo = todoService.getTodo(appReq.getNo())
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        if(!todo.getCreatedBy().equals(appReq.getEmail()) && !appReq.getIsAdmin())
            throw new ForbiddenException("권한이 없습니다.");

        todo.done();
        todoService.update(todo);

        return todoAppMapper.toAppRes(todo, GetTodoAppResponse.class);
    }
}
```

---

### 3. Adapter Layer (어댑터 계층)

#### 3.1 Inbound Adapter (Web Controller)

**위치:** `adapter/in/web/`

**역할:**
- REST API 엔드포인트 제공
- 요청/응답 DTO 변환 (Web DTO ↔ App DTO)
- 인증/인가 처리 (`@PreAuthorize`)
- Swagger 문서화

**구조:**
```
adapter/in/web/home/
├── ApiV1HomeController.java  # 컨트롤러
├── dto/
│   ├── AddTodoWebRequest.java
│   ├── AddTodoWebResponse.java
│   ├── GetTodoWebResponse.java
│   └── DoneTodoWebRequest.java
└── mapper/
    ├── HomeWebMapper.java
    └── strategy/
        └── ...Strategy.java
```

**규칙:**
- 컨트롤러는 `ApiV1{Domain}Controller` 네이밍
- `@RestController`, `@RequestMapping("/api/v1/{domain}")` 사용
- `@Tag`, `@Operation` 어노테이션으로 Swagger 문서화
- `@PreAuthorize("isAuthenticated()")` 로 인증 체크
- Web DTO는 `dto/` 패키지에 위치
- 요청 DTO는 `@Valid` 사용하여 유효성 검증
- ResponseEntity로 HTTP 상태 코드 명시

**예시:**
```java
@RestController
@RequestMapping("/api/v1/home")
@Tag(name = "Home", description = "Home API Document")
@RequiredArgsConstructor
public class ApiV1HomeController {

    private final HomeUseCase homeUseCase;
    private final HomeWebMapper homeWebMapper;

    @PostMapping("/todo")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Add Todo", description = "Add Todo")
    public ResponseEntity<AddTodoWebResponse> addTodo(
            @Valid @RequestBody AddTodoWebRequest webReq) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(homeWebMapper.toWebRes(
                    homeUseCase.addTodo(homeWebMapper.toAppReq(webReq))));
    }

    @PutMapping("/done/{no}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Done Todo", description = "Done Todo")
    public ResponseEntity<GetTodoWebResponse> doneTodo(
            @PathVariable Long no,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        DoneTodoWebRequest webReq = DoneTodoWebRequest.builder()
                .no(no)
                .email(userDetails.getUsername())
                .isAdmin(userDetails.getRole().isAdmin())
                .build();
        return ResponseEntity.ok(
            homeWebMapper.toWebRes(homeUseCase.done(homeWebMapper.toAppReq(webReq))));
    }
}
```

#### 3.2 Outbound Adapter (Persistence)

**위치:** `adapter/out/persistence/`

**역할:**
- JPA 엔티티 및 리포지토리 관리
- 도메인 모델 ↔ JPA 엔티티 변환
- Outbound Port 구현

**구조:**
```
adapter/out/persistence/user/
├── UserPersistenceAdapter.java    # Adapter (Port 구현)
├── entity/
│   └── UserJpaEntity.java         # JPA 엔티티
├── repository/
│   └── UserRepository.java        # Spring Data JPA Repository
└── mapper/
    ├── UserPersistenceMapper.java
    └── strategy/
        └── UserStrategy.java
```

**규칙:**
- Adapter는 `{Domain}PersistenceAdapter` 네이밍
- `@Component` 어노테이션 사용
- `port.out.{domain}.{Create/Read/Update}Port` 인터페이스 구현
- JPA 엔티티는 `{Domain}JpaEntity` 네이밍
- JPA 엔티티는 `BaseEntity` 상속 (auditing 필드 포함)
- Repository는 `JpaRepository` 확장

**예시:**
```java
@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort, ReadUserPort, UpdateUserPort {

    private final UserPersistenceMapper userPersistenceMapper;
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userPersistenceMapper.toDomain(
            userRepository.save(userPersistenceMapper.toEntity(user)));
    }

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findById(email)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public User update(User toBe) {
       return userPersistenceMapper.toDomain(
           userRepository.save(userPersistenceMapper.toEntity(toBe)));
    }
}
```

---

### 4. Port Layer (포트 계층)

**위치:** `port/`

**역할:**
- 어플리케이션 계층과 외부 세계를 연결하는 인터페이스 정의

**구조:**
```
port/
├── in/                        # Inbound Port (UseCase 인터페이스)
│   └── home/
│       ├── HomeUseCase.java
│       └── dto/
│           ├── AddTodoAppRequest.java
│           └── AddTodoAppResponse.java
└── out/                       # Outbound Port (Repository 인터페이스)
    └── user/
        ├── CreateUserPort.java
        ├── ReadUserPort.java
        └── UpdateUserPort.java
```

**규칙:**
- Inbound Port는 UseCase 인터페이스
- Outbound Port는 Repository 인터페이스 (CRUD 기능별 분리)
- App DTO는 `port/in/{domain}/dto/` 에 위치

---

### 5. Common Layer (공통 계층)

**위치:** `common/`

**역할:**
- 전역 예외 처리
- 공통 매퍼 전략 인터페이스
- 공통 엔티티 (BaseEntity)

**예외 처리:**
```java
// 커스텀 예외
- NotFoundException        # 404 Not Found
- BadRequestException      # 400 Bad Request
- ForbiddenException       # 403 Forbidden
- DuplicateMemberException # 409 Conflict (회원 중복)
- DuplicateMappingException # 409 Conflict (매핑 중복)
```

**매퍼 전략:**
```java
- WebToAppStrategy         # Web DTO → App DTO
- AppToWebStrategy         # App DTO → Web DTO
- AppToCommandStrategy     # App DTO → Command
- DomainToAppStrategy      # Domain → App DTO
- EntityToDomainStrategy   # JPA Entity → Domain
- DomainToEntityStrategy   # Domain → JPA Entity
```

**BaseEntity:**
```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;
}
```

---

## 보안 및 인증

### JWT 인증

**설정:** `config/jwt/`
- `JwtTokenProvider`: JWT 토큰 생성/검증
- `JwtAuthFilter`: JWT 인증 필터
- `AppJwtProperties`: JWT 설정 프로퍼티

**설정값 (application.yml):**
```yaml
app:
  jwt:
    secret: "base64-encoded-secret"
    access-exp-minutes: 60
    issuer: "latinhouse.be"
```

### Spring Security 설정

**파일:** `config/SecurityConfig.java`

**주요 설정:**
- CSRF 비활성화 (Stateless JWT 사용)
- CORS 허용 (모든 Origin, 프로덕션에서 제한 필요)
- Session 정책: STATELESS
- 인증 없이 접근 가능:
  - `/api/v1/signup/**`
  - `/api/v1/signin/**`
  - `/api/v1/home/**`
  - `/swagger-ui/**`, `/v3/api-docs/**`, `/h2-console/**`
- 그 외 모든 요청은 인증 필요

### 권한 체크

**Role Enum:**
```java
public enum Role {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_INSTRUCTOR
}
```

**컨트롤러 메서드 레벨:**
```java
@PreAuthorize("isAuthenticated()")
public ResponseEntity<...> someMethod() { ... }

@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<...> adminOnlyMethod() { ... }
```

**비즈니스 로직 레벨:**
```java
if(!todo.getCreatedBy().equals(userEmail) && !isAdmin)
    throw new ForbiddenException("권한이 없습니다.");
```

---

## 도메인 모델

### User
- **필드:** email (PK), password, sex, role, profileId
- **역할:** 사용자 계정 정보
- **관계:** Profile (1:1, optional)

### Profile
- **필드:** id (PK), name, sex, phoneNumber, isAssigned
- **역할:** 댄서/강사 프로필
- **관계:** User (1:1, optional)
- **비즈니스 규칙:** 한 프로필은 최대 1명의 유저에게만 할당 가능 (중복 체크)

### Todo
- **필드:** no (PK), content, done, createdBy
- **역할:** 할일 관리
- **비즈니스 규칙:** 작성자 본인 또는 ADMIN만 완료 처리 가능

---

## 개발 규칙

### 네이밍 컨벤션

**클래스:**
- Controller: `ApiV1{Domain}Controller`
- UseCase 구현: `{Domain}UseCaseImpl`
- Port: `{Create|Read|Update|Delete}{Domain}Port`
- Adapter: `{Domain}PersistenceAdapter`
- JPA Entity: `{Domain}JpaEntity`
- DTO: `{Operation}{Domain}{Web|App}{Request|Response}`
- Mapper: `{Domain}{Web|App|Persistence}Mapper`
- Strategy: `{Operation}{Domain}{Web|App}Strategy`
- Service: `{Domain}Service`
- Command: `{Operation}{Domain}Command`

**메서드:**
- 생성: `add{Domain}`, `create`
- 조회: `get{Domain}`, `get{Domains}` (복수형)
- 수정: `update`, `modify`
- 삭제: `delete`, `remove`
- 비즈니스 로직: 도메인 용어 사용 (예: `done()`, `assign()`)

### 패키지 구조

```
{domain}/
├── {Domain}.java              # 도메인 모델
├── {Enum}.java                # 도메인 Enum
├── command/
│   └── {Operation}{Domain}Command.java
└── service/
    └── {Domain}Service.java
```

### DTO 변환 흐름

```
Web Request (JSON)
  → Web DTO (Validation)
    → App DTO
      → Domain Command
        → Domain Model
          → JPA Entity
            → Database
```

### 예외 처리

**발생:**
```java
throw new NotFoundException("Todo not found");
throw new ForbiddenException("권한이 없습니다.");
throw new DuplicateMemberException("이미 존재하는 회원입니다.");
```

**처리:**
- `common/exception/ExceptionHandler` 에서 전역 처리
- `ErrorResponse` DTO로 일관된 에러 응답 반환

---

## 개발 워크플로우

### 새로운 기능 추가 시

1. **도메인 모델 정의** (`domain/{domain}/`)
   - 도메인 엔티티 생성
   - 필요시 Enum, Value Object 추가
   - Command 객체 정의

2. **Port 인터페이스 정의** (`port/`)
   - Inbound Port (UseCase 인터페이스) 정의
   - Outbound Port (Repository 인터페이스) 정의
   - App DTO 정의

3. **Outbound Adapter 구현** (`adapter/out/persistence/`)
   - JPA Entity 생성 (BaseEntity 상속)
   - Repository 인터페이스 생성
   - Persistence Mapper 구현
   - PersistenceAdapter 구현 (Outbound Port 구현)

4. **Domain Service 구현** (`domain/{domain}/service/`)
   - 비즈니스 로직 구현
   - Outbound Port 의존성 주입

5. **Application UseCase 구현** (`application/{domain}/`)
   - UseCaseImpl 생성
   - App Mapper 구현
   - 트랜잭션 경계 설정

6. **Inbound Adapter 구현** (`adapter/in/web/`)
   - Controller 생성
   - Web DTO 정의 (Validation 포함)
   - Web Mapper 구현
   - Swagger 문서화

7. **보안 설정** (`config/SecurityConfig.java`)
   - 필요시 엔드포인트 접근 권한 설정
   - `@PreAuthorize` 추가

### 테스트

- 테스트 코드는 `src/test/java/` 에 작성
- 통합 테스트 시 H2 인메모리 DB 사용
- `@SpringBootTest` 활용

---

## 빌드 및 실행

### 빌드
```bash
./gradlew build
```

### 실행
```bash
./gradlew bootRun
```

### 테스트
```bash
./gradlew test
```

---

## API 문서

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

---

## 데이터베이스

### H2 Console
- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (비어있음)

### JPA 설정
```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: create       # 개발 환경 (매번 스키마 재생성)
    properties:
      hibernate:
        show-sql: true       # SQL 쿼리 로그 출력
        format_sql: true     # SQL 포맷팅
        use_sql_comments: true
```

### Auditing
- `@EnableJpaAuditing` 활성화
- BaseEntity 상속 시 자동으로 `createdDate`, `modifiedDate`, `createdBy`, `modifiedBy` 추적

---

## 주의사항

### 보안
- JWT Secret은 환경 변수 또는 외부 설정 파일로 관리 권장
- CORS 설정은 프로덕션 환경에서 특정 도메인만 허용해야 함
- H2 Console은 개발 환경에서만 활성화

### 성능
- N+1 문제 주의 (Lazy Loading 사용 시)
- 필요시 `@EntityGraph` 또는 Fetch Join 사용

### 코드 품질
- Lombok 사용하여 보일러플레이트 코드 최소화
- 매퍼 전략 패턴 사용하여 DTO 변환 로직 분리
- 도메인 로직을 도메인 계층에 집중

---

## 최근 변경 사항

최근 커밋 로그:
- `assignProfile` 로직 순서 수정
- Instructor 등록
- 중복 체크 오류 수정
- 프로필 할당 시 중복체크
- 프로필 → 유저에 할당

**주요 변경:**
- Profile 할당 시 중복 체크 로직 강화
- Instructor Role 추가
- Profile-User 매핑 로직 개선

---

## 추가 참고사항

### 매퍼 패턴
- 각 계층 간 DTO 변환을 위해 **Strategy Pattern** 사용
- Mapper 인터페이스는 여러 Strategy를 조합하여 사용
- 새로운 DTO 변환이 필요할 때마다 Strategy 추가

### 커스텀 어노테이션
- `@SelfValidating`: DTO 유효성 검증 자동화

### 유틸리티
- `RandomUtils`: 랜덤 값 생성 (예: Profile ID)

---

## 문의 및 기여

프로젝트에 대한 문의사항이나 개선 사항이 있다면 팀과 논의해주세요.
