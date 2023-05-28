package com.olexxxxandr.carrepair.persistence;

import com.olexxxxandr.carrepair.persistence.dao.AddressDao;
import com.olexxxxandr.carrepair.persistence.dao.BrandDao;
import com.olexxxxandr.carrepair.persistence.dao.CarDao;
import com.olexxxxandr.carrepair.persistence.dao.CarPhotoDao;
import com.olexxxxandr.carrepair.persistence.dao.ClientDao;
import com.olexxxxandr.carrepair.persistence.dao.CurrencyDao;
import com.olexxxxandr.carrepair.persistence.dao.DiscountDao;
import com.olexxxxandr.carrepair.persistence.dao.EmployeeDao;
import com.olexxxxandr.carrepair.persistence.dao.ModelDao;
import com.olexxxxandr.carrepair.persistence.dao.OrderDao;
import com.olexxxxandr.carrepair.persistence.dao.PayrollDao;
import com.olexxxxandr.carrepair.persistence.dao.PhoneDao;
import com.olexxxxandr.carrepair.persistence.dao.PositionDao;
import com.olexxxxandr.carrepair.persistence.dao.RoleDao;
import com.olexxxxandr.carrepair.persistence.dao.ServiceDao;
import com.olexxxxandr.carrepair.persistence.dao.SpareDao;
import com.olexxxxandr.carrepair.persistence.dao.UserDao;
import com.olexxxxandr.carrepair.persistence.dao.WorkroomDao;
import com.olexxxxandr.carrepair.persistence.impl.AddressDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.BrandDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.CarDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.CarPhotoDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.ClientDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.CurrencyDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.DiscountDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.EmployeeDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.ModelDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.OrderDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.PayrollDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.PhoneDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.PositionDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.RoleDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.ServiceDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.SpareDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.UserDaoImpl;
import com.olexxxxandr.carrepair.persistence.impl.WorkroomDaoImpl;
import com.olexxxxandr.carrepair.persistence.util.ConnectionManager;

public class DaoFactory {

    public AddressDao getAddressDao() {
        return AddressDaoImpl.getInstance();
    }

    public BrandDao getBrandDao() {
        return BrandDaoImpl.getInstance();
    }

    public CarDao getCarDao() {
        return CarDaoImpl.getInstance();
    }

    public CarPhotoDao getCarPhotoDao() {
        return CarPhotoDaoImpl.getInstance();
    }

    public ClientDao getClientDao() {
        return ClientDaoImpl.getInstance();
    }

    public CurrencyDao getCurrencyDao() {
        return CurrencyDaoImpl.getInstance();
    }

    public DiscountDao getDiscountDao() {
        return DiscountDaoImpl.getInstance();
    }

    public ModelDao getModelDao() {
        return ModelDaoImpl.getInstance();
    }

    public OrderDao getOrderDao() {
        return OrderDaoImpl.getInstance();
    }

    public PayrollDao getPayrollDao() {
        return PayrollDaoImpl.getInstance();
    }

    public PhoneDao getPhoneDao() {
        return PhoneDaoImpl.getInstance();
    }

    public PositionDao getPositionDao() {
        return PositionDaoImpl.getInstance();
    }

    public RoleDao getRoleDao() {
        return RoleDaoImpl.getInstance();
    }

    public ServiceDao getServiceDao() {
        return ServiceDaoImpl.getInstance();
    }

    public SpareDao getSpareDao() {
        return SpareDaoImpl.getInstance();
    }

    public EmployeeDao getEmployeeDao() {
        return EmployeeDaoImpl.getInstance();
    }

    public UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    public WorkroomDao getWorkroomDao() {
        return WorkroomDaoImpl.getInstance();
    }

    public void closeAllDaoConnections() {
        ConnectionManager.closePool();
    }

    // ...

    private DaoFactory() {}

    private static class SingletonHolder {
        public static final DaoFactory INSTANCE = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
