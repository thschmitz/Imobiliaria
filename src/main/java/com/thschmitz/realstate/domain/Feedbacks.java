package com.thschmitz.realstate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Feedbacks")
public class Feedbacks implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date created_at;
	private Integer author_id;
	private Integer post_id;
	
	public Feedbacks() {
		
	}
	
	public Feedbacks(Date created_at, Integer author_id, Integer post_id) {
		this.created_at = created_at;
		this.author_id = author_id;
		this.post_id = post_id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Integer getAuthor() {
		return author_id;
	}

	public void setAuthor(Integer author_id) {
		this.author_id = author_id;
	}

	public Integer getPost() {
		return post_id;
	}

	public void setPost(Integer post_id) {
		this.post_id = post_id;
	}
	
	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "LikeDTO [created_at=" + created_at + ", author_id=" + author_id + "]";
	}

	public Boolean isEmpty() {
		if(this.id != null & this.created_at != null & this.author_id != null & this.post_id != null) {
			return false;
		} else {
			return true;
		}
	}
}
