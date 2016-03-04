#javaWeb开源框架 spinach
## 简介
spinach是基于多个优秀的开源项目，高度整合封装而成的高效，高性能，强安全性的开源Java EE快速开发平台。

集结最新主流时尚开源技术的面向互联网Web应用的基础开发框架，提供一个J2EE相关主流开源技术架构整合及一些企业应用基础通用功能和组件的设计实现的最佳实践和原型参考。

## 技术选型

1、后端

* 核心框架：Spring Framework 4.1.2.RELEASE
* 安全框架：Apache Shiro 1.2.3
* 服务端验证：Hibernate Validator 5.1.2.Final
* 任务调度：quartz 2.2.1
* 持久层框架：MyBatis 3.2.8(使用[MyBatis通用Mapper](https://github.com/abel533/Mapper)实现简单的sql)
* 数据库连接池：Alibaba Druid 1.0
* 缓存框架：Ehcache、Redis
* 日志管理：SLF4J 1.7、logback
* 工具类：Apache Commons、Jackson 2.2、Xstream 1.4

2、前端
参考[H-ui框架](http://www.h-ui.net/)实现

*  jquery			jQuery类库（v1.9.1）
*  bootstrapSwitch		开关控件
*  Hui-iconfont		        阿里图标字体库（H-ui定制 _v1.0.6）
*  icheck			单选框、复选框控件
*  laypage			laypage 翻页插件
*  layer			layer弹出层插件
*  laytpl			JavaScript模板引擎
*  My97DatePicker		日期插件
*  Validform			表单验证插件
*  AngularJS			前端框架(v1.4.5)


3、平台
* JDK版本:支持jdk1.6及以上
* web容器：支持Tomcat 6、Jboss 7、WebLogic 10、WebSphere 8、jetty等,建议使用jetty运行。
* 数据库支持：目前仅提供Oracle和mysql数据库的支持。
* 开发工具: eclipse、Intellij idea

4、已实现的功能

后台:权限管理(已实现,)

前台:CMS(开发中...)

## 开发部署
1. 打开开发工具,checkout项目http://git.oschina.net/1231/spinach

2. maven编译项目,等待maven下载jar包

3. 在数据库中运行  /sql/demo.sql文件创建数据库和表

4. 修改resource下 resources.properties数据库等配置

    1)  如想切换到集群环境可以修改resource下配置文件:/src/main/resources/shiro/spring-shiro.xml

    2)  将shiro的缓存从ehcache改为redis(请先安装好redis环境并修改配置)

5. 由于项目前台采用AngularJS+html实现,项目发布后不要使用项目名,避免有些资源访问不到.

	前台地址:http://localhost:8080
	
	后台地址: http://localhost:8080/admin

6.代码生成工具:tools/gencode.zip.
    具体使用方法见解压文件中readme.txt

7. 部署中可能还会碰到很多问题，请加入QQ群：21596283

## 交流、反馈

* QQ 群: 21596283
* E-mail：yangbo@live.cn
* GitHub：https://github.com/yangb0/spinach
* OSChina：<http://git.oschina.net/1231/spinach>
* 捐赠支持（支付宝）：yangbo@live.cn

个人能力有限,希望大神多多指点。

怎么共享我的代码：[手把手教你如何加入到github的开源世界！](http://www.cnblogs.com/wenber/p/3630921.html)

## 版权声明

本软件使用 [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) 协议，请严格遵照协议内容：

1. 需要给代码的用户一份Apache Licence。
2. 如果你修改了代码，需要在被修改的文件中说明。
3. **在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。**
4. 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。
3. Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售