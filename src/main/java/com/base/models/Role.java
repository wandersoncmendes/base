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

/**
 *
 * @author Wanderson
 */
@Entity
@Table(name = "role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
    @NamedQuery(name = "Role.findByRoleName", query = "SELECT r FROM Role r WHERE r.name = :name"),
    @NamedQuery(name = "Role.findByRoleDescription", query = "SELECT r FROM Role r WHERE r.description = :description"),
    @NamedQuery(name = "Role.findByCreatedAt", query = "SELECT r FROM Role r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Role.findByUpdatedAt", query = "SELECT r FROM Role r WHERE r.updatedAt = :updatedAt")})
public class Role extends BaseModel {

    @Basic(optional = false)
    @Column(name = "name")
    @NotBlank(message = "role.name.not.blank")
    @Size(max = 45, message = "role.name.size.max")
    private String name;

    @Basic(optional = false)
    @Column(name = "description")
    @NotBlank(message = "description.not.blank")
    @Size(max = 255, message = "description.size.max")
    private String description;

    public Role() {
    }

    public Role(Long id) {
        super(id);
    }

    public Role(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public String getRoleName() {
        return name;
    }

    public void setRoleName(String name) {
        this.name = name;
    }

    public String getRoleDescription() {
        return description;
    }

    public void setRoleDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject5.models.Role[ id=" + getId() + " ]";
    }
    
}
