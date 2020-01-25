package com.hzp.demo.config;

import com.hzp.demo.exception.*;
import com.hzp.demo.utils.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Http请求时，参数异常
     *
     * @param
     * @param e
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity handlerRequstParams(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.error("方法参数类型绑定异常", e);
        return new ResponseEntity(SystemErrors.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION);
    }

    /**
     * Http请求时，参数异常
     *
     * @param
     * @param e
     */
    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public ResponseEntity ServletException(ServletException e, HttpServletRequest request) {
        log.error("请求处理异常", e);
        return new ResponseEntity(SystemErrors.SERVLET_EXCEPTION);
    }

    /**
     * 服务器内部出错
     *
     * @param
     * @param e
     */
    @ExceptionHandler(SysException.class)
    @ResponseBody
    public ResponseEntity SysException(SysException e, HttpServletRequest request) {
        log.error("自定义系统异常", e);
        return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
    }

    /**
     * 请求超时
     *
     * @param
     * @param e
     */
    @ExceptionHandler(RequestTimeOutException.class)
    @ResponseBody
    public ResponseEntity RequestTimeOutException(RequestTimeOutException e, HttpServletRequest request) {
        log.error("请求超时" + e.getError().getMessage() + (e.getData() == null ? "" : (",data=" + e.getData())), e);
        return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
    }

    /**
     * 参数出错
     *
     * @param
     * @param e
     */
    @ExceptionHandler(ParamsException.class)
    @ResponseBody
    public ResponseEntity ParamsException(ParamsException e, HttpServletRequest request) {
        log.error("参数错误", e);
        return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
    }


    /**
     * 基础异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity BaseException(HttpServletRequest request, BaseException e) {
        log.error("(⊙o⊙)…呃豁，报错了", e);
        return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
    }

    /**
     * 统一未知异常处理
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleGlobal(HttpServletRequest request, Exception e) {
        log.error("(⊙o⊙)…呃豁，报错了", e);
        return new ResponseEntity(SystemErrors.SYSTEM_ERROR);
    }

    /**
     * 请求参数格式异常处理
     *
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity MethodArgumentNotValidHandler(MethodArgumentNotValidException exception, HttpServletRequest request) throws Exception {
        BindingResult result = exception.getBindingResult();
        log.error("请求参数格式异常", exception);
        return bindParamValid(result);
    }

    /**
     * 请求参数格式异常处理
     *
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseEntity BindException(BindException exception, HttpServletRequest request) throws Exception {
        BindingResult result = exception.getBindingResult();
        log.error("请求参数绑定异常", exception);
        return bindParamValid(result);
    }

    /**
     * 数据绑定参数校验
     *
     * @param result
     * @return
     */
    private ResponseEntity bindParamValid(BindingResult result) {
        StringBuffer sb = new StringBuffer();
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                String errorMsg = error.getDefaultMessage();
                sb.append(error.getField() + "-" + errorMsg);
                sb.append(",");
            }
        }
        String errMsg = sb.deleteCharAt(sb.length() - 1).toString();
        return new ResponseEntity(SystemErrors.REQUEST_PARAM_ERROR.code, errMsg);
    }

}
