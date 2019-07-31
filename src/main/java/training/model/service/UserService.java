package training.model.service;

import ua.training.model.entities.Coupon;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;
import ua.training.model.entities.enums.Street;

import java.util.List;

public class UserService {


    public List<User> findAll() {
        return userRepo.findAll();
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        user.setRole(Role.Client);

        userRepo.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void updateUser(User user, Map<String, String> form) {
        user.setUsername(form.get("username"));
        user.setRole(Role.valueOf(form.get("role")));
        userRepo.save(user);
    }

    public void addCoupon(double percent, User user) {
        Coupon coupon = Coupon.builder()
                .discountPercent(percent)
                .idUser(user)
                .build();
        couponRepo.save(coupon);
    }

    public boolean createTaxi(String number, String driverName, Type type) {
        Taxi taxi = Taxi.builder()
                .busy(false)
                .carNumber(number)
                .driverName(driverName)
                .locationHouse(1)
                .locationStreet(Street.Bondarska)
                .type(type)
                .build();
        taxiRepo.save(taxi);
        return true;
    }
}
