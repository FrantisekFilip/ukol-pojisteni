package cz.itnetwork.ukol.repository;

import cz.itnetwork.ukol.entity.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InsuranceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Insurance> findAll() {
        String sql = "SELECT id, type, description, amount FROM insurances" ;
        return jdbcTemplate.query(sql, new InsuranceRepository.InsuranceRowMapper());
    }

    private static class InsuranceRowMapper implements RowMapper<Insurance> {
        @Override
        public Insurance mapRow(ResultSet rs, int rowNum) throws SQLException {
            Insurance insurance = new Insurance();
            insurance.setId(rs.getLong("id"));
            insurance.setType(rs.getString("type"));
            insurance.setDescription(rs.getString("description"));
            insurance.setAmount(rs.getBigDecimal("amount"));
            return insurance;
        }
    }
}
