package com.example.springmemoreview.common.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CheckUserRoleAop {
    @Pointcut("execution(* com.example.springmemoreview.memo.service.MemoService.updateMemo(..))")
    private void updateMemo() {
    }
}
