package org._2ndelement.autorunner.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 代跑用户表
 * @TableName proxy_user
 */
@TableName(value ="proxy_user")
@Data
public class ProxyUser implements Serializable {
    /**
     * 代跑用户id
     */
    @TableId
    private Object id;

    /**
     * 代跑用户名
     */
    private String proxyUsername;

    /**
     * 代跑用户密码
     */
    private String proxyPassword;

    /**
     * 代跑用户验证状态
     */
    private Integer verifyStatus;

    /**
     * 所属用户id
     */
    private Object userId;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProxyUser other = (ProxyUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProxyUsername() == null ? other.getProxyUsername() == null : this.getProxyUsername().equals(other.getProxyUsername()))
            && (this.getProxyPassword() == null ? other.getProxyPassword() == null : this.getProxyPassword().equals(other.getProxyPassword()))
            && (this.getVerifyStatus() == null ? other.getVerifyStatus() == null : this.getVerifyStatus().equals(other.getVerifyStatus()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProxyUsername() == null) ? 0 : getProxyUsername().hashCode());
        result = prime * result + ((getProxyPassword() == null) ? 0 : getProxyPassword().hashCode());
        result = prime * result + ((getVerifyStatus() == null) ? 0 : getVerifyStatus().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", proxyUsername=").append(proxyUsername);
        sb.append(", proxyPassword=").append(proxyPassword);
        sb.append(", verifyStatus=").append(verifyStatus);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}