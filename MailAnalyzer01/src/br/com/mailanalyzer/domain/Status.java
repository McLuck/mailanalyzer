
package br.com.mailanalyzer.domain;
/**
 *
 * @author McLuck
 */
public class Status extends DomainObject{
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
