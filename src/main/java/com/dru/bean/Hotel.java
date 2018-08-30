package com.dru.bean;

public class Hotel {
	
	/**
	 * 酒店ID
	 */
	private String id;
	/**
	 * 酒店名称
	 */
	private String name;
	/**
	 * 纬度
	 */
	private double lat;
	/**
	 * 经度
	 */
	private double lon;
	/**
	 * 详情页地址
	 */
	private String url;
	/**
	 * 图片地址
	 */
	private String img;
	/**
	 * 酒店地址
	 */
	private String address;
	/**
	 * 客户点评
	 */
	private double score;
	/**
	 * 推荐百分比
	 */
	private int dpscore;
	/**
	 * 点评总数
	 */
	private int dpcount;
	/**
	 * 星级
	 */
	private String star;
	/**
	 * 星级描述
	 */
	private String stardesc;
	/**
	 * 酒店简称
	 */
	private String shortName;
	
	private boolean isSingleRec;
	
	/**
	 * 酒店最低价格
	 */
	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getDpscore() {
		return dpscore;
	}

	public void setDpscore(int dpscore) {
		this.dpscore = dpscore;
	}

	public int getDpcount() {
		return dpcount;
	}

	public void setDpcount(int dpcount) {
		this.dpcount = dpcount;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getStardesc() {
		return stardesc;
	}

	public void setStardesc(String stardesc) {
		this.stardesc = stardesc;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public boolean isSingleRec() {
		return isSingleRec;
	}

	public void setSingleRec(boolean isSingleRec) {
		this.isSingleRec = isSingleRec;
	}
}
