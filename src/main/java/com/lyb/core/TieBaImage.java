package com.lyb.core;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/18 16:24
 * @Description:
 */
public interface TieBaImage {

    TieBaImage url(String url);

    TieBaImage countPage(Integer countPage);

    void build();
}
