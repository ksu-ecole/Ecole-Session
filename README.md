# Ecole Session
경성대학교 에꼴프로젝트 강의에서 Spring Session 로그인을 구현한 레포지토리 입니다.

<br>

## Folder structure 

```
com
    └── ksu
        └── ecole_session
            ├── EcoleSessionApplication.java
            ├── account
            │   ├── Account.java
            │   ├── AccountController.java
            │   ├── AccountRepository.java
            │   ├── AccountService.java
            └── config
                └── SecurityConfig.java
```

<br>

## 실행방법

1. `IntelliJ` 를 이용한 실행
   1. `$ git clone https://github.com/ksu-ecole/Ecole-Session.git`
   2. `IntelliJ` 실행
   3. `File` → `open` -> (프로젝트가 저장된 디렉토리 열기)
   4. `Run` → `EcoleSessionApplication.java`
   
2. `Gradle`을 이용한 실행
   1. `$ git clone https://github.com/ksu-ecole/Ecole-Session.git`
   2. `$ cd Ecole-Session`
   3. `$ ./gradlew test`
   4. `$ ./gradlew build`
   5. `$ ./gradlew bootrun`

<br>

## 10/30 강의

<details>
<summary>수정 사항</summary>
<br>

1. `index.html` -> `test.html` 로 이름 변경

2. `AccountController.java` 수정
    ```
    // 이전 코드

    @GetMapping("/index")
        public String loadIndex(Model model, @AuthenticationPrincipal Account currentAccount)   {
            model.addAttribute("email", currentAccount.getEmail());

            return "index";
        }
    ```

    ```
    // 수정 후 코드

    @GetMapping("/test")
        public String loadIndex(Model model, @AuthenticationPrincipal Account currentAccount)   {
            model.addAttribute("email", currentAccount.getEmail());

            return "test";
        }
    ```

3. `SecurityConfig.java` 수정

    ```
    @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("!/h2-console/**"))
                .and()
                    .headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
                    .frameOptions().disable()
                .and()
                    .authorizeRequests().antMatchers("/sign-up", "/sign-in", "/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/sign-in")
                    .defaultSuccessUrl("/test", true)
            ;
        }
    ```