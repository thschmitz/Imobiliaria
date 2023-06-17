package com.thschmitz.realstate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name="PostsImages", uniqueConstraints = {@UniqueConstraint(columnNames={"id"})})
public class PostImage implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date created_at;
	private String image_url;
	private Integer post_id;
	
	/*
	 * @NotNull me gera um excepção na hora de salvar no banco de dados -> Posso tratá-la com try/catch
	 * @Column(name="post_id")
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * @JoinColumn(name="post.id")
	 * private Post post
	 */
	
	// Adicionar novas anotações
	// @NotNull @Column(lenght=50) @Column(name="{name}")
	
	public PostImage() {
		
	}
	
	public PostImage(Integer id, Date created_at, String image_url, Integer post_id) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.setImage_url(image_url);
		this.post_id = post_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Integer getPostId() {
		return post_id;
	}

	public void setPostId(Integer post_id) {
		this.post_id = post_id;
	}
	
	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostImage other = (PostImage) obj;
		return Objects.equals(id, other.id);
	}

	public Boolean isEmpty() {
		if(this.created_at != null && this.image_url != "" && this.image_url != null && this.post_id != null) {
			return false;
		} else {
			return true;
		}
	}
}
