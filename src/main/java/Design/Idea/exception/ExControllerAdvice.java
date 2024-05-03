/*
 * 클래스 기능 : 예외 발생했을 때 응답을 처리하는 클래스
 * 최근 수정 일자 : 2024.03.18(금)
 */
package Design.Idea.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResult>> handleValidException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        BindingResult bindingResult = e.getBindingResult();

        List<ErrorResult> response = new ArrayList<>();
        for (FieldError err : bindingResult.getFieldErrors()) {
            response.add(new ErrorResult(errorCode.getCode(), err.getField(), err.getDefaultMessage()));
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<List<ErrorResult>> handleCustomException(CustomException e) {
        ErrorCode errorCode = ErrorCode.ROOM_EXCEEDED;
        List<ErrorResult> response = new ArrayList<>();
        response.add(new ErrorResult(errorCode.getCode(), null, e.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

/*    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResult> testing(NullPointerException e){
        ErrorResult errorResult=new ErrorResult("EMAIL",e.getMessage());
        return new ResponseEntity<>(errorResult,HttpStatus.BAD_REQUEST);
    }*/
}
