package entity;

import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Cacheable
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JoinColumn(name = "uuid", referencedColumnName = "uuid")
    private String uuid;

    @Column
    private Double value;

    @Column
    private Double pickUpLat;

    @Column
    private Double pickUpLon;

    @Column
    private Double dropoffLat;

    @Column
    private Double dropoffLon;

    @Column
    private Date promisedTimeOfPickup;

    @Column
    private Date promisedTimeOfDropOff;

    @Column
    private String clientName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "courier_uuid", referencedColumnName = "uuid")
    private Courier courier;

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getPickUpLat() {
        return pickUpLat;
    }

    public void setPickUpLat(Double pickUpLat) {
        this.pickUpLat = pickUpLat;
    }

    public Double getPickUpLon() {
        return pickUpLon;
    }

    public void setPickUpLon(Double pickUpLon) {
        this.pickUpLon = pickUpLon;
    }

    public Double getDropoffLat() {
        return dropoffLat;
    }

    public void setDropoffLat(Double dropoffLat) {
        this.dropoffLat = dropoffLat;
    }

    public Double getDropoffLon() {
        return dropoffLon;
    }

    public void setDropoffLon(Double dropoffLon) {
        this.dropoffLon = dropoffLon;
    }

    public Date getPromisedTimeOfPickup() {
        return promisedTimeOfPickup;
    }

    public void setPromisedTimeOfPickup(Date promisedTimeOfPickup) {
        this.promisedTimeOfPickup = promisedTimeOfPickup;
    }

    public Date getPromisedTimeOfDropOff() {
        return promisedTimeOfDropOff;
    }

    public void setPromisedTimeOfDropOff(Date promisedTimeOfDropOff) {
        this.promisedTimeOfDropOff = promisedTimeOfDropOff;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}
