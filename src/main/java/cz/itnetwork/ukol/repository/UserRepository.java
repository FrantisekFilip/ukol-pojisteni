package cz.itnetwork.ukol.repository;

import cz.itnetwork.ukol.entity.Address;
import cz.itnetwork.ukol.entity.User;
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


    public Long save(User user) {

        String sql = "INSERT INTO users (first_name, last_name, address_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setLong(3, user.getAddress_id());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
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
