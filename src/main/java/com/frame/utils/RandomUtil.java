package com.frame.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static java.lang.Math.abs;

public class RandomUtil {
	/**
	 * 随机生成指定长度的字符串
	 * @param
	 * @param lengthOfString
	 * @return
	 */
	public static String getRndStrAndNumberByLen(int lengthOfString) {
		int i, count = 0;
		final String chars ="1,2,3,4,5,6,7,8,9,0,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");//字符串数组

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;
		//先随机生成数字，然后将该数字（索引）对应的数组的字符，添加到字符串中
		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于36，i值就在0-35之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString().toLowerCase();
	}
	public static String getRndStrByLen(int lengthOfString) {
		int i, count = 0;
		final String chars ="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;

		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于26，i值就在0-25之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString().toLowerCase();
	}
	public static String getRndStrZhByLen(int lengthOfString) {
		int i, count = 0;
		final String chars ="我,也,不,知,道,你,想,发,神,好,好,的,地,方,发,的,的,是,问,有,二,与";
		String[] charArr = chars.split(",");

		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();
		int strLen = charArr.length;

		while (count < lengthOfString) {
			i = rnd.nextInt(strLen);//strLen如果等于30，i值就在0-30之间
			//System.out.println(i);
			randomStr.append(charArr[i]);
			count++;
		}
		return randomStr.toString().toLowerCase();
	}
	/**
	 * 随机生成指定长度的数字，以字符串形式返回
	 * @param lengthOfNumber
	 * @return
	 */
	public static String getRndNumByLen(int lengthOfNumber) {
		int i, count = 0;
		//098
		StringBuffer randomStr = new StringBuffer("");
		Random rnd = new Random();

		while (count < lengthOfNumber) {
			i = abs(rnd.nextInt(10));//生成[0,9)的随机数
			if (i == 0 && count == 0) {
				//意思是不生成开始为0的数字，比如098,01
			} else {
				randomStr.append(String.valueOf(i));
				count++;
			}
		}
		return randomStr.toString();
	}

	/**
	 * 生成指定范围内的数字，不包含参数本身
	 * @param extent
	 * @return
	 */
	public static int getExtentRandomNumber(int extent) {
		int number = (int) (Math.random() * extent);
		return number;
	}
	public static int getExtentRandomNumber1(int extent) {

		Random rnd =new Random();
		int number = rnd.nextInt(extent);
		return number;
		//return number;
	}
	/**
	 * 生成指定范围内的浮点数
	 * @param min
	 * @param max
	 * @return
	 */
	private static float randomFloat(int min,int max){
        Random random = new Random();
        //10,100
        //0.0746273646*100=746.273646=746=7.46
//        int s = random.nextInt(max)%(max-min+1) + min;
		//random.nextDouble [0.0-1.0)
        float x=min;//x=10
        while(x<=min){
        	double db = random.nextDouble() * max * 100;
        	x = ((int) db) / 100f;
        }
        return x;
	}
	/**
	 * 生成指定范围内的整数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomInt(int min,int max){
        Random random = new Random();
        //10,100 88%91=88+10=98
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
	public static int randomint(int min,int max){
		int i = (int) (Math.random() * (max - min) + min);
		return  i;
	}
	public  static  String  time(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = formatter.format(new Date());
		return time;
	}




	/**
	 * 随机生成指定范围内的日期
	 * @param beginDate
	 * @param endDate
	 * @return 返回值的格式是2019-01-29
	 */
	public static String randomDate(String beginDate,String endDate){
		Random rand = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] beginStr=beginDate.split("-");
		String[] endStr=endDate.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(beginStr[0]),Integer.parseInt(beginStr[1]), Integer.parseInt(beginStr[2]));
		long start = cal.getTimeInMillis();
		cal.set(2008, 0, 1);
		cal.set(Integer.parseInt(endStr[0]),Integer.parseInt(endStr[1]), Integer.parseInt(endStr[2]));
		long end = cal.getTimeInMillis();
		Date d = new Date(start + (long)(rand.nextDouble() * (end - start)));
		System.out.println(format.format(d));
		return format.format(d);

    }
	public static void main(String[] args) {

		System.out.println(randomint(4,10));
		//System.out.println(getExtentRandomNumber(5));
		//System.out.println(getExtentRandomNumber1(5));
//		Random r=new Random();
//		System.out.println(getExtentRandomNumber(4));
//		System.out.println(randomFloat(10,100));
		//System.out.println(RandomUtil.randomInt(1, 2));
//		System.out.println(getRndStrZhByLen(5)+getRndStrByLen(5));
//		System.out.println(r.nextDouble());
//		System.out.println(randomInt(1,10));
////		System.out.println(randomInt(5,10));
//		int s=getInt("appium -p 4490 -bp 2234",1);
//		System.out.println(s);
//		System.out.println("0"+getRndNumByLen(3));
//		System.out.println(getInt("appium -p 4490 -bp 2253 -U 127.0.0.1:62001>logs/127.0.0.1:62001.log",6));
	}

}
