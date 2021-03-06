package ru.itis.romanov_andrey.perpenanto.forms.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;

import ru.itis.romanov_andrey.perpenanto.models.usermodels.Product;
import ru.itis.romanov_andrey.perpenanto.models.usermodels.Reservation;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 30.01.2018
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class ReservationInformation {

    private Reservation reservation;
    private String prettyReservationDate;
    private Integer price;
    private Map<Product, AtomicInteger> productWithCount;

}
