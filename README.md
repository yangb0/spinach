#javaWeb开源框架 spinach
## 简介
spinach是基于多个优秀的开源项目，高度整合封装而成的高效，高性能，强安全性的开源Java EE快速开发平台。
集结最新主流时尚开源技术的面向互联网Web应用的基础开发框架，提供一个J2EE相关主流开源技术架构整合及一些企业应用基础通用功能和组件的设计实现的最佳实践和原型参考。

## 技术选型

1、后端

* 核心框架：Spring Framework 4.0.0
* 安全框架：Apache Shiro 1.2.3
* 视图框架：Spring MVC 4.0.0
* 服务端验证：Hibernate Validator 5.1.2
* 任务调度：quartz 2.2.1
* 持久层框架：MyBatis 3.1.1
* 数据库连接池：Alibaba Druid 1.0
* 缓存框架：Ehcache、Redis
* 日志管理：SLF4J 1.7、Log4j
* 工具类：Apache Commons、Jackson 2.2、Xstream 1.4、POI 3.9

2、前端

* JS框架：jQuery 1.9。
* CSS框架：Twitter Bootstrap 2.3.1。
* 客户端验证：JQuery Validation Plugin 1.11。
* 日期控件： My97DatePicker
* 模板引擎:FreeMarker

3、平台
* JDK版本:支持jdk1.6及以上
* web容器：支持Tomcat 6、Jboss 7、WebLogic 10、WebSphere 8、jetty等,建议使用jetty运行。
* 数据库支持：目前仅提供Oracle和mysql数据库的支持。
* 开发环境：Java EE、Eclipse、Maven、Git

## 部署开发环境
1. 下载 [Eclipse IDE for Java EE Developers](http://eclipse.org/downloads/)
2. 打开eclpse,导入师说CMS
3. File -> Import -> Git -> Projects from Git -> Clone URI
4. 然后在URI输入：http://git.oschina.net/1231/spinach
5. 等待eclipse自动下载jar包
6. 在数据库中运行  /sql/demo.sql文件创建数据库和表
7. 部署中可能还会碰到很多问题，请加入QQ群：21596283

## 交流、反馈

* QQ 群: 21596283
* E-mail：yangbo@live.cn
* GitHub：
* OSChina：<http://git.oschina.net/1231/spinach>
* 捐赠支持（支付宝）：yangbo@live.cn

一个人的个人能力再强，也无法战胜一个团队，希望兄弟姐妹的支持，能够贡献出自己的部分代码，参与进来共同完善它(^_^)。

怎么共享我的代码：[手把手教你如何加入到github的开源世界！](http://www.cnblogs.com/wenber/p/3630921.html)

## 版权声明

本软件使用 [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) 协议，请严格遵照协议内容：

1. 需要给代码的用户一份Apache Licence。
2. 如果你修改了代码，需要在被修改的文件中说明。
3. **在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。**
4. 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。
3. Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售
