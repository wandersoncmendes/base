/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wanderson
 */
@Entity
@Table(name = "address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findById", query = "SELECT a FROM Address a WHERE a.id = :id"),
    @NamedQuery(name = "Address.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street"),
    @NamedQuery(name = "Address.findByNumber", query = "SELECT a FROM Address a WHERE a.number = :number"),
    @NamedQuery(name = "Address.findByComplement", query = "SELECT a FROM Address a WHERE a.complement = :complement"),
    @NamedQuery(name = "Address.findByZipCode", query = "SELECT a FROM Address a WHERE a.zipCode = :zipCode"),
    @NamedQuery(name = "Address.findByCreatedAt", query = "SELECT a FROM Address a WHERE a.createdAt = :createdAt"),
    @NamedQuery(name = "Address.findByUpdatedAt", query = "SELECT a FROM Address a WHERE a.updatedAt = :updatedAt")})
public class Address extends BaseModel {

    @Basic(optional = false)
    @Column(name = "street")
    @NotBlank(message = "address.street.not.blank")
    @Size(max = 255, message = "address.street.size.max")
    private String street;

    @Basic(optional = false)
    @Column(name = "number")
    @NotBlank(message = "address.number.not.blank")
    @Size(max = 20, message = "address.number.size.max")
    private String number;

    @Column(name = "complement")
    @Size(max = 100, message = "address.complement.size.max")
    private String complement;

    @Basic(optional = false)
    @Column(name = "zip_code")
    @NotBlank(message = "address.zipcode.not.blank")
    @Size(max = 8, message = "address.zipcode.size.max")
    private String zipCode;

    @JoinColumn(name = "neighborhood_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull(message = "neighborhood.not.null")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Neighborhood neighborhood;

    public Address() {
    }

    public Address(Long id) {
        super(id);
    }

    public Address(Long id, String street, String number, String zipCode) {
        super(id);
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
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
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject5.models.Address[ id=" + getId() + " ]";
    }
    
}
