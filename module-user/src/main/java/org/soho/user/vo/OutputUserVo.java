package org.soho.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by zhuozl on 17-2-24.
 */
@ApiModel(value = "输出对象")
public class OutputUserVo {

    @ApiModelProperty(value = "ID")
    private String id;
    @ApiModelProperty(value = "名字")
    private String name;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
