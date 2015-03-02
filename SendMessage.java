package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class SendMessage {
	String user,key,number,text;
	String backContent=null;
	final String url="http://openfetionapi.sinaapp.com/fetion.php";
	
	public SendMessage() {
		user="";
		key="";
		number="";
		text="";
//		SendMessageByGet();
		SendMessageByPost();
	}
	public void SendMessageByGet(){
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet get=new HttpGet(url+"?"+"&user="+user+"&key="+key+"&number="+number+"&text="+text);
		try {
			System.out.println(url+"?"+"&user="+user+"&key="+key+"&number="+number+"&text="+text);
			HttpResponse response=client.execute(get);
			HttpEntity entity=response.getEntity();
			if(entity!=null){
				InputStream is=entity.getContent();
				BufferedReader in=new BufferedReader(new InputStreamReader(is));
				StringBuffer buffer=new StringBuffer();
				String line="";
				while((line=in.readLine())!=null)
					buffer.append(line+"\n");
				backContent=buffer.toString();
				System.out.println(buffer.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void SendMessageByPost() {
		CloseableHttpClient client=HttpClients.createDefault();
	
		
		HttpPost post =new HttpPost(url);
		List<NameValuePair>nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user", user));
		nvps.add(new BasicNameValuePair("key", key));
		nvps.add(new BasicNameValuePair("number", number));
		nvps.add(new BasicNameValuePair("text", text));
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			HttpResponse response=client.execute(post);
			HttpEntity entity=response.getEntity();
			if(entity!=null){
				InputStream is=entity.getContent();
				BufferedReader in=new BufferedReader(new InputStreamReader(is));
				StringBuffer buffer=new StringBuffer();
				String line="";
				while((line=in.readLine())!=null)
					buffer.append(line+"\n");
				backContent=buffer.toString();
				System.out.println(buffer.toString());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	
	public static void main(String[] args) {
		new SendMessage();
	}
}
