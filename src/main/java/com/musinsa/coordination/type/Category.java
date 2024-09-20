package com.musinsa.coordination.type;

import com.musinsa.coordination.exception.CategoryNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Category {
    TOP("상의"),
    OUTER("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORIES("액세서리"),
    ;
    private final String desc;

    public static Category fromValue(String value) {
        return Arrays.stream(values())
                .filter(p -> p.desc.equals(value))
                .findFirst()
                .orElseThrow(() -> new CategoryNotFoundException("카테고리명을 확인해주세요."));
    }
}
