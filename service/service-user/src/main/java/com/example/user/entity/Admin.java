package com.example.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;


/**
 * @author xzwnp
 */
@ApiModel(value = "",description = "")
@TableName("admin")
public class Admin implements Serializable,Cloneable{
    /**  */
    @ApiModelProperty(name = "",notes = "null")
    @TableId
    private Integer adminid ;
    /** 管理员名称 */
    @ApiModelProperty(name = "管理员名称",notes = "")
    private String adminname ;
    /**  */
    @ApiModelProperty(name = "",notes = "null")
    private String password ;

    /**  */
    public Integer getAdminid(){
        return this.adminid;
    }
    /**  */
    public void setAdminid(Integer adminid){
        this.adminid=adminid;
    }
    /** 管理员名称 */
    public String getAdminname(){
        return this.adminname;
    }
    /** 管理员名称 */
    public void setAdminname(String adminname){
        this.adminname=adminname;
    }
    /**  */
    public String getPassword(){
        return this.password;
    }
    /**  */
    public void setPassword(String password){
        this.password=password;
    }
}