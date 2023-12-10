# Versions :
```
        springboot : 3.2.0
        jdk : 17
        maven: 4.0.0
```

# Input validation

Dependencie :

```
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
```

How to use :

```
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "username is required")
    private String name;
    @NotNull(message = "email is mandatory")
    @Email(message = "invalid email address")
    private String email;
    @NotNull(message = "mobile number is required")
    private String mobile;
    @NotNull(message = "gender is required")
    private String gender;
    @Max(60)@Min(18)
    private int age;
    @NotBlank
    private String nationality;
}
```

# Exception Handling

## 1. Create Generic Response Error class

```
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;

    public ErrorResponse(String message) {this.message = message;}
}
```
## 2. Create error enum 
```
@AllArgsConstructor
@Getter
public enum ErrorEnum {
    USER_NOT_FOUND("User not found"),
    EMAIL_EXIST("Email already exist");
    private final String message;

}
```

## 3. Create Custom Exception class

```
@NoArgsConstructor
public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException(ErrorEnum errorEnum) {super(errorEnum.getMessage());}
}
```

## 4. Create Global Exception Handler Class

```
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handlerException(EmailAlreadyExistException ex){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    //return error if one of the inputs not valid

    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

}
```

## Throw exception :

```
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userDao;
    private final ModelMapper mapper;

    public User addUser(UserDto userDto){
        userDao.findByEmail(userDto.getEmail()).ifPresent(existingUser -> {
            throw new EmailAlreadyExistException(ErrorEnum.EMAIL_EXIST);
        });
        return userDao.save(mapper.map(userDto, User.class));
    }
}
```


