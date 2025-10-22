package com.latinhouse.backend.util;

import java.security.SecureRandom;

public class RandomUtils {
    // 헷갈릴 수 있는 문자(i, l, 1, o, 0) 제거
    private static final char[] SAFE_ALPHANUM = "abcdefghjkmnpqrstuvwxyz23456789".toCharArray();

    private static final SecureRandom RNG = new SecureRandom();

    private RandomUtils() {
        // 인스턴스화 방지
    }

    /**
     * 영어 소문자 + 숫자 (헷갈리는 문자 제외) 8자리 난수 생성
     */
    public static String generateRandomId() {
        return generateRandomId(8);
    }

    /**
     * 영어 소문자 + 숫자 (헷갈리는 문자 제외) 지정 길이 난수 생성
     */
    public static String generateRandomId(int length) {
        char[] buf = new char[length];
        for (int i = 0; i < length; i++) {
            buf[i] = SAFE_ALPHANUM[RNG.nextInt(SAFE_ALPHANUM.length)];
        }
        return new String(buf);
    }
}