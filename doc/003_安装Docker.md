



# 安装Docker

## 安装



1. 安装 `yum-utils`工具包

   ```bash
   sudo yum install -y yum-utils device-mapper-persistent-data lvm2
   ```

   

2. 添加docker的yum源(阿里源)

   ```bash
   sudo yum-config-manager \
       --add-repo \
       http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
   ```

3. 卸载旧版本(如果存在则删除)

   ```bash
   sudo yum -y remove docker docker-common docker-selinux docker-engine
   ```

4. 查看所有版本，并选择指定版本安装

   ```bash
   sudo yum list docker-ce --showduplicates | sort -r
   ```

   

5. 安装docker

   ```bash
   sudo yum install docker-ce # 默认安装最新版本
   ```

   - `$ yum install  docker-ce-<VERSION_STRING> `(指定安装版本)。例：` yum install docker-ce-18.03.1.ce`

   

6. 验证安装成功

   ```bash
   sudo docker run hello-world
   ```

   

7. 启动并加入开机启动

   ```bash
   sudo systemctl start docker       # (重启命令  $  systemctl restart docker ) 
   sudo systemctl enable docker   # 开机启动
   sudo docker version  # 查看docker版本号
   ```

   

8. 将当前用户(此时可以使用blog登录)加入到`docker`用户组

   - 新增`docker`用户组，已存在可忽略

     ```
      sudo groupadd docker
     ```

   - 将用户加入`docker`用户组

     ```
      sudo usermod -aG docker ${USER}
     ```

   - 重启`docker`服务

     ```
     sudo systemctl restart docker
     ```

   - 重新登录用户



## 镜像加速

使用阿里云镜像加速地址： https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors

获取加速地址并按照说明配置到相应配置文件中。