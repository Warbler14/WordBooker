package com.lotus.jewel.booker.word.model;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lotus.jewel.booker.model.PagingForSqlite;

public class WordVto extends PagingForSqlite implements Serializable{

	@JsonIgnore
	private static final long serialVersionUID = -6360119559242183023L;

	private String word;
	
	private String updateWord;
	
	private Character header;
	
	private String wordType;
	
	private String description;
	
	private Integer lank;
	
	private Integer difficulty;
	
	private Integer groupId;
	
	private String registDatetime;
	
	private String modifyDatetime;

	public WordVto() {
		super();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word.toLowerCase();
		setHeader();
	}
	
	public String getUpdateWord() {
		return updateWord;
	}

	public void setUpdateWord(String updateWord) {
		this.updateWord = updateWord;
	}

	public Character getHeader() {
		return header;
	}

	public void setHeader(Character header) {
		this.header = Character.toUpperCase(header);
	}
	
	public void setHeader() {
		if(StringUtils.hasLength(word)) {
			header = Character.toUpperCase(word.charAt(0));
		}
	}

	public String getWordType() {
		return wordType;
	}

	public void setWordType(String wordType) {
		this.wordType = wordType;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLank() {
		return lank;
	}

	public void setLank(Integer lank) {
		this.lank = lank;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getRegistDatetime() {
		return registDatetime;
	}

	public void setRegistDatetime(String registDatetime) {
		this.registDatetime = registDatetime;
	}

	public String getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(String modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}
	
	@JsonIgnore
	public WordVto getThis() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WordVto [word=");
		builder.append(word);
		builder.append(", updateWord=");
		builder.append(updateWord);
		builder.append(", header=");
		builder.append(header);
		builder.append(", wordType=");
		builder.append(wordType);
		builder.append(", description=");
		builder.append(description);
		builder.append(", lank=");
		builder.append(lank);
		builder.append(", difficulty=");
		builder.append(difficulty);
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append(", registDatetime=");
		builder.append(registDatetime);
		builder.append(", modifyDatetime=");
		builder.append(modifyDatetime);
		builder.append("]");
		return builder.toString();
	}

}
