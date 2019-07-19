package com.lyb;

import com.lyb.core.TieBaImageBuilder;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/18 14:32
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        TieBaImageBuilder.custom()
                //帖子地址
                .url("https://tieba.baidu.com/p/6170218305")
                //下载页数
                .countPage(1)
                .build();
    }
}
