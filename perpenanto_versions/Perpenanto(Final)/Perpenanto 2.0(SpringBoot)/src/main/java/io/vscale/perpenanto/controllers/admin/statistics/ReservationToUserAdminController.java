package io.vscale.perpenanto.controllers.admin.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.vscale.perpenanto.forms.admin.ReservationToUserForm;
import io.vscale.perpenanto.services.interfaces.admin.ReservationToUserAdminService;
import io.vscale.perpenanto.services.interfaces.user.ReservationToUserService;

/**
 * 03.02.2018
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/statistics")
public class ReservationToUserAdminController {

    private final ReservationToUserService reservationToUserService;
    private final ReservationToUserAdminService reservationToUserAdminService;

    @Autowired
    public ReservationToUserAdminController(ReservationToUserService reservationToUserService,
                                            ReservationToUserAdminService reservationToUserAdminService) {
        this.reservationToUserService = reservationToUserService;
        this.reservationToUserAdminService = reservationToUserAdminService;
    }

    @GetMapping("/reservations_to_user")
    public ModelAndView getReservationsToUser(@CookieValue(value = "status", defaultValue = "reset") String cookieValue){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/reservation_to_user_admin");
        modelAndView.addObject("reservations_to_users",
                this.reservationToUserService.getReservationsToUsersByCookie(cookieValue));

        return modelAndView;

    }

    @PostMapping(value = "/reservations_to_user", params = "add_action")
    public ModelAndView saveNewReservationToUser(
                                             @CookieValue(value = "status", defaultValue = "reset") String cookieValue,
                                             @ModelAttribute("reservationToUserForm")
                                                     ReservationToUserForm reservationToUserForm){

        this.reservationToUserAdminService.addReservationToUser(reservationToUserForm);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/reservation_to_user_admin");
        modelAndView.addObject("reservations_to_users",
                this.reservationToUserService.getReservationsToUsersByCookie(cookieValue));

        return modelAndView;

    }

    @PostMapping(value = "/reservations_to_user", params = "update_action")
    public ModelAndView updateReservationToUser(
            @CookieValue(value = "status", defaultValue = "reset") String cookieValue,
            @ModelAttribute("reservationToUserForm")
                    ReservationToUserForm reservationToUserForm){

        this.reservationToUserAdminService.updateReservationToUser(reservationToUserForm);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/reservation_to_user_admin");
        modelAndView.addObject("reservations_to_users",
                this.reservationToUserService.getReservationsToUsersByCookie(cookieValue));

        return modelAndView;

    }

    @PostMapping(value = "/reservations_to_user", params = "delete_action")
    public ModelAndView deleteReservationToUser(
            @CookieValue(value = "status", defaultValue = "reset") String cookieValue,
            @ModelAttribute("reservationToUserForm")
                    ReservationToUserForm reservationToUserForm){

        this.reservationToUserAdminService.deleteReservationToUser(
                reservationToUserForm.getUserId(), reservationToUserForm.getOrderId()
        );

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/reservation_to_user_admin");
        modelAndView.addObject("reservations_to_users",
                this.reservationToUserService.getReservationsToUsersByCookie(cookieValue));

        return modelAndView;

    }

}
