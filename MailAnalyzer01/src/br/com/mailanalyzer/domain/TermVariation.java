package br.com.mailanalyzer.domain;

import java.io.Serializable;
import javax.persistence.Entity;


/**
 * 
 *  @author Guilherme Lucas
 * 
 */
@Entity
//@Table(name="termvariation",catalog="mailanalyzer")
public class TermVariation extends DomainObject implements Serializable {
 
	private static final long serialVersionUID = 1L;
	 
	private String name;
	 
	private String variations;
	 
	private String replacer;
	 
	/**
	 *  @return the name
	 * 
	 */
	public String getName() {
		return null;
	}
	 
	/**
	 *  @param name the name to set
	 * 
	 */
	public void setName(String name) {
	 
	}
	 
	/**
	 *  @return the variations
	 * 
	 */
	public String getVariations() {
		return null;
	}
	 
	/**
	 *  @param variations the variations to set
	 * 
	 */
	public void setVariations(String variations) {
	 
	}
	 
	/**
	 *  @return the replacer
	 * 
	 */
	public String getReplacer() {
		return null;
	}
	 
	/**
	 *  @param replacer the replacer to set
	 * 
	 */
	public void setReplacer(String replacer) {
	 
	}
	 
}
 
