# hotel-crawler
携程酒店信息爬虫

IP池问题：抓取到的IP可用比例很低，造成爬取效率低，可以考虑接入代理网站API代替爬取免费IP的方式构建IP池，省去筛选IP的试错过程，备忘。

环境：java8
使用的开源工具可见maven配置文件

携程全国城市酒店入口：http://hotels.ctrip.com/domestic-city-hotel.html<br>
城市信息数据样例：<br>
"<a title="保亭酒店" href="/hotel/baoting54">保亭</a>"<br>
在后续爬取中需要"baoting54"，将城市的拼音和key作为表单参数提交

携程在具体的页面里使用Ajax传输除页面头尾的内容，通过JS渲染在浏览器端拼接网页，通过抓包可以发现<br>
![](https://github.com/zhl-dru/hotel-crawler/raw/master/picture/a1.jpg) <br>
信息存储在hotelPositionJSON中，<br>
返回这个HTML的URL:http://hotels.ctrip.com/Domestic/Tool/AjaxHotelList.aspx<br>
完整的请求参数可参见urlCreate/PostJson.java<br>
实际测试发现，实际只需要提交其中四个参数"cityName","cityId","cityPY","page"<br>

获得json串后，其中包含当前页所有酒店的基本信息和详情页url，使用正则将需要的信息提取即可

PS:携程反爬虫阻断连接的反应推测是同IP连续访问300-500次，并且忽略两次访问间的时间，反爬虫会短时间阻断访问，不会长时间封禁IP，携程的反爬虫似乎晚上会下线，测试时有一天晚上单一IP运行了一整晚没有出现访问失败
