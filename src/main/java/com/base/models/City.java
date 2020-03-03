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
@Table(name = "city")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findById", query = "SELECT c FROM City c WHERE c.id = :id"),
    @NamedQuery(name = "City.findByName", query = "SELECT c FROM City c WHERE c.name = :name"),
    @NamedQuery(name = "City.findByCreatedAt", query = "SELECT c FROM City c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "City.findByUpdatedAt", query = "SELECT c FROM City c WHERE c.updatedAt = :updatedAt")})
public class City extends BaseModel {

    @Basic(optional = false)
    @Column(name = "name")
    @NotBlank(message = "name.not.blank")
    @Size(max = 255, message = "name.size.max")
    private String name;

    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull(message = "state.not.null")
    private State state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Collection<Neighborhood> neighborhoodCollection;

    public City() {
    }

    public City(Long id) {
        super(id);
    }

    public City(Long id, String name) {
        super(id);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @XmlTransient
    public Collection<Neighborhood> getNeighborhoodCollection() {
        return neighborhoodCollection;
    }

    public void setNeighborhoodCollection(Collection<Neighborhood> neighborhoodCollection) {
        this.neighborhoodCollection = neighborhoodCollection;
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
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject5.models.City[ id=" + getId() + " ]";
    }
    
}
