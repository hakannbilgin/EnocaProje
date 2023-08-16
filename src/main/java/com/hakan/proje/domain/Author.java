package com.hakan.proje.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "author")
@Table(name = "author")
public class Author {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authorid")
	private long authorId;
	
	@Column(length = 200, nullable = false, name = "full_name")
	private String fullName;

	public Author(long authorId, String fullName) {
		super();
		this.authorId = authorId;
		this.fullName = fullName;
	}
public Author() {
		
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorId, fullName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return authorId == other.authorId && Objects.equals(fullName, other.fullName);
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", fullName=" + fullName + "]";
	}
	
	
	
}
