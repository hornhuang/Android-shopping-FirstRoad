package com.example.firstroad.classes;

import android.graphics.Bitmap;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {

    private String flag;// 0：普通用户 1：商家
    private Bitmap userIcon;
    private String nickname;
    private String motto;
    private BmobFile imageFile;

    public User(){

    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Bitmap getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(Bitmap userIcon) {
        this.userIcon = userIcon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public BmobFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(BmobFile imageFile) {
        this.imageFile = imageFile;
    }
}
