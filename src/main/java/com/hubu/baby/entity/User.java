package com.hubu.baby.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhaokunsong
 * @since 2018-04-18
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目ID
     */
	@TableId(value="user_id", type= IdType.AUTO)
	private Integer userId;
	private String nickname;
	private String picture;
	private Date birthday;
	private BigDecimal weight;
	private BigDecimal hight;
	private String openid;
	private String unionid;
	private String status;
	private Date created;
	private Date updated;


	public Integer getUserId() {
		return userId;
	}

	public User setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getNickname() {
		return nickname;
	}

	public User setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public String getPicture() {
		return picture;
	}

	public User setPicture(String picture) {
		this.picture = picture;
		return this;
	}

	public Date getBirthday() {
		return birthday;
	}

	public User setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public User setWeight(BigDecimal weight) {
		this.weight = weight;
		return this;
	}

	public BigDecimal getHight() {
		return hight;
	}

	public User setHight(BigDecimal hight) {
		this.hight = hight;
		return this;
	}

	public String getOpenid() {
		return openid;
	}

	public User setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getUnionid() {
		return unionid;
	}

	public User setUnionid(String unionid) {
		this.unionid = unionid;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public User setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreated() {
		return created;
	}

	public User setCreated(Date created) {
		this.created = created;
		return this;
	}

	public Date getUpdated() {
		return updated;
	}

	public User setUpdated(Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "User{" +
			", userId=" + userId +
			", nickname=" + nickname +
			", picture=" + picture +
			", birthday=" + birthday +
			", weight=" + weight +
			", hight=" + hight +
			", openid=" + openid +
			", unionid=" + unionid +
			", status=" + status +
			", created=" + created +
			", updated=" + updated +
			"}";
	}
}
