package com.xupt;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsoupFirst {


    @Test
    public void testUrl() throws IOException {
        // 解析Url
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    // 字符串解析
    @Test
    public void testUrl1() throws IOException {
        String file = FileUtils.readFileToString(new File("/home/maxu/IdeaProjects/crawlerdemo/test.html"), "utf8");
        Document document = Jsoup.parse(file);
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }


    // 字符串解析
    @Test
    public void testUrl2() throws IOException {
        Document document = Jsoup.parse(new File("/home/maxu/IdeaProjects/crawlerdemo/test.html"),"utf8");
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }
}
