package com.lyb.core;

import com.lyb.util.FileUtil;
import com.lyb.util.HttpUtil;
import com.lyb.util.JsonUtil;
import com.lyb.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/18 16:24
 * @Description:
 */
public class TieBaImageBuilder implements TieBaImage{

    private String url;

    private String savePath;

    private Integer countPage = null;

    public static TieBaImage custom() {
        return new TieBaImageBuilder();
    }

    public TieBaImage url(String url) {
        this.url = url;
        return this;
    }

    public TieBaImage countPage(Integer countPage) {
        this.countPage = countPage;
        return this;
    }

    public void build() {

        //加载配置
        loadSetting();

        //获取帖子ID
        String id = getId(url);

        String html = HttpUtil.httpGet(url);

        //计算或者取用户设置的页数
        Integer size;
        if (countPage == null) {
            size = countPage(html);
        }else{
            size = countPage;
        }

        if (size > 1) {
            for (int i = 0; i < size;i++) {
                url = url + "?pn=" + i + 1;
                html = HttpUtil.httpGet(url);
                downImage(html,id);
            }
        }else{
            downImage(html,id);
        }
    }

    private Integer countPage(String html) {
        Document doc = Jsoup.parse(html);
        System.out.println(doc);
        String countPage = doc.select(".pb_footer").select(".l_reply_num").get(0).select(".red").get(1).text();
        System.out.println(countPage);
        return Integer.valueOf(countPage);
    }

    private void downImage(String html,String id) {
        if (StringUtil.isNotBlank(html)) {
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select(".left_section").select(".p_postlist").select(".d_post_content_main").select("img");

            List<String> uu = new ArrayList<>();
            for (Element e : elements) {
                String url = e.attr("src");
                uu.add(url);
            }

            for (String s : uu) {

                //image_emoticon 屏蔽表情
                if (s.contains("https") && !s.contains("image_emoticon")) {
                    String name = s.substring(s.lastIndexOf("/")+1);
                    if (name.contains(".jpg") || name.contains(".png") || name.contains(".gif")) {
                        HttpUtil.addFile(s,savePath + "/" + id + "/",name);
                    }
                }

            }
        }
    }

    private void loadSetting() {
        String json = FileUtil.readText(new File("src/main/resources/setting.json"),"UTF-8");
        this.savePath = JsonUtil.getValue(json,"savePath");
    }

    /**
     * 从URL中获取ID
     */
    private String getId(String url) {
        String id = StringUtil.cutting(url,"p/","r");
        if (id != null) {
            //精品贴
            if (id.contains("fr=good")){
                return StringUtil.cutting(id,"?fr=good","l");
            }else if (id.contains("red_tag")) {
                return StringUtil.cutting(id,"?red_tag","l");
            }else{
                return id;
            }
        }

        System.out.println("警告,未能获取正确的ID,已创建默认文件夹");
        return "default";
    }

}
