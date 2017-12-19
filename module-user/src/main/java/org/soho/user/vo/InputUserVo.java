package org.soho.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhuozl on 17-2-24.
 */
@ApiModel(value = "输入对象")
public class InputUserVo {

    @ApiModelProperty(value = "ID") private String id;
    @ApiModelProperty(value = "keyword") private String keyword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}