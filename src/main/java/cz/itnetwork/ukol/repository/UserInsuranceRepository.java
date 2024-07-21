package cz.itnetwork.ukol.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInsuranceRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void updateUserInsurances(Long userId, List<Long> insuranceIds) {

        String insertSql = "INSERT INTO users_insurances (user_id, insurance_id) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE user_id = VALUES(user_id), insurance_id = VALUES(insurance_id)";

        for (Long insuranceId : insuranceIds) {
            try {
                jdbcTemplate.update(insertSql, userId, insuranceId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUserInsurance(Long userId, Long insuranceId) {
        String deleteSql = "DELETE FROM users_insurances WHERE user_id = ? AND insurance_id = ?";
        try {
            jdbcTemplate.update(deleteSql, userId, insuranceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
