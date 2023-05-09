package com.olexxxxandr.factory;

import com.olexxxxandr.dao.AddressDao;
import com.olexxxxandr.dao.BrandDao;
import com.olexxxxandr.dao.CarDao;
import com.olexxxxandr.dao.CarPhotoDao;
import com.olexxxxandr.dao.ClientDao;
import com.olexxxxandr.dao.CurrencyDao;
import com.olexxxxandr.dao.DiscountDao;
import com.olexxxxandr.dao.EmployeeDao;
import com.olexxxxandr.dao.ModelDao;
import com.olexxxxandr.dao.OrderDao;
import com.olexxxxandr.dao.PayrollDao;
import com.olexxxxandr.dao.PhoneDao;
import com.olexxxxandr.dao.PositionDao;
import com.olexxxxandr.dao.RoleDao;
import com.olexxxxandr.dao.ServiceDao;
import com.olexxxxandr.dao.SpareDao;
import com.olexxxxandr.dao.UserDao;
import com.olexxxxandr.dao.WorkroomDao;
import com.olexxxxandr.impl.AddressDaoImpl;
import com.olexxxxandr.impl.BrandDaoImpl;
import com.olexxxxandr.impl.CarDaoImpl;
import com.olexxxxandr.impl.CarPhotoDaoImpl;
import com.olexxxxandr.impl.ClientDaoImpl;
import com.olexxxxandr.impl.CurrencyDaoImpl;
import com.olexxxxandr.impl.DiscountDaoImpl;
import com.olexxxxandr.impl.EmployeeDaoImpl;
import com.olexxxxandr.impl.ModelDaoImpl;
import com.olexxxxandr.impl.OrderDaoImpl;
import com.olexxxxandr.impl.PayrollDaoImpl;
import com.olexxxxandr.impl.PhoneDaoImpl;
import com.olexxxxandr.impl.PositionDaoImpl;
import com.olexxxxandr.impl.RoleDaoImpl;
import com.olexxxxandr.impl.ServiceDaoImpl;
import com.olexxxxandr.impl.SpareDaoImpl;
import com.olexxxxandr.impl.UserDaoImpl;
import com.olexxxxandr.impl.WorkroomDaoImpl;
import com.olexxxxandr.util.ConnectionManager;

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
