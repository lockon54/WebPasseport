package org.m2acsi.inscription.boundary;

import javax.enterprise.event.Event;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import org.m2acsi.entity.Inscription;

@Stateless
public class InscriptionsRessource {

    @PersistenceContext
    EntityManager em;

    @Inject
    Event<Inscription> listener;
    
    public Inscription findById(long id) {
        return this.em.find(Inscription.class, id);
    }

    public List<Inscription> getAll() {
        return this.em.createNamedQuery("Inscription.findAll", Inscription.class).getResultList();
    }

    public void delete(long id) {
        try {
            Inscription ref = this.em.getReference(Inscription.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {
            // on veut supprimer, et elle n'existe pas, donc c'est bon
        }
    }

    public Inscription save(Inscription ins) {
        Inscription insc = this.em.merge(ins);
        listener.fire(ins);//on declenche l'evenement , tous les composant en ecoute vont recevoir cette evenement
        return insc;
    }

}
