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
@Table(name = "neighborhood")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Neighborhood.findAll", query = "SELECT n FROM Neighborhood n"),
    @NamedQuery(name = "Neighborhood.findById", query = "SELECT n FROM Neighborhood n WHERE n.id = :id"),
    @NamedQuery(name = "Neighborhood.findByName", query = "SELECT n FROM Neighborhood n WHERE n.name = :name"),
    @NamedQuery(name = "Neighborhood.findByCreatedAt", query = "SELECT n FROM Neighborhood n WHERE n.createdAt = :createdAt"),
    @NamedQuery(name = "Neighborhood.findByUpdatedAt", query = "SELECT n FROM Neighborhood n WHERE n.updatedAt = :updatedAt")})
public class Neighborhood extends BaseModel {

    @Basic(optional = false)
    @Column(name = "name")
    @NotBlank(message = "name.not.blank")
    @Size(max = 255, message = "name.size.max")
    private String name;

    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull(message = "city.not.null")
    private City city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "neighborhood")
    private Collection<Address> addressCollection;

    public Neighborhood() {
    }

    public Neighborhood(Long id) {
        super(id);
    }

    public Neighborhood(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        if (!(object instanceof Neighborhood)) {
            return false;
        }
        Neighborhood other = (Neighborhood) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject5.models.Neighborhood[ id=" + getId() + " ]";
    }
    
}
