#签名
FROM debian:wheezy
MAINTAINER yangbo <yangbo@live.cn>

# 更新源，安装ssh server
#apt
#ADD 02proxy /etc/apt/apt.conf.d/02proxy
RUN apt-get update
RUN apt-get upgrade -y

#package
RUN apt-get install -y vim \
 aptitude squirrelmail-locales squirrelmail apache2 htop w3m

#ssh
RUN apt-get install -y openssh-server
RUN mkdir /var/run/sshd/
RUN mkdir /root/.ssh
#ADD authorized_keys /root/.ssh/authorized_keys
RUN perl -i -ple 's/^(permitrootlogin\s)(.*)/\1yes/i' /etc/ssh/sshd_config

# 设置root ssh远程登录密码为123456
RUN echo "root:123456" | chpasswd 

# 添加orache java7源，一次性安装vim，wget，curl，java7，tomcat7,maven等必备软件
RUN apt-get install -y vim wget curl oracle-java7-installer tomcat7
RUN apt-get install -y maven

# 设置JAVA_HOME环境变量
RUN update-alternatives --display java
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/environment
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/default/tomcat7

#mysql
RUN yum install -y  mysql-devel MySQL-python
run --name mysql-ct -e MYSQL_ROOT_PASSWORD=123456 -d -p 3306:3306 mysql:5.5
exec -it mysql-ct bash
mysql -uroot -p123456

# 容器需要开放SSH 22端口
EXPOSE 22

# 容器需要开放Tomcat 8080端口
EXPOSE 8080

#下载项目
RUN git clone -b stable http://git.oschina.net/1231/spinach /opt/spinach
#权限
RUN  chmod +x /opt/spinach
RUN /opt/spinach/sql/demo.sql

ADD pom.xml /opt/spinach/pom.xml
RUN ["mvn", "clean install"]

# 设置Tomcat7初始化运行，SSH终端服务器作为后台运行
ENTRYPOINT service tomcat7 start && /opt/spinach/target/spinach.war -D
