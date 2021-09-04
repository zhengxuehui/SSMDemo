package com.itcast.pachong.service.impl;

import com.itcast.pachong.service.ApiService;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class ApiServiceImpl implements ApiService {


    @Autowired
    private PoolingHttpClientConnectionManager cm;


    /**
     * 通过html 获取页面的数据
     *
     * @param url
     * @return
     */
    @Override
    public String getHtml(String url) {
        //获取httpClient 对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        //声明httpGet 请求对象
        HttpGet httpGet = new HttpGet(url);

        //设置用户代理信息
        httpGet.setHeader("User-Agent", "");
        //设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());


        CloseableHttpResponse response = null;

        try {
            // 使用HttpClient 发起请求 ，返回response
            response = httpClient.execute(httpGet);

            //解析response 返回数据
            if (response.getStatusLine().getStatusCode() == 200) {
                String html = "";
                //如果response。getEntry获取的结果为空，在执行EntryUtils.toString()会报错， 需要对response.getEntry()作非空判断
                if (response.getEntity() != null) {
                    html = EntityUtils.toString(response.getEntity(), "UTF-8");
                }
                return html;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                //不能关闭， 现在使用的是连接管理器
                //httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return null;
    }


    /**
     * 通过html 下载图片一下打印 图片名称
     *
     * @param url
     * @return
     */
    @Override
    public String getImage(String url) {
        //获取httpClient 对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        //声明httpGet 请求对象
        HttpGet httpGet = new HttpGet(url);

        //设置用户代理信息
        httpGet.setHeader("User-Agent", "");
        //设置请求参数RequestConfig
        httpGet.setConfig(this.getConfig());


        CloseableHttpResponse response = null;

        try {
            // 使用HttpClient 发起请求 ，返回response
            response = httpClient.execute(httpGet);

            //解析response  下载图片
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取文件的类型
                String contentType = response.getEntity().getContentType().getValue();
                // 获取文件后罪名
                String extName = "." + contentType.split("/")[1];
                //得到图片名称
                String imageName = UUID.randomUUID().toString().replace("-", "") + extName;

                //创建文件输出流
                OutputStream outputStream = new FileOutputStream(new File("E:/images/" + imageName));

                //写出文件输出流
                response.getEntity().writeTo(outputStream);

                //返回生成的文件名
                return imageName;

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                //不能关闭， 现在使用的是连接管理器
                //httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    /**
     * 获取请求参数对象
     *
     * @return
     */
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000) // 设置创建连接的超时时间
                .setConnectionRequestTimeout(500) //设置请求连接的超时时间
                .setSocketTimeout(10000) //设置连接的超时时间
                .build();

        return config;
    }


}
