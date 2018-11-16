package de.tudarmstadt.informatik.ukp.athenakp.database.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "author")
public class Author extends Person {

	/*Written papers*/
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "author_paper",
			joinColumns = { @JoinColumn(name = "authorID") },
			inverseJoinColumns = { @JoinColumn(name = "paperID") }
	)
	private Set<Paper> papers = new HashSet<>();


	/**
	 * Gets the papers this author has written
	 * @return The papers this author has written
	 */
	public Set<Paper> getPapers()
	{
		return papers;
	}



}