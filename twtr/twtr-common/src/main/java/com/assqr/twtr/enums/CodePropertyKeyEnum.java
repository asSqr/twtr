package com.assqr.twtr.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * コード値・名称プロパティーキーにて表現される Enum のインターフェース
 *
 * @author asSqr
 * @param <E>
 *     実装 Enum
 */
public interface CodePropertyKeyEnum<E extends Enum<E>> {

    /**
     * コード値を取得する．
     *
     * @return コード値
     */
    public String getCode();

    /**
     * プロパティーキーを取得する．
     *
     * @return プロパティーキー
     */
    public String getPropKey();

    /**
     * Enum として自身を返す．
     *
     * @return 自 Enum
     */
    @SuppressWarnings("unchecked")
    default E toEnum() {
        return (E) this;
    }

    /**
     * 対象の Enum において指定されたコードの Enum を返す．
     *
     * @param clazz このインターフェースを実装する Enum クラス
     * @param code コード値
     * @return コード値が一致する Enum
     * @param <E>
     */
    public static <E extends Enum<E>> E getByCode(Class<? extends CodePropertyKeyEnum<E>> clazz, String code) {
        if (clazz == null || code == null) {
            return null;
        }

        for (CodePropertyKeyEnum<E> e : clazz.getEnumConstants()) {
            if (code.equals(e.getCode())) {
                return e.toEnum();
            }
        }

        return null;
    }

    public static <E extends Enum<E>> List<String> getCodeList(Class<? extends CodePropertyKeyEnum<E>> clazz) {
        if (clazz == null) {
            return null;
        }

        List<String> rtnList = new ArrayList<>();

        for (CodePropertyKeyEnum<E> e : clazz.getEnumConstants()) {
            rtnList.add(e.getCode());
        }

        return rtnList;
    }
}
