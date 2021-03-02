package com.frame.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ProUtil {
	private String file;
	private Properties prop;
	private static Logger log = Logger.getLogger(ProUtil.class);

	/**
	 * 构造方法
	 *
	 * @param filePath
	 * @throws Exception
	 */
	public ProUtil(String file) {
		this.file = file;
		this.prop = readProperties();
	}

	/**
	 * 读取资源文件,并处理中文乱码
	 *
	 * @return
	 * @throws Exception
	 */
	private Properties readProperties(){
		Properties properties = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			properties.load(bf);
			inputStream.close(); // 关闭流
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return properties;
	}

	/**
	 * 获取某项文本内容
	 *
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getKey(String key){
		if (prop.containsKey(key)) {
			return prop.getProperty(key);
		} else {
			log.error("get key: " + key + " is not exist in " + file);
			throw new RuntimeException("get key: " + key + " is not exist in " + file);
		}
	}

	public void setProp(String key, String value) {
		if (prop == null) {
			prop = new Properties();
		}
		try {
			OutputStream outputStream = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
			prop.setProperty(key, value);
			prop.store(bw,key+" value");
			bw.close();
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("write " + key + " and " + value + " to file " + file + " fail");
			throw new RuntimeException(e.getMessage());
		}
		log.info("write " + key + " and " + value + " to file " + file + " successed");
	}
	/**
	 * 用在读取android_caps和ios_caps配置文件时
	 * @return
	 */
	public HashMap<String, String> getAllKeyValue() {
		HashMap<String, String> keyValus = new HashMap<String, String>();
		Iterator it = prop.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			keyValus.put(key.toString(), value.toString());
			log.info("get caps " + key.toString() + ":" + value.toString());
		}
		return keyValus;
	}

	public static void main(String[] args) throws Exception {
		ProUtil p = new ProUtil("src/main/resources/global.properties");
		String s = p.getKey("implicitlyWait");
		System.out.println(s);
	}
}
