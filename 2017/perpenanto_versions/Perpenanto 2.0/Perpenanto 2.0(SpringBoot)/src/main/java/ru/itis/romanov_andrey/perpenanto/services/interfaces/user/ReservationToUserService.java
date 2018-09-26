package ru.itis.romanov_andrey.perpenanto.services.interfaces.user;

import ru.itis.romanov_andrey.perpenanto.models.transfermodels.ReservationToUserTransfer;
import ru.itis.romanov_andrey.perpenanto.models.usermodels.ReservationToUser;

import java.util.List;

/**
 * 03.02.2018
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
public interface ReservationToUserService {

    List<ReservationToUserTransfer> getReservationsToUsers();
    List<ReservationToUserTransfer> getReservationsToUsersByCookie(String cookieValue);

    void saveReservationToUser(ReservationToUser reservationToUser);
    void updateReservationToUser(ReservationToUser reservationToUser);
    void deleteReservationToUser(Long userId, Long orderId);

}
