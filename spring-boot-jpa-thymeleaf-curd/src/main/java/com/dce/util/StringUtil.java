package com.dce.util;


import org.apache.commons.lang3.StringUtils;

import java.net.SocketAddress;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 字符串是否包含中文
	 * @param str
	 * @return
	 */
	public static boolean containChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
	
	/**
	 * 是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * map转string
	 * @param map
	 * @param s1 一级分隔符
	 * @param s2 二级分隔符
	 * @return
	 */
	public static String toStr(Map<Integer, Integer> map, String s1, String s2){
		if(map == null || map.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> iter = map.keySet().iterator();
		while(iter.hasNext()){
			int key = iter.next();
			sb.append(key);
			sb.append(s2);
			sb.append(map.get(key));
			sb.append(s1);
		}
		return sb.substring(0, sb.length()-1);
	}
	
	/**
	 * 数组转String
	 * @param arr
	 * @param s 分隔符
	 * @return
	 */
	public static String toStr(Object[] arr, String s){
		if(arr == null || arr.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length; i++){
			if(i>0){
				sb.append(s);
			}
			sb.append(String.valueOf(arr[i]));
		}
		return sb.toString();
	}
	
	public static String toStr(int[] arr, String s){
		if(arr == null || arr.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length; i++){
			if(i>0){
				sb.append(s);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	public static String toStr(long[] arr, String s){
		if(arr == null || arr.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length; i++){
			if(i>0){
				sb.append(s);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	/**
	 * list转str
	 * @param list
	 * @param s
	 * @return
	 */
	public static String toStr(Collection<Integer> list, String s){
		if(list == null || list.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		for(int i : list){
			sb.append(i);
			sb.append(s);
		}
		sb.substring(0, sb.length()-1);
		return sb.toString();
	}
	
	/**
	 * list转str
	 * @param list
	 * @return
	 */
	public static String toStr(Collection<? extends Object> list){
		if(list == null || list.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		for(Object o : list){
			sb.append(o.toString());
			sb.append("_");
		}
		sb.substring(0, sb.length()-1);
		return sb.toString();
	}
	
	/**
	 * string转map
	 * @param str
	 * @param s1 分隔符1
	 * @param s2 分隔符2
	 * @return
	 */
	public static Map<Integer, Integer> strToMap(String str, String s1, String s2){
		if(isBlank_0(str))
			return null;
		String[] split1 = str.split(s1);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>(split1.length);
		for(String s : split1){
			String[] split2 = s.split(s2);
			map.put(Integer.parseInt(split2[0]), Integer.parseInt(split2[1]));
		}
		return map;
	}
	
	public static int[] toIntArr(String str, String split){
		if(isBlank_0(str)){
			return null;
		}
		String[] strarr = str.split(split);
		int[] intarr = new int[strarr.length];
		int i = 0;
		for(String s : strarr){
			intarr[i] = Integer.parseInt(s);
			i++;
		}
		return intarr;
	}

    public static float[] toFloatArr(String str, String split){
        if(isBlank_0(str)){
            return null;
        }
        String[] strarr = str.split(split);
        float[] intarr = new float[strarr.length];
        int i = 0;
        for(String s : strarr){
            intarr[i] = Float.parseFloat(s);
            i++;
        }
        return intarr;
    }
    
    /**
     * 解析属性
     * 配置表默认的格式
     * @param attrStr
     * @return
     */
    public static int[][] parseAttrs(String attrStr){
    	return toInt2Arr(attrStr, ",", "_");
    }
	
	public static int[][] toInt2Arr(String str, String split1, String split2){
		if(isBlank_0(str)){
			return null;
		}
		String[] strarr = str.split(split1);
		int[][] intarr = new int[strarr.length][];
		int i = 0;
		for(String s : strarr){
			intarr[i] = toIntArr(s, split2);
			i++;
		}
		return intarr;
	}
	
	public static int[][][] toInt3Arr(String str, String ost, String sst, String tst){
		if(isBlank_0(str)){
			return null;
		}
		String[] strs = StringUtils.split(str, ost);
		int[][][] arr = new int[strs.length][][];
		int i=0;
		for(String s:strs){
			arr[i]=toInt2Arr(s,sst,tst);
			i++;
		}
		return arr;
	}
	
	public static String[][] toStr2Arr(String str, String split1, String split2){
		if(isBlank_0(str)){
			return null;
		}
		String[] arr = str.split(split1);
		String[][] arr2 = new String[arr.length][];
		int i = 0;
		for(String s : arr){
			arr2[i] = s.split(split2);
			i++;
		}
		return arr2;
	}

	public static String[] toStrArr(String str, String split1){
		if(isBlank_0(str)){
			return null;
		}
		String[] arr = str.split(split1);
		return arr;
	}

	public static List<Integer> toIntList(String str, String split){
		if(isBlank_0(str)){
			return null;
		}
		String[] strarr = str.split(split);
		List<Integer> list = new ArrayList<Integer>(strarr.length);
		for(String s : strarr){
			list.add(Integer.parseInt(s));
		}
		return list;
	}
	
	public static long[] toLongArr(String str, String split){
		if(isBlank_0(str)){
			return null;
		}
		String[] strarr = str.split(split);
		long[] intarr = new long[strarr.length];
		int i = 0;
		for(String s : strarr){
			intarr[i] = Long.parseLong(s);
			i++;
		}
		return intarr;
	}
	
	public static List<String> toStringList(String str, String split){
		if(isBlank_0(str)){
			return null;
		}
		String[] arr = str.split(split);
		List<String> list = new ArrayList<String>(arr.length);
		for(String s : arr){
			list.add(s);
		}
		return list;
	}
	
	
	public static boolean isBlank(String str){
		return str == null || str.equals("");
	}
	
	public static boolean isBlank_0(String str){
		return str == null || str.equals("") || "0".equals(str);
	}
	

	public static String getIp(SocketAddress remoteAddress) {
		String[] ipArr = remoteAddress.toString().split("\\:");
		return ipArr[0].substring(1,ipArr[0].length());
	}
	
	/**
	 * 奖励数量翻倍
	 * @param reward
	 * @param rate 倍数
	 */
	public static void rewardRate(int[][] reward, int rate){
		if(reward == null || rate == 1) {
			return;
		}
		for(int[] i : reward){
			i[2] *= rate;
		}
	}
	/**
	 * 奖励数量翻倍
	 * @param reward
	 * @param rate 倍数
	 */
	public static int[][] rewardRate(String reward, int rate){
		if(reward == null) return null;
		int[][] arr = parseAttrs(reward);
		if(rate == 1){
			return arr;
		}
		for(int[] i : arr){
			i[2] *= rate;
		}
		return arr;
	}
}

