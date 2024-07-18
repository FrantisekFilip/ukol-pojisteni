package cz.itnetwork.ukol.repository;

import cz.itnetwork.ukol.entity.Address;
import cz.itnetwork.ukol.entity.User;
import cz.itnetwork.ukol.mapper.AddressMapper;
import cz.itnetwork.ukol.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressMapper addressMapper;


    public List<User> findAll() {
        String sql = "SELECT u.users_id, u.first_name, u.last_name, u.address_id, " +
                "a.street, a.city, a.postcode " +
                "FROM users u " +
                "JOIN addresses a ON u.address_id = a.id";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public User findById(Long id) {
        String sql = "SELECT u.users_id, u.first_name, u.last_name, u.address_id, " +
                "a.street, a.city, a.postcode " +
                "FROM users u " +
                "JOIN addresses a ON u.address_id = a.id " +
                "WHERE u.users_id = ?";

        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        return user;
    }


    public void save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, address_id) VALUES (?, ?, ?)";

        Long addressId = addressService.getNewAddress(user.getAddress()).getId();
        user.setAddress_id(addressId);
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getAddress_id());

    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ? WHERE users_id = ?";

        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("users_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setAddress_id(rs.getLong("address_id"));

            Address address = new Address();
            address.setId(rs.getLong("address_id"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setPostcode(rs.getString("postcode"));

            user.setAddress(address);
            return user;
        }
    }
}
