/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.mktecoturism.facade;

import co.edu.uniandes.mktecoturism.entities.DetalleCompra;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jlrodriguez
 */
@Stateless
public class DetalleCompraFacade extends AbstractFacade<DetalleCompra> {

    @PersistenceContext(unitName = "com.mycompany_mktEcoturism_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCompraFacade() {
        super(DetalleCompra.class);
    }
    
}
