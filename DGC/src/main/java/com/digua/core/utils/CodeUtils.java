package com.digua.core.utils;

public class CodeUtils {

    public PAYCODE pay;

    public enum PAYCODE {
        ALIPAY_CODE,
        WECHAT_CODE,
        OTHER_CODE
    }

    /**
     * 根据前两位返回来的付款码和长度判断
     * <p>
     * 支付宝用户付款码，25-30 开头的长度为 16-24 位的数字，实际字符串长度以开发者获取的付款码长度为准；付款码使用一次即失效
     * <p>
     * 微信用户付款码条形码规则：18位纯数字，以10、11、12、13、14、15开头
     */
    public static PAYCODE check(String code) {

        PAYCODE status = PAYCODE.OTHER_CODE;

        String FormatCode = code.substring(0, 2);

        if ((FormatCode.equals("10") || FormatCode.equals("11") || FormatCode.equals("12") || FormatCode.equals("13") || FormatCode.equals("14") || FormatCode.equals("15")) && code.length() == 18) {
            status = PAYCODE.WECHAT_CODE;
        } else if
        ((FormatCode.equals("25") || FormatCode.equals("26") || FormatCode.equals("27") || FormatCode.equals("28") || FormatCode.equals("29") || FormatCode.equals("30")) && code.length() >= 16 && code.length() <= 24) {
            status = PAYCODE.ALIPAY_CODE;
        }

        return status;

    }
}
