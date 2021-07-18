package com.zfg.learn.model.po;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


/**
 * 动态
 */
public class Dynamic {
    @ApiModelProperty("动态id")
    private Long id;

    @ApiModelProperty("作者名")
    private String authorName;

    @ApiModelProperty("作者Id")
    private Long authorId;

    @ApiModelProperty("链接")
    private String url;

    @ApiModelProperty("动态内容")
    private String content;

    @ApiModelProperty("动态内容")
    private DynamicStat stat;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("创建时间")
    private Integer ctime;

    @ApiModelProperty("时间日期格式")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DynamicStat getStat() {
        return stat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStat(DynamicStat stat) {
        this.stat = stat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
