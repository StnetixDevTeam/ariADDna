package com.stnetix.ariaddna.commonutils.DTO;


/**
 * Created by alexkotov on 01.05.17.
 */
public class KeyStorePasswordDTO {
    private Long id;

    private String pass;

    public KeyStorePasswordDTO() {
    }

    public KeyStorePasswordDTO(String pass) {
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
