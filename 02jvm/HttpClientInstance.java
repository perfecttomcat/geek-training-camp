package com.wpf.MyFirstMaven;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

/**
 * @description 客户端
 * @author perfecttomcat
 * @date 2021年7月4日 上午10:01:54
 */
public class HttpClientInstance {

	private static CloseableHttpClient httpClient = HttpClients.createDefault();

	public static void main(String[] args) {
		CloseableHttpResponse resp = null;
		try {
			HttpPost httpPost = new HttpPost("http://localhost:8801");
			httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
			StringEntity body = new StringEntity("", "UTF-8");
			httpPost.setEntity(body);

			resp = httpClient.execute(httpPost);
			System.out.println(resp.getStatusLine());
			HttpEntity entity = resp.getEntity();
			System.out.println(entity.toString());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
