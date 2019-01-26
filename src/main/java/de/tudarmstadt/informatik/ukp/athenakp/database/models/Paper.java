package de.tudarmstadt.informatik.ukp.athenakp.database.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="paper")
public class Paper extends Model {
	/*Identifier*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paperID", updatable = false, nullable = false)
	private long paperID;

	/*Paper's authors*/
	@JsonIgnore //fixes infinite recursion
	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "papers", fetch = FetchType.EAGER)
	private Set<Author> authors = new HashSet<>();
	/*Release date*/
	@Column(name = "releaseDate")
	private LocalDate releaseDate;
	/*Topic of the paper*/
	@Column(name = "topic")
	private String topic;
	/*Title of the paper*/
	@Column(name = "title", columnDefinition = "VARCHAR(1000)") //fixes titles that are too long for being storable in the column
	private String title;
	/*URL to PDF*/
	@Column(name = "href")
	private String href;
	/*PDF filesize in Bytes*/
	@Column(name = "pdfFileSize")
	private int pdfFileSize;
	/*anthology of paper as String*/
	@Column (name = "anthology")
	private String anthology;

	//	Removed all code concerning quotations and alike. Too time consuming right now.

	//	TODO: Metadata?

	/**
	 * Get this paper's ID
	 * @return This paper's ID
	 */
	public long getPaperID(){
		return paperID;
	}

	/**
	 * Gets List of this paper's authors
	 * @return List of this paper's authors
	 */
	public Set<Author> getAuthors() {
		return authors;
	}

	/**
	 * Sets this paper's authors
	 * @param authors The new author of this paper
	 */
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	/**
	 * Adds an author to this paper's author list
	 * @param a The author to add
	 */
	public void addAuthor(Author a) {
		authors.add(a);
	}

	/**
	 * Gets this paper's release date
	 * @return This paper's release date
	 */
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Sets this paper's release date
	 * @param releaseDate The new release date of this paper
	 */
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Gets this papers topic
	 *
	 * @return The topic of this paper
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Sets this institution's topic
	 *
	 * @param topic The new topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Gets this papers title
	 *
	 * @return The title of this paper
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets this institution's title
	 *
	 * @param title The new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the link to this papers PDF file
	 *
	 * @return The link to this papers PDF file
	 */
	public String getHref() {
		return href;
	}

	/**
	 * Sets the link to this papers PDF file
	 *
	 * @param href The new link to this papers PDF file
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * Gets the filesize of this papers PDF file in Bytes
	 *
	 * @return The filesize of this papers PDF file in Bytes
	 */
	public int getPdfFileSize() {
		return pdfFileSize;
	}

	/**
	 * Sets the filesize of this papers PDF file in Bytes
	 *
	 * @param pdfFileSize The new filesize of this papers PDF file in Bytes
	 */
	public void setPdfFileSize(int pdfFileSize) {
		this.pdfFileSize = pdfFileSize;
	}

	/**
	 * Gets the paper's anthology
	 * @return the paper's anthology
	 */
	public String getAnthology() {
		return anthology;
	}

	/**
	 * Sets the paper's anthology
	 * @param anthology anthology of the paper as String
	 */
	public void setAnthology(String anthology) {
		this.anthology = anthology;
	}
}
