package cz.vsmie.krist.pms.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Jan Krist
 */

//@Entity
public class Project implements Serializable {
    
    @Id @GeneratedValue
    private Long id;
    
    private String nazev;
    private String poznamka;
    private double rozpocet;
    private double pausal;
    private boolean smazano;

}
