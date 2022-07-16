package com.assqr.twtr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * Bean 操作に関する Util クラス.
 */
public class BeanUtil {

    /**
     * ロガー.
     */
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 指定されたビーンの内容をコピーする.
     *
     * @param dest コピー先
     * @param src コピー元
     * @param <T> 対象 Bean
     */
    public static <T> void copyBean(T dest, T src) {
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (Exception e) {
            logger.warn("プロパティのコピーに失敗しました．", e);
        }
    }

}
