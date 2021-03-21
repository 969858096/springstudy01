package com.lsp.springstudy01.SpringIOC.组件注册;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @FileName: CustomImportSelector
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/11/22 13:01
 */
public class CustomImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.lsp.springstudy01.bean.Cat"};
    }
}
