package com.zfg.learn.until;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*
* 获取api的数据
*
* */
@SpringBootTest
public class CatchApi {
    public final String METHOD_DEFAULT = "GET";


    //获取输入的api的数据
    public String getJsonFromApi(String originalUrl) throws IOException {
        return getJsonFromApi(originalUrl, METHOD_DEFAULT);
    }

    //获取输入的api的数据
    public String getJsonFromApi(String originalUrl, String method) throws IOException {
        String result = "";
        URL url = new URL(originalUrl);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("请求过于频繁，请一小时后再获取数据");
            e.printStackTrace();
        }
        // 设置连接主机服务器超时时间：10000毫秒
        connection.setConnectTimeout(10000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);
        // 设置重连次数为五次
        /*connection.setChunkedStreamingMode(5);*/
        //请求方式为get
        connection.setRequestMethod(method);
        /*connection.setRequestProperty("Content-Type", "application/json");*/
        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {
            System.out.println("link ok");
            InputStream is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //使用字符缓冲流，
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }

            result = sbf.toString();
            System.out.println(sbf);
        } else {
            System.out.println("link fault");
        }

        return result;
    }

    /*跳过防盗链*/
    public String skipReferer(String api, String referer) throws IOException {
       //String result = "https://api.bilibili.com/x/relation/followers?vmid=20736117&pn=1&ps=20&order=desc&jsonp=jsonp&callback=__jp5";
        String dataJSONP = null;
        URL url = new URL(api);
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("请求过于频繁，请一小时后再获取数据");
            e.printStackTrace();
        }
        //伪造referer
        connection.setRequestProperty("referer", referer);
        // 设置连接主机服务器超时时间：10000毫秒
        connection.setConnectTimeout(10000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);
        //请求方式为get
        connection.setRequestMethod("GET");
        /*connection.setRequestProperty("Content-Type", "application/json");*/
        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {
            System.out.println("link ok");
            InputStream is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //使用字符缓冲流，
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }

            dataJSONP = sbf.toString();
            System.out.println(sbf);
        } else {
            System.out.println("link fault");
        }

        return dataJSONP;
    }

    public String getJsonFromApiByCook(String originalUrl, String cookie) throws IOException {
        String result = "";
        URL url = new URL(originalUrl);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("请求过于频繁，请一小时后再获取数据");
            e.printStackTrace();
        }
        // 设置连接主机服务器超时时间：10000毫秒
        connection.setConnectTimeout(10000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);
        connection.setRequestProperty("cookie", cookie); //请求方式为get

        connection.setRequestMethod("GET");
        /*connection.setRequestProperty("Content-Type", "application/json");*/
        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {
            System.out.println("link ok");
            InputStream is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //使用字符缓冲流，
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }

            result = sbf.toString();
            System.out.println(sbf);
        } else {
            System.out.println("link fault");
        }

        return result;
    }

    public String getJsonFromApiByHeader(String originalUrl, HashMap<String, String> headerMap) throws IOException {
        String result = "";
        URL url = new URL(originalUrl);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("请求过于频繁，请一小时后再获取数据");
            e.printStackTrace();
        }

        // 设置连接主机服务器超时时间：10000毫秒
        connection.setConnectTimeout(10000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);

        //设置请求头
        for (Map.Entry<String, String> entry:headerMap.entrySet()){
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        connection.setRequestMethod("GET");
        /*connection.setRequestProperty("Content-Type", "application/json");*/
        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {
            System.out.println("link ok");
            InputStream is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //使用字符缓冲流，
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }

            result = sbf.toString();
        } else {
            System.out.println("link fault");
        }

        return result;
    }





    //发送请求并且携带参数
    public String request(String originalUrl, String data) throws IOException {
        String result = "";
        URL url = new URL(originalUrl);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("请求过于频繁，请一小时后再获取数据");
            e.printStackTrace();
        }

        connection.setDoOutput(true);
        // 设置连接主机服务器超时时间：10000毫秒
        connection.setConnectTimeout(10000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);
        //请求方式为get
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        //发送数据
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
        PrintWriter printWriter = new PrintWriter(outputStreamWriter);
        printWriter.println(data);
        printWriter.flush();

        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {
            System.out.println("link ok");
            InputStream is = connection.getInputStream();
            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //使用字符缓冲流，
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }

            result = sbf.toString();
            System.out.println(sbf);
        } else if (connection.getResponseCode() == 500){
            System.out.println("server error, to check server's log to find reason");
        } else {
            System.out.println("失败,响应码为" + connection.getResponseCode());
        }

        return result;
    }

    public String request(String originalUrl, HashMap<String, String> header, String method) throws IOException{
        return this.request(originalUrl, header, null, method);
    }


    /**
     * 最多选择版
     * @param originalUrl
     * @param header
     * @param data
     * @param method
     * @return
     * @throws IOException
     */
    public String request(String originalUrl, HashMap<String, String> header, String data, String method) throws IOException {
        String result = "";
        URL url = new URL(originalUrl);

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("请求过于频繁，请一小时后再获取数据");
            e.printStackTrace();
        }

        // 设置连接主机服务器超时时间：10000毫秒
        connection.setConnectTimeout(10000);
        // 设置读取主机服务器返回数据超时时间：60000毫秒
        connection.setReadTimeout(60000);

        //设置请求头
        for (Map.Entry<String, String> entry:header.entrySet()){
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        connection.setRequestMethod(method);
        //connection.setRequestProperty("Content-Type", "application/json");
        //发送数据
        if (data != null){
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);
            printWriter.println(data);
            printWriter.flush();
        }

        // 通过连接对象获取一个输入流，向远程读取
        if (connection.getResponseCode() == 200) {
            System.out.println("link ok");
            InputStream is = connection.getInputStream();
            //对输入流对象进行包装:charset根据工作项目组的要求来设置
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //使用字符缓冲流，
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            // 循环遍历一行一行读取数据
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }

            result = sbf.toString();
        } else {
            System.out.println("link fault");
        }

        return result;
    }

    /*public static void main(String[] args) throws IOException {
        HashMap hashMap = new HashMap();
        hashMap.put("cookie", "SESSDATA=e1319954%2C1639991814%2C9f66d*61; bili_jct=1bda6f083fb5cbe1f451fbb7bc6ee100;buvid3=448EC7E8-5003-7E20-FFE9-4EA8436B235A46228infoc;");
        hashMap.put("content-type", "application/x-www-form-urlencoded");
        hashMap.put("referer","https://www.bilibili.com/");
        System.out.println(request("https://api.bilibili.com/pgc/web/follow/del",hashMap ,
                "csrf=1bda6f083fb5cbe1f451fbb7bc6ee100&season_id=20001",
                "POST"));
    }*/
}
