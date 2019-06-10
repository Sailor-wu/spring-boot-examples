package com.dce.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.dce.model.Location;

/**
 * desc 工具
 * 
 * @author Administrator
 */
public class UtilTool {

	public static String getPublicIP() {
		String ip = "";

		Document doc = null;
		Connection con = Jsoup.connect("http://www.net.cn/static/customercare/yourip.asp").timeout(10000);

		try {
			doc = con.get();
			// 获得包含本机ip的文本串：您的IP是：[xxx.xxx.xxx.xxx]
			Elements els = doc.body().select("center");
			for (org.jsoup.nodes.Element el : els) {
				ip = el.text();
			}
			// 从文本串过滤出ip，用正则表达式将非数字和.替换成空串""
			ip = ip.replaceAll("[^0-9.]", "");
			System.out.println("ip:\t"+ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ip;
	}
	
	/**
	 * desc 获取定位地址信息
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 */
	public static JSONObject getAddrName() throws JSONException, IOException{
		JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=dqGTLmnqwqqW3vtO7bUuBsw0djFRxiPu&ip="+getPublicIP());
        JSONObject content=json.getJSONObject("content");              //获取json对象里的content对象
        if(content == null) {
        	return json;
        }
        String addr = json.get("address").toString();					//获取总地址，“CN|广东|广州|None|CHINANET|0|0”
        JSONObject addr_detail=content.getJSONObject("address_detail");//从content对象里获取address_detail
        String city=addr_detail.get("city").toString();                //获取市名，可以根据具体需求更改，如果需要获取省份的名字，可以把“city”改成“province”...
        String province = addr_detail.get("province").toString(); 
        String city_code =  addr_detail.get("city_code").toString();
        
        JSONObject point=content.getJSONObject("point"); 
        String point_x = point.get("x").toString(); 
        String point_y =  point.get("y").toString();
        Location location = new Location();
        location.setAddr(addr);
        location.setCity(city);
        location.setCity_code(city_code);
        location.setPoint_x(point_x);
        location.setPoint_y(point_y);
        location.setProvince(province);
        return JSONObject.parseObject(JsonUtil.objToJson(location));
    }
	
	public static JSONObject readJsonFromUrl(String url) throws MalformedURLException, IOException {
		InputStream is = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = (JSONObject) JSON.parse(jsonText);
            return json;
        } finally {
            //关闭输入流
            is.close();
        }
	}



	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
	}



	public static void main(String[] args) throws JSONException, IOException {
		System.out.println(getAddrName());
	}
}
