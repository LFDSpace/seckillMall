package com.work.seckill.base.exception;

import com.work.seckill.base.result.CodeMsg;

public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }
    public CodeMsg getCodeMsg(){
        return codeMsg;
    }
}
