/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

/**
 *
 * @author Wanderson
 */
@Entity
@Table(name = "state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s"),
    @NamedQuery(name = "State.findById", query = "SELECT s FROM State s WHERE s.id = :id"),
    @NamedQuery(name = "State.findByName", query = "SELECT s FROM State s WHERE s.name = :name"),
    @NamedQuery(name = "State.findByAbbreviation", query = "SELECT s FROM State s WHERE s.abbreviation = :abbreviation"),
    @NamedQuery(name = "State.findByCreatedAt", query = "SELECT s FROM State s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "State.findByUpdatedAt", query = "SELECT s FROM State s WHERE s.updatedAt = :updatedAt")})
public class State extends BaseModel {

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

    @NotNull(message = "country.not.null")
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    private Collection<City> cityCollection;


    public State() {
    }

    public State(Long id) {
        super(id);
    }

    public State(Long id, String name, String abbreviation) {
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
    public Collection<City> getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country countryId) {
        this.country = countryId;
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
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject5.models.State[ id=" + getId() + " ]";
    }
    
}
