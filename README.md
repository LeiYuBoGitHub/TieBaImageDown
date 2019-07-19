# TieBaImageDown
百度贴吧图片下载器,根据需求下载贴吧的图片


#### 功能
放入百度贴吧帖子地址,即可下载帖子中的图片,默认全部,可通过下载页数控制下载量

#### 演示

```
public static void main(String[] args) {
        TieBaImageBuilder.custom()
                //帖子地址
                .url("https://tieba.baidu.com/p/6170218305")
                //下载页数
                .countPage(1)
                .build();
    }
```

#### 更改图片保存地址
在`src/main/resources/`文件夹下的`setting.json` 更改`savePath`