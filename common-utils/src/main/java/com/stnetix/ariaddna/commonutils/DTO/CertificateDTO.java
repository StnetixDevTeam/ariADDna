package com.stnetix.ariaddna.commonutils.DTO;

/**
 * Created by alexkotov on 01.05.17.
 */
public class CertificateDTO {
    private Long id;

    private String uuid;

    private Boolean isActive;

    public CertificateDTO() {
        isActive = false;
    }

    public CertificateDTO(String uuid, Boolean isActive) {
        this.uuid = uuid;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CertificateDTO that = (CertificateDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        return isActive != null ? isActive.equals(that.isActive) : that.isActive == null;

    }


}
