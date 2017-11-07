package co.edu.uniandes.mktecoturism.controllers;

import co.edu.uniandes.mktecoturism.entities.DetalleCompra;
import co.edu.uniandes.mktecoturism.controllers.util.JsfUtil;
import co.edu.uniandes.mktecoturism.controllers.util.JsfUtil.PersistAction;
import co.edu.uniandes.mktecoturism.facade.DetalleCompraFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("detalleCompraController")
@SessionScoped
public class DetalleCompraController implements Serializable {

    @EJB
    private co.edu.uniandes.mktecoturism.facade.DetalleCompraFacade ejbFacade;
    private List<DetalleCompra> items = null;
    private DetalleCompra selected;

    public DetalleCompraController() {
    }

    public DetalleCompra getSelected() {
        return selected;
    }

    public void setSelected(DetalleCompra selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
        selected.setDetalleCompraPK(new co.edu.uniandes.mktecoturism.entities.DetalleCompraPK());
    }

    private DetalleCompraFacade getFacade() {
        return ejbFacade;
    }

    public DetalleCompra prepareCreate() {
        selected = new DetalleCompra();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/co/edu/uniandes/mktecoturism/util/Bundle").getString("DetalleCompraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/co/edu/uniandes/mktecoturism/util/Bundle").getString("DetalleCompraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/co/edu/uniandes/mktecoturism/util/Bundle").getString("DetalleCompraDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetalleCompra> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/co/edu/uniandes/mktecoturism/util/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/co/edu/uniandes/mktecoturism/util/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DetalleCompra getDetalleCompra(co.edu.uniandes.mktecoturism.entities.DetalleCompraPK id) {
        return getFacade().find(id);
    }

    public List<DetalleCompra> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetalleCompra> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetalleCompra.class)
    public static class DetalleCompraControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleCompraController controller = (DetalleCompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleCompraController");
            return controller.getDetalleCompra(getKey(value));
        }

        co.edu.uniandes.mktecoturism.entities.DetalleCompraPK getKey(String value) {
            co.edu.uniandes.mktecoturism.entities.DetalleCompraPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new co.edu.uniandes.mktecoturism.entities.DetalleCompraPK();
            key.setIdcompra(Integer.parseInt(values[0]));
            key.setIdproducto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(co.edu.uniandes.mktecoturism.entities.DetalleCompraPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdcompra());
            sb.append(SEPARATOR);
            sb.append(value.getIdproducto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetalleCompra) {
                DetalleCompra o = (DetalleCompra) object;
                return getStringKey(o.getDetalleCompraPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleCompra.class.getName()});
                return null;
            }
        }

    }

}
