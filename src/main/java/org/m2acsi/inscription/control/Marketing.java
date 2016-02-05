/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m2acsi.inscription.control;

import java.time.LocalDateTime;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import org.m2acsi.entity.*;


/**
 *
 * @author Yannis
 */
@Stateless
public class Marketing {
    
    public void onStoring( @Observes Inscription ins){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(">>> Notification >> ajout -- "+now);
    }
}
