package com.wind.common.utils.token;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * 一个Token加密与解密工具 ，不限于Token范围
 * 使用时，结合 TokenProductFactory 生成 平台Token 通过解密，识别Token来源。
 *
 * @author linxiaoqing
 * 注：通用的base64加密方式
 */
public class TokenToolEncrypterBase64 implements TokenBaseInter {

    private static final Log logger = LogFactory.getLog(TokenToolEncrypterBase64.class);

    @SuppressWarnings("restriction")
    private static BASE64Encoder encoder = new BASE64Encoder();

    @SuppressWarnings("restriction")
    private static BASE64Decoder decoder = new BASE64Decoder();

    /**
     * 对字符串进行加密
     *
     * @param str
     * @return
     */
    @Override
    public String encrypt(String str) {
        return encrypt(str.getBytes()).replace("=", "_");
    }

    /**
     * 对数组进行加密
     *
     * @param b
     * @return
     */
    @SuppressWarnings("restriction")
    public String encrypt(byte[] b) {
        return encoder.encode(b);
    }

    /**
     * 对加密的信息进行解密
     *
     * @param str
     * @return
     */
    @SuppressWarnings("restriction")
    @Override
    public String decrypt(String str) {
        try {
            byte[] temp = decoder.decodeBuffer(str.replace("_", "="));
            String result = new String(temp);
            temp = null;
            return result;
        } catch (IOException e) {
            logger.error("解密[" + str + "]出错" + e);
            return null;
        }
    }

    /**
     * 对加密的信息进行解密
     *
     * @param b
     * @return
     */
    public String decrypt(byte[] b) {
        return decrypt(new String(b));
    }


    /**
     * 生成token
     *
     * @param pramaters 自定义参数
     */
    @Override
    public String productToken(String[] pramaters) {
        if (pramaters == null || pramaters.length == 0) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < pramaters.length; i++) {
                sb.append(pramaters[i] + "-");
            }
            sb.append(key);//最后加上Key值
            return this.encrypt(sb.toString());
        }
    }

    /**
     * 通过自定义前缀和用户编号，生成token
     *
     * @param pix    前缀
     * @param userNo 用户编码
     */
    @Override
    public String productToken(String pix, String userNo) {
        return this.encrypt(pix + "-" + userNo + "-" + System.currentTimeMillis() + "-" + key);
    }

}
