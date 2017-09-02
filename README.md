# BruceCoreLib 基本封装库开始
BruceCoreLib 使用
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```
## 同时请加上<br>
```
dependencies {
	        compile 'com.github.weileng11:BruceTestDemo:v1.0.0'
	}

```
====
BruceCoreLib 整体介绍
-------
* 1.Base                     所有的activity，fragment都继承该类
* 2.callback                 封装的接口类
* 3.database                 一.greendao数据库封装，二.ormlite 数据库封装， 三.realm 数据库封装
* 4.dialog                   常用的dialog类
* 5.imagepicker              仿微信选择图片
* 6.net                      okhttp3网络请求部分
* 7.utils                    常用的工具类，banner首页轮播图片
* 8.view                     常用的视图
* 9.updateapk                更新apk 一.dialog方式 二.notification方式
* 10.imagewatcher             仿微信朋友圈功能，测试还没有成功，有可能要删除
*  同时加入了不带rv的仿微信朋友圈和带rv仿微信朋友圈 详情见testdemo
* 说明：使用见Explain类

## bruce下一些基本如何使用介绍
* 1.引导界面  [引导界面](https://github.com/bingoogolapple/BGABanner-Android#%E6%89%93%E8%B5%8F%E6%94%AF%E6%8C%81)
* 2.banner 首页轮播  [首页轮播](https://github.com/youth5201314/banner)
* 3.imagepick 选择图片  [选择图片](https://github.com/jeasonlzy/ImagePicker)
* 4.database   暂时没有
* 5.utils文件下的uploadhead上传头像封装  下载地址： [单个图片上传](https://github.com/wsy858/android-headimage-cliper)<br>
* 6.updateapk 目录下dialog+notification    更新Apk: [更新Apk](https://github.com/feicien/android-auto-update)<br>
* RxJava方式更新apk  [RxJava方式更新apk](https://github.com/guoyoujin/MyUpdateApk)<br>
* 7.ExpandableTextView [ExpandableTextView 文本展开](https://github.com/search?utf8=✓&q=ExpandableTextView&type=)<br>
        不足:数据库还要封装一下<br>

## bruce 一些效果图 <br>
引导页:<br>
![screenshot](https://github.com/weileng11/BruceTestDemo/blob/master/img/yd1.png)
![screenshot](https://github.com/weileng11/BruceTestDemo/blob/master/img/yd2.png)



## License


```
Copyright 2017 trycatch

Licensed under the Apache License, Version 1.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```