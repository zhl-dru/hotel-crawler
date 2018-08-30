package com.dru.ipPool;

import java.util.LinkedList;
import java.util.List;

import com.dru.bean.IPPool;

public class IPFilter {
	
	/**
	 * @Description:IP筛选器,保留HTTPS类型及连接速度3秒以下的IP
	 */
	public static List<IPPool> Filter(List<IPPool> ipPool) {
	        List<IPPool> newIPPool = new LinkedList<>();

	        for (IPPool ip : ipPool) {
	            String ipType = ip.getIPType();
	            String ipSpeed = ip.getIPSpeed();

	            ipSpeed = ipSpeed.substring(0, ipSpeed.indexOf("秒"));
	            double Speed = Double.parseDouble(ipSpeed);

	            if (ipType.equals("HTTPS") && Speed <= 3.0) {
	                newIPPool.add(ip);
	            }
	        }

	        return newIPPool;
	}
}
