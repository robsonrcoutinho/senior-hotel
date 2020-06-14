package br.com.robson.seniorhotel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="booking")
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private STATUS status;
    private String roomNumber;


    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss.fffffffzzz")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonIgnore
    private LocalDateTime initialDay;

    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss.fffffffzzz")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime finalDay;
    private BigDecimal valueConsumedHotel;


    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserClient userClient;

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getInitialDay() {
        return initialDay;
    }

    public void setInitialDay(LocalDateTime initialDay) {
        this.initialDay = initialDay;
    }

    public LocalDateTime getFinalDay() {
        return finalDay;
    }

    public void setFinalDay(LocalDateTime finalDay) {
        this.finalDay = finalDay;
    }

    public BigDecimal getValueConsumedHotel() {
        return valueConsumedHotel;
    }

    public void setValueConsumedHotel(BigDecimal valueConsumedHotel) {
        this.valueConsumedHotel = valueConsumedHotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserClient getUserClient() {
        return userClient;
    }

    public void setUserClient(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) &&
                status == booking.status &&
                Objects.equals(roomNumber, booking.roomNumber) &&
                Objects.equals(initialDay, booking.initialDay) &&
                Objects.equals(finalDay, booking.finalDay) &&
                Objects.equals(valueConsumedHotel, booking.valueConsumedHotel) &&
                Objects.equals(userClient, booking.userClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, roomNumber, initialDay, finalDay, valueConsumedHotel, userClient);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", status=" + status +
                ", roomNumber='" + roomNumber + '\'' +
                ", initialDay=" + initialDay +
                ", finalDay=" + finalDay +
                ", valueConsumedHotel=" + valueConsumedHotel +
                ", userClient=" + userClient +
                '}';
    }
}
