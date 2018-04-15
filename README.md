# SpringBoot-veems
##### 技术选型 
###### 后台选型
* SpringBoot 1.5.9.RELEASE
* mybatis 1.3.2
* 通用Mapper 2.0.1
* Shiro
* logback
* druid

###### 前台选型
* AdminLTE
* Freemarker
* Bootstrap
* Echarts
* Jquery


##### 结构
*  common中包含帮助类
*  web中包含基本模块（权限、角色、用户）
*  resources\static\assets\ 静态资源
*  resources\static\webjars\ 通过http://www.webjars.org/添加maven jar的外部资源
*  resources\static\assets\plugins 外部引用js、css
*  resources\static\assets\js 项目中模块定制的js，按照功能模块划分
*  resources\static\assets\css 项目中模块定制的css，按照功能模块划分
*  resources\web\bzh 项目中模块ftl，按照功能模块划分


##### 测试地址
* 初始化sql：biaozhunhua_init.sql
*  Bootstrap：http://localhost:8080/test/bootstrap
*  echarts：http://localhost:8080/test/echarts
*  Druid:http://localhost:8080/druid/   用户名:admin 密码:admin 
*  shiro 账号登录测试：http://localhost:8080/login  账号lisi 密码111111
*  完成模块：用户管理、角色管理、权限管理
*  演示图片：
![输入图片说明](https://github.com/luomouren/veems/blob/master/web/src/main/resources/static/assets/img/screenshot/login.png "登录")
![输入图片说明](https://github.com/luomouren/veems/blob/master/web/src/main/resources/static/assets/img/screenshot/home.png "主页")
![输入图片说明](https://github.com/luomouren/veems/blob/master/web/src/main/resources/static/assets/img/screenshot/user.png "用户")
![输入图片说明](https://github.com/luomouren/veems/blob/master/web/src/main/resources/static/assets/img/screenshot/role.png "角色")
![输入图片说明](https://github.com/luomouren/veems/blob/master/web/src/main/resources/static/assets/img/screenshot/permission.png "权限")