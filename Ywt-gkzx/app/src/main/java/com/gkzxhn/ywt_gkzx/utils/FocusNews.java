package com.gkzxhn.ywt_gkzx.utils;

/**
 * Created by ZengWenZhi on 2016/8/29 0029.
 *焦点关注的条目的信息类
 *包括条目中的图片、标题、和信息详情缩略
 */

public class FocusNews {
    public String title;
    public int image;
    public String content;

    public FocusNews(String title, int image, String content) {
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
