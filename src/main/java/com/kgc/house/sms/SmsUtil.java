package com.kgc.house.sms;

import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class SmsUtil {
	
	//�û���
	private static String Uid = "wzcgit123";
	
	//�ӿڰ�ȫ��Կ
	private static String Key = "d41d8cd98f00b204e980";
	
	//�ֻ����룬���������13800000000,13800000001,13800000002
	//private static String smsMob = "13387277008";
	
	//��������
	//private static String smsText = "˯����û?";


	/**
	 * ���Ͷ��ŵķ���
	 * @param smsMob  �ֻ���
	 * @param smsText  ���͵��ı�
	 * @return ��ʾ���ͳɹ������� >0 ���ǳɹ�
	 */
	public static int sendMsm(String smsMob,String smsText) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF����
		int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
		return result;
	}
}
