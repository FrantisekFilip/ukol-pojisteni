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

@Repository
public class AddressRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Address findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM addresses WHERE id = ?", new AddressRowMapper(), id);
    }

    public Long save(Address address) {

        String sql = "INSERT INTO addresses (street, city, postcode) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection con) -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, address.getStreet());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getPostcode());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private static class AddressRowMapper implements RowMapper<Address> {
        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setId(rs.getLong("id"));
            address.setStreet(rs.getString("street"));
            address.setCity(rs.getString("city"));
            address.setPostcode(rs.getString("postcode"));
            return address;
        }
    }
}
