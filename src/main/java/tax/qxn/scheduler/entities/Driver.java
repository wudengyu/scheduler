package tax.qxn.scheduler.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="driver")
public class Driver implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String license;
    private int order;
    
}