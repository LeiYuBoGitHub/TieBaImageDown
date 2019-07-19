package com.lyb.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.sql.rowset.serial.SerialStruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/18 18:03
 * @Description:
 */
public class HttpUtil {

    private static CloseableHttpClient httpClient;

    private static CloseableHttpResponse response;

    private static InputStream httpGetContent(String url) {
        httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            response = httpClient.execute(get);
            return response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addFile(String url,String outFilePath, String outFileName) {
        InputStream inputStream = HttpUtil.httpGetContent(url);
        if (inputStream != null) {
            try {
                FileUtil.addFile(inputStream,outFilePath,outFileName);
                inputStream.close();
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String httpGet(String url) {
        httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            response = httpClient.execute(get);
            return EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return null;
    }

    private static void close() {
        close(response);
        close(httpClient);
    }

    private static void close(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        close(response);
        close(httpClient);
    }

    private static void close(CloseableHttpResponse response) {
        try {
            if (response != null) {
                response.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(CloseableHttpClient httpClient) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
