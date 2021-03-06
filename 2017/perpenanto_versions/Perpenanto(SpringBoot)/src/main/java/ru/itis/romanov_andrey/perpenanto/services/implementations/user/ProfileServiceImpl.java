package ru.itis.romanov_andrey.perpenanto.services.implementations.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.romanov_andrey.perpenanto.models.usermodels.Product;
import ru.itis.romanov_andrey.perpenanto.models.usermodels.Profile;
import ru.itis.romanov_andrey.perpenanto.models.usermodels.Reservation;
import ru.itis.romanov_andrey.perpenanto.models.usermodels.User;
import ru.itis.romanov_andrey.perpenanto.repositories.ProfileRepository;
import ru.itis.romanov_andrey.perpenanto.repositories.ReservationRepository;
import ru.itis.romanov_andrey.perpenanto.services.interfaces.user.ProfileServiceInterface;
import ru.itis.romanov_andrey.perpenanto.utils.CompareAttributes;
import ru.itis.romanov_andrey.perpenanto.utils.StreamCompareAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * 11.11.2017
 *
 * @author Andrey Romanov (steampart@gmail.com)
 * @version 1.0
 */
@Service
public class ProfileServiceImpl implements ProfileServiceInterface{

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Profile> getProfiles() {
        return this.profileRepository.findAll();
    }

    @Override
    public List<Profile> getProfilesByCookie(String cookieValue) {

        List<Profile> currentProfiles = this.profileRepository.findAll();
        List<Profile> sortedList = new ArrayList<>();
        int size = 3;

        Function<Profile, String> zero = (Profile p) -> String.valueOf(p.getId());
        Function<Profile, String> first = Profile::getPersonName;
        Function<Profile, String> second = Profile::getPersonSurname;

        List<Function<Profile, String>> functions = Arrays.asList(zero, first, second);
        List<String> indexes = Arrays.asList("0", "1", "2");

        Map<String, Function<Profile, String>> functionMap = new HashMap<>();

        IntStream.range(0, size).forEachOrdered(i -> functionMap.put(indexes.get(i), functions.get(i)));

        CompareAttributes<Profile> compareAttr = new StreamCompareAttributes<>();

        sortedList.addAll(compareAttr.sortList(currentProfiles, functionMap, cookieValue));

        return sortedList;

    }

    @Override
    public void saveOrUpdate(Profile profile) {
        this.profileRepository.save(profile);
    }

    @Override
    public void delete(Long id) {
        this.profileRepository.delete(id);
    }

    @Override
    public Profile findByUserId(Long userId) {
        return this.profileRepository.findByUserId(userId);
    }

    @Override
    public int countReservations(User user) {
        return user.getReservations().size();
    }

    @Override
    public Set<Product> getProductsByUser(User user) {
        return user.getProducts();
    }

    @Override
    public int getCommonProductsPrice(User user) {

        Set<Product> products = user.getProducts();

        return products.stream()
                       .mapToInt(Product::getPrice)
                       .sum();

    }

    @Override
    public int getSpendedMoneyOnReservations(User user) {

        Set<Reservation> reservations = user.getReservations();

        return reservations.stream()
                           .mapToInt(reservation -> reservation.getProducts()
                                                               .stream()
                                                               .mapToInt(Product::getPrice)
                                                               .sum()
                           )
                           .sum();
    }

    @Override
    public int getSoldedProductsCount(User user) {

        List<Reservation> reservations = this.reservationRepository.findAll();
        Set<Product> products = user.getProducts();

        return reservations.stream()
                           .map(Reservation::getProducts)
                           .mapToInt(products1-> products1.stream()
                                                          .mapToInt(
                                                            product1 ->
                                                               (int) products.stream()
                                                                             .filter(product -> product.equals(product1))
                                                                             .count()
                                                          )
                                                          .sum()
                                      )
                                      .sum();
    }

    @Override
    public Map<Reservation, Integer> getReservationInformation(User user) {

        Map<Reservation, Integer> resultMap = new HashMap<>();

        Set<Reservation> reservations = user.getReservations();
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.addAll(reservations);

        IntStream.range(0, reservationList.size()).forEachOrdered(i ->{

            Reservation reservation = reservationList.get(i);
            List<Product> products = reservation.getProducts();

            int price =  products.stream()
                                 .mapToInt(Product::getPrice)
                                 .sum();

            resultMap.put(reservation, price);

        });

        return resultMap;

    }
}
