/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.mktecoturism.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jlrodriguez
 */
@Embeddable
public class DetalleCompraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOMPRA")
    private int idcompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPRODUCTO")
    private int idproducto;

    public DetalleCompraPK() {
    }

    public DetalleCompraPK(int idcompra, int idproducto) {
        this.idcompra = idcompra;
        this.idproducto = idproducto;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcompra;
        hash += (int) idproducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCompraPK)) {
            return false;
        }
        DetalleCompraPK other = (DetalleCompraPK) object;
        if (this.idcompra != other.idcompra) {
            return false;
        }
        if (this.idproducto != other.idproducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.mktecoturism.entities.DetalleCompraPK[ idcompra=" + idcompra + ", idproducto=" + idproducto + " ]";
    }
    
}
