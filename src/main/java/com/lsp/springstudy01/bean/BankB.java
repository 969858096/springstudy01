package com.lsp.springstudy01.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @FileName: BankA
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/18 22:21
 */
@Component
@Data
public class BankB {

    private String userId;
    private Long money;
}
