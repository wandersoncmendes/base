/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

/**
 *
 * @author Wanderson
 */
@Entity
@Table(name = "country")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findById", query = "SELECT c FROM Country c WHERE c.id = :id"),
    @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name"),
    @NamedQuery(name = "Country.findByAbbreviation", query = "SELECT c FROM Country c WHERE c.abbreviation = :abbreviation"),
    @NamedQuery(name = "Country.findByCreatedAt", query = "SELECT c FROM Country c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Country.findByUpdatedAt", query = "SELECT c FROM Country c WHERE c.updatedAt = :updatedAt")})
public class Country extends BaseModel {

    @Basic(optional = false)
    @Column(name = "name")
    @NotBlank(message = "name.not.blank")
    @Size(max = 255, message = "name.size.max")
    private String name;

    @Basic(optional = false)
    @Column(name = "abbreviation")
    @NotBlank(message = "abbreviation.not.blank")
    @Size(max = 3, message = "abbreviation.size.max")
    private String abbreviation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Collection<State> stateCollection;

    public Country() {
    }

    public Country(Long id) {
        super(id);
    }

    public Country(Long id, String name, String abbreviation) {
        super(id);
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public Collection<State> getStateCollection() {
        return stateCollection;
    }

    public void setStateCollection(Collection<State> stateCollection) {
        this.stateCollection = stateCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject5.models.Country[ id=" + getId() + " ]";
    }
    
}
