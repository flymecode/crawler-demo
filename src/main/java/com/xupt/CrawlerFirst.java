package com.xupt;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerFirst {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // 打开浏览器,创建一个HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 输入网址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        // 创建URL
        URIBuilder urlBuilder = new URIBuilder("http://www.itheima.com/search");
        urlBuilder.setParameter("keys", "Java");
        HttpGet httpGet1 = new HttpGet(urlBuilder.build());
        HttpPost httpPost = new HttpPost("http://www.itheima.com/search");
        // 声明list集合，封装集合中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys", "Java"));
        // 创建表单的对象
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");
        httpPost.setEntity(formEntity);
        // 按回车，发起请求，返回数据
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 解析响应，获取数据
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity,"utf8");
            System.out.println(content.length());
        }
        response.close();
        httpClient.close();
    }
}
