package com.neo.ratelimit.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>@Description: IP地址</p>
 *
 * @author Sue
 * @date 2018/1/12上午9:02
 */
public class IPUtils {
    private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

    public static final String IP_STATUS_UNKNOWN = "unknown";
    public static final String HTTP_HEADER_HTTPCLIENTIP = "HTTP_CLIENT_IP";
    public static final String HTTP_HEADER_PROXYCLIENTIP = "Proxy-Client-IP";
    public static final String HTTP_HEADER_XFORWARDEDFOR = "x-forwarded-for";
    public static final String HTTP_HEADER_WLPROXYCLIENTIP = "WL-Proxy-Client-IP";
    public static final String HTTP_HEADER_HTTPXFORWARDEDROR = "HTTP_X_FORWARDED_FOR";

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
/*            //todo 测试桩
            ip =  request.getParameter("IP");
            if (!StringUtils.isEmpty(ip) || !IP_STATUS_UNKNOWN.equalsIgnoreCase(ip)) {
                return ip;
            }*/

            ip = request.getHeader(HTTP_HEADER_XFORWARDEDFOR);
            if (StringUtils.isEmpty(ip) || IP_STATUS_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HTTP_HEADER_PROXYCLIENTIP);
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || IP_STATUS_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HTTP_HEADER_WLPROXYCLIENTIP);
            }
            if (StringUtils.isEmpty(ip) || IP_STATUS_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HTTP_HEADER_HTTPCLIENTIP);
            }
            if (StringUtils.isEmpty(ip) || IP_STATUS_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader(HTTP_HEADER_HTTPXFORWARDEDROR);
            }
            if (StringUtils.isEmpty(ip) || IP_STATUS_UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            logger.error("IPUtils ERROR ", e);
        }

//        logger.info("ip="+ip);
        //使用代理，则获取第一个IP地址
        if (StringUtils.isNotEmpty(ip) && ip.indexOf(",") >= 0) {

            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                ip = ips[i];
                if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
                    break;
                }
            }
        }
//        logger.info("ip="+ip);
        return ip;
    }

    /**
     * 通过接口 http://ip-api.com/json/ 获取IP数据
     *
     * <p>
     * 目前将IP存到Ip地址库中，ip库中有的不再使用这个方法查询
     * <p>
     *
     * @param ip IP地址
     * @return exam:山东省青岛市 联通
     * @throws URISyntaxException URI语法错误
     * @throws IOException
     */
    public static String getRealAddress(String ip) throws URISyntaxException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequest request = restTemplate.getRequestFactory()
                .createRequest(new URI("http://ip-api.com/json/" + ip
                        + "?lang=zh-CN&key=3hUE571XaIwRNmu&fields=country,regionName,city,isp,status"), HttpMethod.GET);
        ClientHttpResponse response = request.execute();
        if (HttpStatus.OK == response.getStatusCode()) {
            InputStream is = response.getBody();
            byte[] bytes = new byte[2048];
            StringBuilder buffer = new StringBuilder();
            while (is.read(bytes) > -1) {
                buffer.append(new String(bytes, "UTF-8"));
            }
            JSONObject jsonObject = JSON.parseObject(buffer.toString());
            if ("success".equals(jsonObject.getString("status"))) {
                buffer = new StringBuilder();
                buffer.append(jsonObject.getString("country"));
                buffer.append("-");
                buffer.append(jsonObject.getString("regionName"));
                buffer.append("-");
                buffer.append(jsonObject.getString("city"));
                buffer.append("-");
                buffer.append(jsonObject.getString("isp"));
                return buffer.toString();
            }
        }
        return null;
    }
}
