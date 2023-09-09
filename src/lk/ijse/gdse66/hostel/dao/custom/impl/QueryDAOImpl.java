package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.QueryDAO;
import lk.ijse.gdse66.hostel.entity.Custom;
import lk.ijse.gdse66.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LocalDateType;
import org.hibernate.type.StringType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 8:21 PM - 9/8/2023
 **/
public class QueryDAOImpl implements QueryDAO {
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public ArrayList<Custom> getAllData() {
        try {
            session = FactoryConfiguration.getInstance().getSession();

            String sql = "SELECT reservation.res_id, reservation.date, reservation.status, reservation.roomQty, s.id, s.name, r.id, r.type, r.key_money, r.quantity " +
                    "FROM reservation " +
                    "INNER JOIN student s on reservation.res_id = s.id " +
                    "INNER JOIN room r on reservation.res_id = r.id " +
                    "ORDER BY CAST(SUBSTRING(res_id, 3) AS UNSIGNED)";

            NativeQuery nativeQuery = session.createSQLQuery(sql);

            nativeQuery.addScalar("res_id", StringType.INSTANCE);
            nativeQuery.addScalar("date", LocalDateType.INSTANCE);
            nativeQuery.addScalar("status", StringType.INSTANCE);
            nativeQuery.addScalar("roomQty", StringType.INSTANCE);
            nativeQuery.addScalar("id", StringType.INSTANCE);
            nativeQuery.addScalar("name", StringType.INSTANCE);
            nativeQuery.addScalar("id", StringType.INSTANCE);
            nativeQuery.addScalar("type", StringType.INSTANCE);
            nativeQuery.addScalar("key_money", StringType.INSTANCE);
            nativeQuery.addScalar("quantity", StringType.INSTANCE);

            nativeQuery.setResultTransformer(Transformers.aliasToBean(Custom.class));

            List<Custom> customEntities = nativeQuery.list();
            ArrayList<Custom> customData = new ArrayList<>();

            for (Custom custom : customEntities) {
                customData.add(custom);
            }
            return customData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }


    }
}
