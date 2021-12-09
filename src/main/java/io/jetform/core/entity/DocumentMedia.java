package io.jetform.core.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="tbl_document")
public class DocumentMedia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/*
	 * @Column(name="file")
	 * 
	 * @Basic(fetch=FetchType.LAZY)
	 * 
	 * @Lob private byte[] document;
	 */

	@Column(name="fileName")
	private String name;
	
	@Column(name="filePath")
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name="fileSize")
	private long size;

	@Column(name="fileType")
	private String contentType;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {                                                                                                                                                                       
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getSize() {
		return size;
	}
	
	public void setSize(long size) {
		this.size = size;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "DocumentMedia [id=" + id + ", name=" + name + ", filePath=" + filePath + ", size=" + size
				+ ", contentType=" + contentType + "]";
	}
	
	
	/*
	 * public byte[] getDocument() { return document; }
	 * 
	 * public void setDocument(byte[] document) { this.document = document; }
	 */

	/*
	 * @Override public String toString() { return "DocumentMedia [id=" + id +
	 * ", document=" + document + "]"; }
	 */
}