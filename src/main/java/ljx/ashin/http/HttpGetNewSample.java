package ljx.ashin.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Ashin Liang on 2017/12/19.
 */
public class HttpGetNewSample {
    public static void main(String[] args) {
        String url = "http://www.baidu.com";
        //使用默认配置的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //使用get方法
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        InputStream inputStream = null;
        try {
            response = httpClient.execute(httpGet);
            //查看请求是否成功，可以查看响应码
            System.out.println("请求返回的响应码:"+response.getStatusLine().getStatusCode());
            //获取响应的实体内容
            HttpEntity httpEntity = response.getEntity();
//            System.out.println("响应的实体内容:"+http);
            //将其打印到控制台上面
            //方法一：使用EntityUtils的toString打印数据，consume关闭连接，释放资源。
            System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
            //EntityUtils.consume(httpEntity);
//            EntityUtils.toString(httpEntity);
            //方法二  :使用inputStream
           /* if (httpEntity != null) {
                inputStream = httpEntity.getContent();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);

                }
            }*/


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
