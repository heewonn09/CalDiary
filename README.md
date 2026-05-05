# CalDiary 코드 심층 분석

## 1) 프로젝트 개요
CalDiary는 **안드로이드(Java) 단일 앱**으로, 건강 관리(식단 기록, BMI 계산, 음식 영양정보 조회, 질환 정보/추천 음식 제공)를 목표로 만든 구조입니다. 서버를 별도로 두지 않고, 앱 내부 저장소와 SQLite를 이용해 데이터를 처리합니다.

- 플랫폼: Android (minSdk 26, targetSdk 34)
- 언어: Java
- 빌드: Gradle Kotlin DSL
- 핵심 기능:
  - 회원가입/로그인(SQLite)
  - 날짜별 식단 다이어리(내부 파일 저장)
  - BMI 계산
  - 공공데이터 API 기반 음식 영양 조회(XML 파싱)
  - 질환 정보 링크 및 추천 음식 갤러리

---

## 2) 전체 아키텍처(Front/Back 분리 관점)

이 프로젝트는 전통적인 웹의 FE/BE처럼 물리적으로 분리되지 않았지만, 코드 책임을 기준으로 아래처럼 나눌 수 있습니다.

## 프런트엔드(화면/UI/사용자 상호작용)
- **Activity 중심 UI**
  - `MainActivity`: 로그인 화면
  - `Join`: 회원가입 화면
  - `Dia`: 메인 허브 + 날짜별 다이어리
  - `Bmi`, `Cal`, `Food`, `Info`: 각 기능 화면
  - `Ob`, `HBP`, `HL`, `FL`: 질환별 추천 음식 갤러리
- **리소스(XML) 중심 뷰 구성**
  - `app/src/main/res/layout/*.xml`
- **화면 이동 방식**
  - `Intent`로 Activity 간 직접 전환
- **네비게이션 패턴**
  - 다수 화면에 공통 하단 버튼(`btnBMI`, `btnCal`, `btnDia`, `btnFood`, `btnInfo`, `btnLogOut`)을 반복 배치하여 직접 이동

## 백엔드(데이터/비즈니스 로직/API)
- **로컬 인증 데이터 저장소(SQLite)**
  - `Join.myDBHelper`에서 `LoginDB` / `JoinInfo` 테이블 생성 및 CRUD 일부 구현
- **로컬 파일 저장소(Internal Storage)**
  - `Dia`에서 날짜별 텍스트 파일(`yyyy_m_d_.txt`) 저장/읽기
- **외부 API 연동**
  - `Cal`에서 식약처 공공 API 호출 후 XML Pull Parser로 데이터 파싱
- **도메인 계산 로직**
  - `Bmi`에서 BMI 계산 및 구간별 메시지 도출

---

## 3) 프런트엔드 상세 설계/구현

### 3-1. 화면 구성 철학
이 앱은 MVVM/Compose 같은 현대 패턴보다, **Activity + XML + Listener** 기반의 고전적인 안드로이드 방식으로 구성되어 있습니다.

- 장점: 학습 난이도가 낮고 구현이 직관적
- 단점: 화면별 중복 코드가 많고 상태 관리 확장이 어렵다

### 3-2. 로그인/회원가입 UX
- `MainActivity`
  - 아이디/비밀번호 입력 후 `JoinInfo` 전체를 순회하며 일치 여부 확인
  - 성공 시 `Dia` 진입
- `Join`
  - 입력값을 `INSERT` 후 다시 로그인 화면으로 이동

### 3-3. 메인 허브(Dia)
- `DatePicker`로 날짜 선택
- 선택 날짜를 파일명으로 변환하여 파일 읽기/쓰기
- 안내성 `AlertDialog` 3종(고객센터, 앱 소개, 기능 소개)
- 다른 기능 화면으로 이동하는 허브 역할

### 3-4. 음식/질환 정보 UI
- `Food`는 `TabActivity`로 4개 탭(비만/고혈압/고지혈증/지방간)을 붙여 각 Activity를 표시
- `Ob`, `HBP`, `HL`, `FL`은 공통 구조:
  - `Gallery + BaseAdapter` 이미지 썸네일
  - 터치 시 큰 이미지/텍스트/토스트 표시
  - 큰 이미지 클릭 시 외부 브라우저 링크 오픈

### 3-5. 정보 화면(Info)
- 질환별 위키/유튜브 검색 링크를 버튼으로 제공
- 외부 정보 탐색 중심

---

## 4) 백엔드 상세 설계/구현

### 4-1. 로컬 DB 인증 구조
`Join` 내부 클래스 `myDBHelper`가 SQLiteOpenHelper 역할을 수행합니다.

- DB: `LoginDB`
- 테이블: `JoinInfo(uId TEXT, uPassword TEXT)`
- 동작:
  - 회원가입: `INSERT INTO JoinInfo VALUES(...)`
  - 로그인: `SELECT * FROM JoinInfo` 후 Java 레벨 비교

#### 설계 특성
- 서버 인증 없이 **디바이스 로컬 인증**
- 교육용/프로토타입에 적합
- 실제 서비스에는 보안/무결성 한계

### 4-2. 다이어리 저장 구조
`Dia`에서 날짜별 파일 분리 저장.

- 파일명: `연_월_일_.txt`
- 저장 API: `openFileOutput(..., MODE_PRIVATE)`
- 조회 API: `openFileInput(...)`

#### 설계 특성
- 간단하고 의존성 없음
- 파일 단위라 검색/통계/동기화가 어려움

### 4-3. 외부 음식 영양 API
`Cal#getXmlData()`에서 네트워크 요청 및 XML 파싱.

- 호출 URL: 식약처 `FoodNtrIrdntInfoService1`
- 입력: 음식명(URLEncoder)
- 출력: 음식명, 1회 제공량, 열량 등 영양값 문자열
- 스레드 처리: `new Thread` + `runOnUiThread`

#### 설계 특성
- 별도 Retrofit/OkHttp 계층 없이 직접 호출
- 빠르게 기능 구현 가능
- 예외처리/재시도/타임아웃/파싱 안정성은 제한적

### 4-4. BMI 계산 로직
`Bmi`에서 신장/체중 입력을 받아:

- BMI = 체중(kg) / (신장(m)^2)
- 구간 메시지
  - <=18.5 저체중
  - 18.5~23 정상
  - 23~25 과체중
  - >25 비만

---

## 5) 화면/기능 흐름(사용자 여정)
1. 앱 시작 → `MainActivity`(로그인)
2. 신규 사용자 → `Join` 회원가입
3. 로그인 성공 → `Dia` 허브
4. 허브에서 다음으로 이동
   - `Bmi`(BMI 측정)
   - `Cal`(음식 검색/영양 조회)
   - `Food`(질환별 추천 음식 탭)
   - `Info`(질환 정보 링크)

---

## 6) 코드 구조 평가 (핵심 포인트)

### 강점
- 기능 분리가 화면 단위로 명확
- Android 기본 컴포넌트 활용 경험이 잘 드러남
- 외부 API 연동, 로컬 DB, 파일 저장 등 실습 범위가 넓음

### 한계
- **중복 코드**: 하단 네비게이션 로직이 여러 Activity에 반복
- **보안 취약**: SQL 문자열 결합, 평문 비밀번호 저장
- **구조 결합도 높음**: `MainActivity extends Join`로 DB 의존을 상속으로 공유
- **구식 컴포넌트**: `TabActivity`, `Gallery`는 현대 Android에서 비권장
- **예외 처리 부족**: catch 블록 비어 있음, 사용자 오류 메시지 제한

---

## 7) 개선 제안 (리팩터링 로드맵)

### 1단계: 안정성/보안
- SQL 파라미터 바인딩(`SQLiteStatement`/`query` 인자화)
- 비밀번호 해시 저장(최소 SHA-256 + salt, 실제는 stronger KDF 권장)
- 입력값 검증(빈 값/숫자 변환 예외)
- 네트워크/파싱 실패 메시지 명확화

### 2단계: 구조 개선
- DB Helper를 별도 클래스로 분리(상속 제거)
- 공통 네비게이션 컴포넌트화
- Repository 계층 도입(인증/다이어리/API)

### 3단계: 현대화
- `TabActivity` → `TabLayout + ViewPager2` 또는 Navigation
- `Gallery` → `RecyclerView`
- Java → Kotlin + ViewBinding(+ 가능하면 MVVM)

### 4단계: 확장성
- Room 도입(로그/다이어리 구조화)
- 검색 히스토리/즐겨찾기
- 오프라인 캐시 + 동기화 전략

---

## 8) 파일 기준 빠른 맵

- 앱 설정/의존성
  - `app/build.gradle.kts`
  - `app/src/main/AndroidManifest.xml`
- 인증
  - `app/src/main/java/bu/ac/kr/caldiary/MainActivity.java`
  - `app/src/main/java/bu/ac/kr/caldiary/Join.java`
- 허브/다이어리
  - `app/src/main/java/bu/ac/kr/caldiary/Dia.java`
- BMI
  - `app/src/main/java/bu/ac/kr/caldiary/Bmi.java`
- 음식 영양 API
  - `app/src/main/java/bu/ac/kr/caldiary/Cal.java`
- 질환 정보
  - `app/src/main/java/bu/ac/kr/caldiary/Info.java`
- 추천 음식 탭/갤러리
  - `app/src/main/java/bu/ac/kr/caldiary/Food.java`
  - `app/src/main/java/bu/ac/kr/caldiary/Ob.java`
  - `app/src/main/java/bu/ac/kr/caldiary/HBP.java`
  - `app/src/main/java/bu/ac/kr/caldiary/HL.java`
  - `app/src/main/java/bu/ac/kr/caldiary/FL.java`

---

## 9) 결론
이 프로젝트는 “건강관리 학습형 안드로이드 앱”으로, 프런트는 Activity 기반 화면 전환과 상호작용, 백엔드는 로컬 DB/파일 저장/API 파싱으로 구성된 **단일 앱형 풀스택 구조**입니다. 즉, 서버 없는 구조로 빠르게 기능을 실험하고 구현한 형태이며, 실제 서비스로 발전시키려면 보안·구조·현대화 리팩터링이 핵심 과제입니다.
