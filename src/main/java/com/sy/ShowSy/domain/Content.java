package com.sy.ShowSy.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * <p>Title: Content</p>
 * <p>Description: auto generated </p>
 * <p>Company: 湖州双翼信息技术有限公司</p>
 * @author AlbertZhang
 * @date	2017-11-06 14:41:56
 * @version 1.0
 */
@Entity
@Table(name="t_content")
public class Content implements EntityBase {
	private static final long serialVersionUID = 4961115982468162243L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String thumb;
	/**
	 * 
	 */
	private String contents;
	/**
	 * 
	 */
	private String tags;
	/**
	 * 
	 */
	private String type;
	/**
	 * 
	 */
	private Long status;
	/**
	 * 
	 */
	private Long hits;
	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
	public void setThumb(String thumb){
		this.thumb = thumb;
	}
	public String getThumb(){
		return thumb;
	}
	public void setContents(String contents){
		this.contents = contents;
	}
	public String getContents(){
		return contents;
	}
	public void setTags(String tags){
		this.tags = tags;
	}
	public String getTags(){
		return tags;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setStatus(Long status){
		this.status = status;
	}
	public Long getStatus(){
		return status;
	}
	public void setHits(Long hits){
		this.hits = hits;
	}
	public Long getHits(){
		return hits;
	}
}