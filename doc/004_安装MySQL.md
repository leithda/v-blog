

# 安装MySQL



## 方式一_本地安装

-  查询并卸载系统自带的Mariadb

```bash
rpm -qa | grep mariadb
rpm -e --nodeps {查询到的文件名}
```



- 下载安装文件

```bash
sudo wget -O ~/install/mysql-community-server-5.7.33-1.el7.x86_64.rpm https://downloads.mysql.com/archives/get/p/23/file/mysql-community-server-5.7.33-1.el7.x86_64.rpm 
```



- 创建数据存储文件夹

```bash
mkdir -p ~/data/mysql/data
```



- 编辑配置文件,如无新增, `vi /etc/my.cnf`

```properties
[client]
port = 3306
socket = /tmp/mysql.sock
[mysqld]
character_set_server=utf8
init_connect='SET NAMES utf8'
basedir=/usr/local/mysql
# 指定为刚才创建的数据存储文件夹
datadir=/home/blog/data/mysql/data
socket=/tmp/mysql.sock
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid
#不区分大小写
lower_case_table_names = 1
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
max_connections=5000
default-time_zone = '+8:00'
```



- 安装MySQL服务

```bash
/usr/local/mysql/bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/home/blog/data/mysql/data --lc_messages_dir=/usr/local/mysql/share --lc_messages=en_US
```



- 查看初始密码

```bash
cat /var/log/mysqld.log

# 查找A temporary password is generated for root@localhost: xxx
# 其中xxx为初始密码
```



- 启动MySQL

```bash
/usr/local/mysql/support-files/mysql.server start
```



- 登录MySQL

```bash
mysql -u root -p {密码}
```



- 修改密码

```bash
set password=password(‘blog123’);
```



- 开启远程连接

```bash
UPDATE mysql.user SET Host = '%', User = 'root'  WHERE (Host = 'localhost')  AND  (User = 'root');
FLUSH PRIVILEGES;
然后exit;退出mysql
```



- 设置开机自启动

```bash
cd /usr/local/mysql/support-files &&  cp mysql.server /etc/init.d/mysqld  &&  chkconfig --add mysqld
```



- 重启MySQL

```bash
service mysqld restart
```



## 方式二_Docker安装(推荐)

> 由于本地安装的复杂性，推荐使用Docker安装，其中Docker安装MySQL的缺陷请查看：https://blog.csdn.net/github_38592071/article/details/107328900



1. 安装docker(已完成)

   

2. 查看可用的MySQL版本

   [Docker-MySQL版本](https://hub.docker.com/_/mysql?tab=tags)

3. 拉取docker镜像（可以直接使用第7步操作进行拉取）

   ```
   docker pull mysql:5.7
   # docker pull mysql:{tag}
   # 如 docker pull mysql:5.7
   # docker pull mysql 会默认拉取最新版本
   ```

4. 查看是否拉取成功

   ```
   docker images
   ```

5. 创建本地数据目录及复制MySQL的配置文件

   ```
   mkdir -p /home/blog/data/mysql/data /home/blog/data/mysql/logs /home/blog/data/mysql/conf
   ```

   ```
   ├── mysql
   │   ├── conf # 拷贝自MySQL
   │   │   ├── docker.cnf
   │   │   ├── mysql.cnf
   │   │   └── mysqldump.cnf
   │   ├── data
   │   └── logs
   ```

   - 配置文件挂载到服务器主要为了修改方便，**配置文件可以通过如下方式获取**.

   ```bash
   # 启动获取配置文件的docker容器
   $ docker run -p 23306:3306 --name mysql_config -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
   
   # 查看容器是否启动
   $ docker ps
   CONTAINER ID   IMAGE       COMMAND                  CREATED          STATUS          PORTS                                                    NAMES
   e454f36eb8c5   mysql:5.7   "docker-entrypoint.s…"   26 seconds ago   Up 24 seconds   33060/tcp, 0.0.0.0:23306->3306/tcp, :::23306->3306/tcp   mysql_config
   
   # 进入容器，查看配置文件
   $ docker exec -it mysql_config /bin/bash
   $ cd /etc/mysql/conf.d/
   $ ls -rlt
   total 12
   -rw-r--r-- 1 root root 55 Aug  3  2016 mysqldump.cnf
   -rw-r--r-- 1 root root  8 Aug  3  2016 mysql.cnf
   -rw-r--r-- 1 root root 43 Apr 19 18:57 docker.cnf
   
   $ exit # 退出容器
   
   # 拷贝容器中文件到本机， `.` 表示拷贝到当前目录
   $ docker cp mysql_config:/etc/mysql/conf.d/ .
   
   # 查看拷贝结果
   $ ls
   conf.d  data  front  install_package  java  middle
   
   # 将conf.d中文件拷贝到指定的文件夹即可
   $ mv conf.d/* ~/data/mysql/conf
   
   # 关闭容器并删除
   $ docker stop mysql_config
   $ docker rm mysql_config
   ```

6. 启动脚本

   ```bash
   docker run -p 3306:3306 --name mysql -v /home/dev/data/mysql/conf:/etc/mysql/conf.d -v /home/dev/data/mysql/logs:/logs -v /home/dev/data/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=blog123 -d mysql:5.7
   ```

   
