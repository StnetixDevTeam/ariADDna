package com.stnetix.ariaddna.persistence.entities;



import javax.persistence.*;

/**
 * Created by alexkotov on 26.04.17.
 */
@Entity
@Table(name = "keystorepassword")
public class KeyStorePassword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pass;

    public Long getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
