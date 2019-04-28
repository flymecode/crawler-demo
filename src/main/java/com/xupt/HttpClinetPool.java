package com.xupt;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClinetPool {
    public static void main(String[] args) throws IOException {
        // 创建链接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        // 设置主机的最大链接数
        cm.setDefaultMaxPerRoute(10);
        // 使用连接池管理发起请求
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        // 配置请求信息
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(10 * 1000)
                .build();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "utf8");
            System.out.println(content);
        }
        response.close();
    }
}
