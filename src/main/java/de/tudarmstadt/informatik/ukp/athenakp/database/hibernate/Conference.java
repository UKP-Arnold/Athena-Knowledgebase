package de.tudarmstadt.informatik.ukp.athenakp.database.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="conference")
public class Conference
{
	/*First day of conference*/
	private Date startDate;
	/*Last day of conference*/
	private Date endDate;
	/*Shown papers*/
	private Set<Paper> papers = new HashSet<Paper>();
	/*Authors that talked*/
	private Set<Author> authors = new HashSet<Author>();
	//TODO: Workshops? Other data?

	/**
	 * Gets the date of the day this conference started
	 * @return The date of the day this conference started
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * Sets the date of the day this conference started
	 * @param startDate The new start date
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * Gets the date of the day this conference ended
	 * @return The date of the day this conference ended
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the date of the day this conference ended
	 * @param endDate The new end date
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * Gets the papers that were shown at this conference
	 * @return The papers that were shown at this conference
	 */
	@ManyToMany
	@Column(name="papers")
	public Set<Paper> getPapers()
	{
		return papers;
	}

	/**
	 * Gets the authors that talked at this conference
	 * @return The authors that talked at this conference
	 */
	@ManyToMany
	@Column(name="authors")
	public Set<Author> getAuthors()
	{
		return authors;
	}
}
