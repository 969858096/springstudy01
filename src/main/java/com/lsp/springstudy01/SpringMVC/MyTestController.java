package com.lsp.springstudy01.SpringMVC;

/**
 * @FileName: MyController
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/24 09:42
 */
@MyController
@MyRequestMapping("lsp")
public class MyTestController {

    @MyRequestMapping("test")
    public String test(){
        System.out.println("手写springmvc");
        return "index";
    }
}
