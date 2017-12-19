package org.soho.common.exception;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by zhuozl on 17-4-24.
 */
public class BaseException extends RuntimeException{

        private static final long serialVersionUID = 1L;
        /**
         * 异常编码
         */
        private String exceptionCode;
        /**
         * 异常堆栈信息
         */
        private String stackTraceMessage;

        /**
         * 构造（仅用于序列化）
         */
        public BaseException() {
            this(StringUtils.EMPTY, StringUtils.EMPTY, null);
        }

        /**
         * 构造
         *
         * @param message 系统内部异常描述信息
         */
        public BaseException(String message) {
            this("10000", message, null);
        }

        /**
         * 构造
         *
         * @param expCode 异常编码
         * @param message 系统内部异常描述信息
         */
        public BaseException(String expCode, String message) {
            this(expCode, message, null);
        }

        /**
         * 构造
         *
         * @param expCode 异常编码
         * @param message 系统内部异常描述信息
         * @param cause   异常对象
         */
        public BaseException(String expCode, String message, Throwable cause) {
            super(message == null ? StringUtils.EMPTY : message, cause);
            this.exceptionCode = expCode;
        }


        /**
         * 获取异常编码
         *
         * @return 异常编码
         */
        public String getExceptionCode() {
            return exceptionCode;
        }

        /**
         * 设置异常编码
         *
         * @param exceptionCode 异常编码
         */
        public void setExceptionCode(String exceptionCode) {
            this.exceptionCode = exceptionCode;
        }

        public String getStackTraceMessage() {
            Writer w = new StringWriter();
            this.printStackTrace(new PrintWriter(w));
            stackTraceMessage = w.toString();
            return stackTraceMessage;
        }

        public void setStackTraceMessage(String stackTraceMessage) {
            this.stackTraceMessage = stackTraceMessage;
        }

    }
