/**
 *
 */
package com.assqr.twtr.enums;

import java.util.Calendar;

/**
 * 曜日.
 *
 * @author asSqr
 */
public enum DayOfWeek implements CodePropertyKeyEnum<DayOfWeek> {

    /** 日曜日. */
    SUNDAY(String.valueOf(Calendar.SUNDAY), "DayOfWeek.SUNDAY", "DayOfWeek.short.SUNDAY"),
    /** 月曜日. */
    MONDAY(String.valueOf(Calendar.MONDAY), "DayOfWeek.MONDAY", "DayOfWeek.short.MONDAY"),
    /** 火曜日. */
    TUESDAY(String.valueOf(Calendar.TUESDAY), "DayOfWeek.TUESDAY", "DayOfWeek.short.TUESDAY"),
    /** 水曜日. */
    WEDNESDAY(String.valueOf(Calendar.WEDNESDAY), "DayOfWeek.WEDNESDAY", "DayOfWeek.short.WEDNESDAY"),
    /** 木曜日. */
    THURSDAY(String.valueOf(Calendar.THURSDAY), "DayOfWeek.THURSDAY", "DayOfWeek.short.THURSDAY"),
    /** 金曜日. */
    FRIDAY(String.valueOf(Calendar.FRIDAY), "DayOfWeek.FRIDAY", "DayOfWeek.short.FRIDAY"),
    /** 土曜日. */
    SATURDAY(String.valueOf(Calendar.SATURDAY), "DayOfWeek.SATURDAY", "DayOfWeek.short.SATURDAY"),
    /** 祝日. */
    HOLIDAY("9", "DayOfWeek.HOLIDAY", "DayOfWeek.short.HOLIDAY");

    /** コード値. */
    private String code;

    /** プロパティキー. */
    private String propKey;

    /** プロパティキー. */
    private String propKeyShort;

    /**
     * コンストラクタ.
     *
     * @param code
     *            コード値
     * @param propKey
     *            プロパティキー
     * @param propKeyShort
     *            プロパティキー（略称用）
     */
    private DayOfWeek(String code, String propKey, String propKeyShort) {

        this.code = code;
        this.propKey = propKey;
        this.propKeyShort = propKeyShort;
    }

    /**
     * コード値を取得する.
     *
     * @return コード値
     */
    public String getCode() {

        return code;
    }

    /**
     * プロパティキーを取得する.
     *
     * @return プロパティキー
     */
    public String getPropKey() {

        return propKey;
    }

    /**
     * プロパティキー（略称用）を取得する.
     *
     * @return プロパティキー（略称用）
     */
    public String getPropKeyShort() {

        return propKeyShort;
    }

    /**
     * 指定されたコード値の曜日を取得する.
     *
     * @param code
     *            コード値
     * @return 曜日
     */
    public static DayOfWeek getByCode(String code) {

        return CodePropertyKeyEnum.getByCode(DayOfWeek.class, code);
    }
}
