package com.dru.bean;

import java.io.Serializable;

public class IPPool implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ip地址
	 */
	private String IPAddress;
	/**
	 *  端口号
	 */
    private String IPPort;
    /**
     * ip类型
     */
    private String IPType;
    /**
     * ip速度
     */
    private String IPSpeed;
    /**
     * 用于计算ip是否可用的计数器
     */
    private int useCount;
    
    public IPPool() { this.useCount = 0; }

    public IPPool(String IPAddress, String IPPort, String IPType, String IPSpeed) {
        this.IPAddress = IPAddress;
        this.IPPort = IPPort;
        this.IPType = IPType;
        this.IPSpeed = IPSpeed;
        this.useCount = 0;
    }
    
	public String getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public String getIPPort() {
		return IPPort;
	}
	public void setIPPort(String iPPort) {
		IPPort = iPPort;
	}
	public String getIPType() {
		return IPType;
	}
	public void setIPType(String iPType) {
		IPType = iPType;
	}
	public String getIPSpeed() {
		return IPSpeed;
	}
	public void setIPSpeed(String iPSpeed) {
		IPSpeed = iPSpeed;
	}
	public void initCount() {
		this.useCount = 0;
	}
	public void setUseCount() {
		this.useCount++;
	}
	public int getUseCount() {
		return useCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "IPPool [IPAddress=" + IPAddress + ", IPPort=" + IPPort + ", IPType=" + IPType + ", IPSpeed=" + IPSpeed
				+ ", useCount=" + useCount + "]";
	}
	
}
