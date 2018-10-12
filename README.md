# Meitao Mart 网站
## 运行project和所需要服务器搭建方法

## 第一部分
### web层
meitao-manager-web: _后台管理系统的web层_

meitao-portal-web: _主页web层_

meitao-search-web: _搜索结果_

meitao-item-web: _商品详情_

meitao-sso-web: _登录注册、找回密码等_

meitao-cart-web: _购物车页面展示_

meitao-order-web: _结算中心_

meitao-user-web: _个人中心_


###Service层：

meitao-manager: _商品和商品分类的代码逻辑，所依赖其它的服务: redis_

meitao-content: _主页中的内容，包括轮播图和优选商品的代码逻辑，所依赖其它的服务: redis_

meitao-search: _搜索的代码逻辑， 所依赖其它的服务: solr_

meitao-sso: _注册登录、找回密码代码逻辑，所依赖其它的服务: redis_

meitao-cart: _购物车后台代码逻辑，所依赖其它的服务: redis_

meitao-order: _支付代码逻辑，所依赖其它的服务: redis_

meitao-user: _个人中心的代码逻辑，所依赖其它的服务: redis_



###目前所用的域名：192.168.1.100 (如果测试，请全局替换成自己的域名)

**启动顺序如下（括号内为每个服务的端口号）：**

**先service层：**
```
meitao-manager (8080) -> meitao-content (8083) -> meitao-search (8084) -> meitao-sso (8087) -> meitao-cart (8089) -> meitao-order (8091) -> meitao-user (8093)
```

**后web层：**
```
meitao-manager-web (8081) -> meitao-portal-web (8082) -> meitao-search-web (8085) -> meitao-item-web (8086) -> meitao-sso-web (8088) -> meitao-cart-web (8090)-> meitao-order-web (8092) -> meitao-user-web (8094)
```

###后台管理系统使用说明：
**默认url和端口： http://192.168.1.100:8081**

第一类：
1. 商品管理目录
   - 新增商品（红色框为必填部分，商品图片请上传四张，以正方形为佳，最好不要小于360*360。商品描述图片数量不限，大小为1000*560，商品描述不需要）
   - 商品管理（获取所有商品，编辑上架下架功能暂不可用） 
   - 商品分类管理（此分类将显示在主页导航栏，设三级分类不多不少，点右键可添加重命名并删除，请保证只有三级分类）
   - 规格参数（无用）
2. 网站内内容管理
   - 内容分类管理（内容分类是对主页不同的展示进行分类，如轮播图等）
   - 内容管理
3. 索引库管理
   - solr索引库维护（点击“一键导入商品数据到索引库”按钮可以把所有商品都更新到索引库，所有商品必须经过导入索引库才能被用户搜索到）
4. 订单管理目录
   - 订单管理（查看所有订单）


##第二部分：
1. ZooKeeper
   - 当前ip地址： 192.168.1.211 (测试请全局替换成自己的ip地址)。
   - 测试请全局替换成自己的ip地址，每个服务包括service和web层，都在这个文件下：/src/main/resources/spring/applicationContext-service.xml， 注意所有的zookeeper地址必须保持一致。


2. Redis
   - 当前ip地址： 192.168.1.211 (测试请全局替换成自己的ip地址)。
   - 测试请全局替换成自己的ip地址，每个服务包括service和web层，都在这个文件下：/src/main/resources/spring/applicationContext-redis.xml，注意所有的redis地址必须保持一致。
   - Redis单机版的默认端口: 6379， 集群版端口： 7001-7006， 单机版和集群版切换请修改文件：/src/main/resources/spring/applicationContext-redis.xml


3. Solr: 
   - 当前ip地址： 192.168.1.213(测试请全局替换成自己的ip地址)。
   - 测试请全局替换成自己的ip地址，每个服务包括service和web层，都在这个文件下：/src/main/resources/spring/applicationContext-solr.xml，注意所有的redis地址必须保持一致。
   - Solr单机版和集群版切换请修改文件：/meitao-search-service/src/main/resources/spring/applicationContext-solr.xml




4. 图片服务器:
   - Nginx：ip地址：192.168.1.11，用于图片服务器的Nginx端口：8081
   - FTP：
     - ip地址：192.168.1.11
	 - FTP服务器的配置文件：/meitao-manager-web/src/main/resources/conf/resource.properties
     - FTP服务器的内容：
     ```
		DEFAULT_HOST=192.168.1.11  //图片服务器的host
        DEFAULT_PORT=21	// ftp的端口
        DEFAULT_USERNAME=ftpuser  //ftp的username（需要在Linux创建相同用户名的用户）
        DEFAULT_PASSWORD=ftp // ftp的password
        DEFAULT_BASE_PATH=/home/ftpuser/www/images  // 图片存放根目录
        DEFAULT_FILE_PATH="" //基于base_path的相对路径，默认为空
        NGINX_IMAGE_SERVER_PORT=8081  // 图片服务器的nginx端口
        NGINX_IMAGE_SERVER_PATH=/images/  //当前ftp配置的根目录是/home/ftpuser/www/，所以访问图片的image_server_path就是http://192.168.1.11/images
    ```
	
