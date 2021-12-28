package com.etiya.rentACar.entities;

import java.sql.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="renting_bills")
public class RentingBill {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    @Column(name="bill_id")
    private int billId;

    @Column(name="creation_date")
    private Date creationDate;

    @Column(name="renting_start_date")
    private Date rentingStartDate;

    @Column(name="renting_end_date")
    private Date rentingEndDate;

    @Column(name="total_renting_day")
    private int totalRentingDay;

    @Column(name="renting_price")
    private int rentingPrice;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="rental_id")
    private Rental rental;

}