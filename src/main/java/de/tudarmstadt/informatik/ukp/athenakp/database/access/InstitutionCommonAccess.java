package de.tudarmstadt.informatik.ukp.athenakp.database.access;

import java.util.List;

import de.tudarmstadt.informatik.ukp.athenakp.database.models.Institution;

/**
 * @author Daniel Lehmann
 */
public interface InstitutionCommonAccess extends ICommonAccess<Institution>
{
	/**
	 * Get all institutions with specified institution id.
	 * @param name The institution's id.
	 * @return A List of all institutions with the specified institution id.
	 */
	public List<Institution> getByInstitutionID(Long id);

	/**
	 * Get all institutions with specified name.
	 * @param name The institution's name.
	 * @return A List of all institutions with the specified institution name.
	 */
	public List<Institution> getByName(String name);

	/**
	 * Get all institutions with institution person.
	 * @param author The institution's person.
	 * @return A List of all institutions with the specified person.
	 */
	public List<Institution> getByPerson(String person);
}
