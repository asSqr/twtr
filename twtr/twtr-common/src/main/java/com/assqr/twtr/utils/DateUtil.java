package com.assqr.twtr.utils;

import com.assqr.twtr.constants.LiteralValueConstants;
import com.assqr.twtr.enums.DayOfWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.lang.System.currentTimeMillis;

/**
 * 日付操作 Util.
 *
 * @author asSqr
 */
public class DateUtil {

    /** ロガー. */
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /** デフォルトタイムゾーン. */
    public static TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Tokyo");

    /** フォーマット: ISO-8601. */
    public static final String ISO8601 = "yyyy-MM-dd'T'HH:mm:ssX";

    /** フォーマット:年月日スラッシュ区切り. */
    public static final String DT_FMT_YMD_S = "yyyy/MM/dd";
    /** フォーマット:年月日ハイフン区切り. */
    public static final String DT_FMT_YMD_H = "yyyy-MM-dd";
    /** フォーマット:年月日区切りなし. */
    public static final String DT_FMT_YMD_N = "yyyyMMdd";

    /** フォーマット:日月年スラッシュ区切り. */
    public static final String DT_FMT_DMY_S = "dd/MM/yyyy";
    /** フォーマット:日月年ハイフン区切り. */
    public static final String DT_FMT_DMY_H = "dd-MM-yyyy";

    /** フォーマット:月日年スラッシュ区切り. */
    public static final String DT_FMT_MDY_S = "MM/dd/yyyy";
    /** フォーマット:月日年ハイフン区切り. */
    public static final String DT_FMT_MDY_H = "MM-dd-yyyy";

    /** フォーマット:年月スラッシュ区切り. */
    public static final String DT_FMT_YM_S = "yyyy/MM";
    /** フォーマット:年月ハイフン区切り. */
    public static final String DT_FMT_YM_H = "yyyy-MM";

    /** フォーマット:月年スラッシュ区切り. */
    public static final String DT_FMT_MY_S = "MM/yyyy";
    /** フォーマット:月年ハイフン区切り. */
    public static final String DT_FMT_MY_H = "MM-yyyy";

    /** フォーマット:月日スラッシュ区切り. */
    public static final String DT_FMT_MD_S = "MM/dd";
    /** フォーマット:月日ハイフン区切り. */
    public static final String DT_FMT_MD_H = "MM-dd";

    /** フォーマット:日月スラッシュ区切り. */
    public static final String DT_FMT_DM_S = "dd/MM";
    /** フォーマット:日月ハイフン区切り. */
    public static final String DT_FMT_DM_H = "dd-MM";

    /** フォーマット:日. */
    public static final String DT_FMT_D = "d";

    /** フォーマット:時. */
    public static final String TM_FMT_H = "HH";
    /** フォーマット:時分. */
    public static final String TM_FMT_HM = "HH:mm";
    /** フォーマット:時分秒. */
    public static final String TM_FMT_HMS = TM_FMT_HM + ":ss";
    /** フォーマット:時分秒ミリ秒. */
    public static final String TM_FMT_HMS_DETAIL = TM_FMT_HMS + ".SSS";

    /** フォーマット:年月日時分フォーマット. */
    public static final String DTTM_FMT_YMDHM_S = DT_FMT_YMD_S + LiteralValueConstants.SPACE + TM_FMT_HM;
    /** フォーマット:年月日時分秒フォーマット. */
    public static final String DTTM_FMT_YMDHMS_S = DT_FMT_YMD_S + LiteralValueConstants.SPACE + TM_FMT_HMS;

    /** フォーマット:年月日時フォーマット区切りなし. */
    public static final String DTTM_FMT_YMDH_N = DT_FMT_YMD_N + TM_FMT_H;

    /** 日付変更時刻書式正規表現. */
    private static final String DATELINE_REGEX = "[0-9]{2}:[0-9]{2}";

    /** テスト用日付設定キー. */
    private static final String SYS_PROP_KEY_TEST_TIME_MILLIS = "testTimeMillis";

    /**
     * 現在時刻取得系
     *
     */

    /**
     * 現在時刻の取得．
     *
     * @return 現在時刻
     */
    public static final Date now() {
        return new Date(currentTimeMillis());
    }

    /**
     * 現在自国のエポックミリ秒の取得を行う. テスト用の設定が存在する場合は，その設定に従う.
     *
     * @return 現在時刻
     */
    public static final Long currentTimeMillis() {
        String testTimeMillis = System.getProperty(SYS_PROP_KEY_TEST_TIME_MILLIS);

        if (!StringUtils.hasLength(testTimeMillis)) {
            return Long.valueOf(testTimeMillis);
        }

        return Long.valueOf(System.currentTimeMillis());
    }

    /**
     * 現在時刻を Timestamp 型で返す.
     *
     * @return タイムスタンプオブジェクト
     */
    public static Timestamp getTimestampNow() {
        return getTimestamp(now());
    }

    /**
     * 指定された Date 型を Timestamp 型で返す.
     * @param date 日付
     * @return タイムスタンプオブジェクト
     */
    public static Timestamp getTimestamp(Date date) {
        if (date == null) {
            return null;
        }

        return new Timestamp(date.getTime());
    }

    /**
     * 日付文字列変換
     *
     */

    /**
     * 指定されたフォーマットで日付文字列を取得する.
     *
     * @param date 日付
     * @param pattern 日付書式
     * @param tz タイムゾーン
     * @return 日付文字列
     */
    public static String formatDate(Date date, String pattern, TimeZone tz) {
        if (date == null) {
            return null;
        }

        if (!StringUtils.hasLength(pattern)) {
            throw new IllegalArgumentException("pattern = " + pattern);
        }

        DateFormat fmt = new SimpleDateFormat(pattern);

        if (tz != null) {
            fmt.setTimeZone(tz);
        }

        return fmt.format(date);
    }

    /**
     * 文字列の日付の書式を別の書式に変換する.
     *
     * @param date 日付文字列
     * @param befFmt 変更前書式
     * @param aftFmt 変更後書式
     * @param tz タイムゾーン
     * @return 書式変換された日付文字列
     */
    public static String changeFormat(String date, String befFmt, String aftFmt, TimeZone tz) {
        if (date == null) {
            return null;
        }

        if (!StringUtils.hasLength(date)
                || !StringUtils.hasLength(befFmt)
                || !StringUtils.hasLength(aftFmt)) {
            throw new IllegalArgumentException("date=" + date + ", befFmt = " + befFmt + ", aftFmt = " + aftFmt);
        }

        if (!isParsable(date, befFmt, tz)) {
            throw new IllegalArgumentException("date = " + date + ", befFmt = " + befFmt);
        }

        Date dateVal = parseDate(date, befFmt, tz);

        return formatDate(dateVal, aftFmt, tz);
    }

    /**
     * ミリ秒を分に変換する.
     *
     * @param milliseconds ミリ秒
     * @return 分
     */
    public static BigDecimal milliseconds2Minutes(Long milliseconds) {
        if (milliseconds == null) {
            return null;
        }

        BigDecimal ms = new BigDecimal(milliseconds.longValue(), MathContext.DECIMAL64);

        return ms.movePointLeft(3).divide(new BigDecimal(60), MathContext.DECIMAL64);
    }

    /**
     * ミリ秒を時に変換する.
     *
     * @param milliseconds ミリ秒
     * @return 時
     */
    public static BigDecimal milliseconds2Hours(Long milliseconds) {
        if (milliseconds == null) {
            return null;
        }

        BigDecimal ms = new BigDecimal(milliseconds.longValue(), MathContext.DECIMAL64);

        return ms.movePointLeft(3).divide(new BigDecimal(60 * 60), MathContext.DECIMAL64);
    }

    /**
     * ミリ秒を ##0:00:00 形式表示に変換する.
     *
     * @param milliseconds ミリ秒
     * @param hourFormat 時フォーマット
     * @return ##0:00:00 形式表示
     */
    public static String convertMs2HhhhMsSs(long milliseconds, NumberFormat hourFormat) {

        NumberFormat fmt = hourFormat;
        if (fmt == null) {
            fmt = new DecimalFormat("###0");
        }

        BigDecimal stopTime = new BigDecimal(milliseconds, MathContext.DECIMAL64);
        BigDecimal stopHours = stopTime.movePointLeft(3).divide(new BigDecimal(3600), MathContext.DECIMAL64).setScale(0, RoundingMode.DOWN);
        BigDecimal stopMinutes = stopTime.movePointLeft(3).subtract(stopHours.multiply(new BigDecimal(3600), MathContext.DECIMAL64))
                .divide(new BigDecimal(60), MathContext.DECIMAL64).setScale(0, RoundingMode.DOWN);
        BigDecimal stopSeconds = stopTime.movePointLeft(3)
                .subtract(stopHours.multiply(new BigDecimal(3600), MathContext.DECIMAL64).add(stopMinutes.multiply(new BigDecimal(60), MathContext.DECIMAL64)))
                .setScale(0, RoundingMode.DOWN);

        StringBuilder sb = new StringBuilder();
        sb.append(hourFormat.format(stopHours));
        sb.append(LiteralValueConstants.COLON);
        sb.append(String.format("%02d", Integer.valueOf(stopMinutes.intValue())));
        sb.append(LiteralValueConstants.COLON);
        sb.append(String.format("%02d", Integer.valueOf(stopSeconds.intValue())));

        return sb.toString();
    }

    /**
     * 文字列日付変換
     *
     */

    /**
     * 指定されたフォーマットで日付オブジェクトを取得する.
     *
     * @param date 日付文字列
     * @param pattern 日付書式
     * @param tz タイムゾーン
     * @return 日付オブジェクト
     * @throws ParseException 日付変換に失敗
     */
    public static Date parseDate(String date, String pattern, TimeZone tz) {
        if (!StringUtils.hasLength(date)
            || !StringUtils.hasLength(pattern)) {
            throw new IllegalArgumentException("date = " + date + ", pattern = " + pattern);
        }

        DateFormat fmt = new SimpleDateFormat(pattern);

        if (tz != null) {
            fmt.setTimeZone(tz);
        }

        try {
            return fmt.parse(date);
        } catch(ParseException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("parseDate の fmt.parse 失敗");
                logger.debug("例外詳細: ", e);
            }

            throw new RuntimeException(e);
        }
    }

    public static boolean isParsable(String date, String pattern, TimeZone tz) {
        try {
            parseDate(date, pattern, tz);
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 日付計算
     *
     */

    /**
     * 指定された日付オブジェクトに指定された日数を足す.
     *
     * @param date 日付オブジェクト
     * @param delta 足す日数
     * @return 計算結果
     */
    public static Date addDate(Date date, int delta) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, delta);

        return cal.getTime();
    }

    /**
     * 指定された日付オブジェクトに指定された時間を足す.
     *
     * @param date 日付オブジェクト
     * @param delta 足す時間
     * @return 計算結果
     */
    public static Date addHour(Date date, int delta) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, delta);

        return cal.getTime();
    }

    /**
     * 指定された日付オブジェクトに指定された月数を足す.
     *
     * @param date 日付オブジェクト
     * @param delta 足す月数
     * @return 計算結果
     */
    public static Date addMonth(Date date, int delta) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, delta);

        return cal.getTime();
    }

    /**
     * 指定された日付文字列から曜日を取得する
     *
     * @param dayString 日付文字列
     * @param pattern 日付書式
     * @param tz タイムゾーン
     * @return 曜日
     */
    public static DayOfWeek getDayOfWeek(String dayString, String pattern, TimeZone tz) {
        Date date = parseDate(dayString, pattern, tz);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        return DayOfWeek.getByCode(String.valueOf(dayOfWeek));
    }
}
