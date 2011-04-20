package br.com.mailanalyzer.domain;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name="ofield")
public class Field extends DomainObject {

	private String name;
	private int type;
	private boolean required;
	public static final int STRING = 0;
	public static final int INTEGER = 1;
	public static final int DATE_TIME = 2;
	public static final int DOUBLE = 3;
	 /**
	 *  @return the name
	 *
	 */
	 public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * @return the required
     */
    public boolean isRequired()
    {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required)
    {
        this.required = required;
    }
}
