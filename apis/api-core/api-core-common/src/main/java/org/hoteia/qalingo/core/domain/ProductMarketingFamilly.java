/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.8.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2014
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.core.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "TECO_PRODUCT_MARKETING_FAMILLY")
public class ProductMarketingFamilly extends AbstractEntity<ProductMarketingFamilly> {

    /**
     * Generated UID
     */
    private static final long serialVersionUID = -3980701330914384779L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Version
    @Column(name = "VERSION", nullable = false) // , columnDefinition = "int(11) default 1"
    private int version;

    @Column(name = "CODE", unique = true, nullable = false)
    private String code;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE }, targetEntity = org.hoteia.qalingo.core.domain.CurrencyReferential.class)
    @JoinTable(name = "TECO_PRODUCT_MARKETING_FAMILLY_TAX_REL", joinColumns = @JoinColumn(name = "PRODUCT_MARKETING_TYPE_FAMILLY_ID"), inverseJoinColumns = @JoinColumn(name = "TAX_ID"))
    private Set<Tax> taxes = new HashSet<Tax>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE }, targetEntity = org.hoteia.qalingo.core.domain.ProductSkuOptionDefinition.class)
    @JoinTable(name = "TECO_PRODUCT_MARKETING_FAMILLY_PRODUCT_SKU_OPTION_DEFINITION_REL", joinColumns = @JoinColumn(name = "PRODUCT_MARKETING_FAMILLY_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_SKU_OPTION_DEFINITION_ID"))
    private Set<ProductSkuOptionDefinition> productSkuOptionDefinition = new HashSet<ProductSkuOptionDefinition>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATE")
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_UPDATE")
    private Date dateUpdate;

    public ProductMarketingFamilly() {
        this.dateCreate = new Date();
        this.dateUpdate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
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

    public Set<Tax> getTaxes() {
        return taxes;
    }

    public void setTaxes(Set<Tax> taxes) {
        this.taxes = taxes;
    }

    public Set<ProductSkuOptionDefinition> getProductSkuOptionDefinition() {
		return productSkuOptionDefinition;
	}
    
    public void setProductSkuOptionDefinition(Set<ProductSkuOptionDefinition> productSkuOptionDefinition) {
		this.productSkuOptionDefinition = productSkuOptionDefinition;
	}

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object sourceObj) {
        Object obj = deproxy(sourceObj);
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductMarketingFamilly other = (ProductMarketingFamilly) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (dateCreate == null) {
            if (other.dateCreate != null)
                return false;
        } else if (!dateCreate.equals(other.dateCreate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProductMarketingFamilly [id=" + id + ", version=" + version + ", name=" + name + ", description=" + description + ", code=" + code + ", dateCreate=" + dateCreate + ", dateUpdate=" + dateUpdate + "]";
    }

}