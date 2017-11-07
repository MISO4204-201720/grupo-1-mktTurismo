/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.mktecoturism.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jlrodriguez
 */
@Entity
@Table(name = "detalle_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCompra.findAll", query = "SELECT d FROM DetalleCompra d")
    , @NamedQuery(name = "DetalleCompra.findByCantidad", query = "SELECT d FROM DetalleCompra d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleCompra.findByValor", query = "SELECT d FROM DetalleCompra d WHERE d.valor = :valor")
    , @NamedQuery(name = "DetalleCompra.findByIdcompra", query = "SELECT d FROM DetalleCompra d WHERE d.detalleCompraPK.idcompra = :idcompra")
    , @NamedQuery(name = "DetalleCompra.findByIdproducto", query = "SELECT d FROM DetalleCompra d WHERE d.detalleCompraPK.idproducto = :idproducto")})
public class DetalleCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleCompraPK detalleCompraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private double valor;

    public DetalleCompra() {
    }

    public DetalleCompra(DetalleCompraPK detalleCompraPK) {
        this.detalleCompraPK = detalleCompraPK;
    }

    public DetalleCompra(DetalleCompraPK detalleCompraPK, int cantidad, double valor) {
        this.detalleCompraPK = detalleCompraPK;
        this.cantidad = cantidad;
        this.valor = valor;
    }

    public DetalleCompra(int idcompra, int idproducto) {
        this.detalleCompraPK = new DetalleCompraPK(idcompra, idproducto);
    }

    public DetalleCompraPK getDetalleCompraPK() {
        return detalleCompraPK;
    }

    public void setDetalleCompraPK(DetalleCompraPK detalleCompraPK) {
        this.detalleCompraPK = detalleCompraPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCompraPK != null ? detalleCompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCompra)) {
            return false;
        }
        DetalleCompra other = (DetalleCompra) object;
        if ((this.detalleCompraPK == null && other.detalleCompraPK != null) || (this.detalleCompraPK != null && !this.detalleCompraPK.equals(other.detalleCompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.mktecoturism.entities.DetalleCompra[ detalleCompraPK=" + detalleCompraPK + " ]";
    }
    
}
