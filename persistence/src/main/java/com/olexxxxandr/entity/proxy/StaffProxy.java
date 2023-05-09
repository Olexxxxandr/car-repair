package com.olexxxxandr.entity.proxy;

import com.olexxxxandr.entity.impl.EmployeeEntity;
import com.olexxxxandr.factory.DaoFactory;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class StaffProxy implements Staff {

    private Staff staff;

    @Override
    public List<EmployeeEntity> get(UUID orderId) {
        if (Objects.isNull(staff)) {
            staff =
                    oi ->
                            Collections.unmodifiableList(
                                    DaoFactory.getInstance().getOrderDao().findAllStaff(oi));
        }
        return staff.get(orderId);
    }
}
