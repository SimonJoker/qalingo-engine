/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.7.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2013
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.core.web.mvc.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * 
 */
public class TaxForm {

	private String id;
    private int version;
	private String code;
	private String name;
	private String description;
    private String percent;
    
    public String getId() {
	    return id;
    }
    
    public void setId(String id) {
	    this.id = id;
    }
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
	@NotEmpty(message = "bo.tax.error_form_code_empty")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotEmpty(message = "bo.tax.error_form_name_empty")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @NotEmpty(message = "bo.tax.error_form_percent_empty")
	public String getPercent() {
        return percent;
    }
	
	public void setPercent(String percent) {
        this.percent = percent;
    }
	
}