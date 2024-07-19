package Proyecto.TapiceriaConfort.entities;

import Proyecto.TapiceriaConfort.constants.EntityStorage;
import Proyecto.TapiceriaConfort.enums.ServiceStatus;
import Proyecto.TapiceriaConfort.storage.Storable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Service implements Storable {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Long id;
    private String name;
    private Double price;
    private ServiceStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private Bill bill;
    private Long userId;

    public Service() {
        this.bill = new Bill();
    }

    public Service(Long id, String name, Double price, ServiceStatus status, LocalDateTime createdAt, LocalDateTime startedAt, LocalDateTime finishedAt, Bill bill, Long userId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.bill = bill;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void startService() {
        System.out.println("Starting service...");
    }

    public void finishService() {
    }

    public void completeService() {
    }

    @Override
    public String serialize() {
        return this.id + ","
                + this.name + ","
                + this.price + ","
                + this.status + ","
                + this.createdAt.format(formatter) + ","
                + this.startedAt.format(formatter) + ","
                + this.finishedAt + ","
                + this.bill.getId() + ","
                + this.userId;
    }

    @Override
    public Storable deserialize(String line) {
        String[] fields = line.split(",");
        Bill dsBill = EntityStorage.billStorage.find(new Bill(), Bill -> Objects.equals(bill.getId(), Long.valueOf(fields[7]))).orElse(null);

        return new Service(Long.valueOf(
                fields[0]),
                fields[1],
                Double.valueOf(fields[2]),
                ServiceStatus.valueOf(fields[3]),
                LocalDateTime.parse(fields[4], formatter),
                LocalDateTime.parse(fields[5], formatter),
                LocalDateTime.parse(fields[6], formatter),
                dsBill,
                Long.valueOf(fields[8])
        );
    }
}
