package com.example.safedistrict.util;

import ch.qos.logback.core.joran.spi.XMLUtil;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConfigUtil {
    /**
     * 服务号相关信息
     */
    public final static String APP_ID = "2016";//服务号的应用ID
    public final static String APP_SECRET = "2016";//服务号的应用密钥
    public final static String TOKEN = "weixinCourse";//服务号的配置token
    public final static String MCH_ID = "2016";//商户号
    public final static String API_KEY = "2016";//API密钥
    public final static String SIGN_TYPE = "MD5";//签名加密方式
    public final static String CERT_PATH = "apiclient_cert.p12";//微信支付证书存放路径地址
    static ResourceBundle resource = ResourceBundle.getBundle("config");
    //微信支付统一接口的回调action
    public final static String NOTIFY_URL = resource.getString("WEIXIN_NOTIFY_URL");
    /**
     * 微信基础接口地址
     */
    //获取token接口(GET)
    public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //oauth2授权接口(GET)
    public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //刷新access_token接口（GET）
    public final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    // 菜单创建接口（POST）
    public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 菜单查询（GET）
    public final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 菜单删除（GET）
    public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    /**
     * 微信支付接口地址
     */
    //微信支付统一接口(POST)
    public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信退款接口(POST)
    public final static String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    //订单查询接口(POST)
    public final static String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    //关闭订单接口(POST)
    public final static String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
    //退款查询接口(POST)
    public final static String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    //对账单接口(POST)
    public final static String DOWNLOAD_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    //短链接转换接口(POST)
    public final static String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
    //接口调用上报接口(POST)
    public final static String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";

//    public static void commonParams(SortedMap<Object, Object> packageParams) {
//        // 账号信息
//        String appid = ConfigUtil.APP_ID; // appid
//        String mch_id = ConfigUtil.MCH_ID; // 商业号
//        // 生成随机字符串
//        String currTime = PayCommonUtil.getCurrTime();
//        String strTime = currTime.substring(8, currTime.length());
//        String strRandom = PayCommonUtil.buildRandom(4) + "";
//        String nonce_str = strTime + strRandom;
//        packageParams.put("appid", appid);// 公众账号ID
//        packageParams.put("mch_id", mch_id);// 商户号
//        packageParams.put("nonce_str", nonce_str);// 随机字符串
//    }
//
//    /**
//     * 该接口主要用于扫码原生支付模式一中的二维码链接转成短链接(weixin://wxpay/s/XXXXXX)，减小二维码数据量，提升扫描速度和精确度。
//     *
//     * @param urlCode void
//     * @Author 张志朋
//     * @Date 2016年10月26日
//     * 更新日志
//     * 2016年10月26日 张志朋  首次创建
//     */
//    @SuppressWarnings("rawtypes")
//    public static void shorturl(String urlCode) {
//        try {
//            String key = ConfigUtil.API_KEY; // key
//            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
//            ConfigUtil.commonParams(packageParams);
//            packageParams.put("long_url", urlCode);// URL链接
//            String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
//            packageParams.put("sign", sign);// 签名
//            String requestXML = PayCommonUtil.getRequestXml(packageParams);
//            String resXml = HttpUtil.postData(ConfigUtil.SHORT_URL, requestXML);
//            Map map = XMLUtil.doXMLParse(resXml);
//            String returnCode = (String) map.get("return_code");
//            if ("SUCCESS".equals(returnCode)) {
//                String resultCode = (String) map.get("return_code");
//                if ("SUCCESS".equals(resultCode)) {
//                    urlCode = (String) map.get("short_url");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}