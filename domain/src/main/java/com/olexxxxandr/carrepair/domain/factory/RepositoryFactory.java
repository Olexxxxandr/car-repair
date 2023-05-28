package com.olexxxxandr.carrepair.domain.factory;

import com.olexxxxandr.carrepair.domain.repository.AddressRepository;
import com.olexxxxandr.carrepair.domain.repository.BrandRepository;
import com.olexxxxandr.carrepair.domain.repository.CarPhotoRepository;
import com.olexxxxandr.carrepair.domain.repository.CarRepository;
import com.olexxxxandr.carrepair.domain.repository.ClientRepository;
import com.olexxxxandr.carrepair.domain.repository.CurrencyRepository;
import com.olexxxxandr.carrepair.domain.repository.DiscountRepository;
import com.olexxxxandr.carrepair.domain.repository.EmployeeRepository;
import com.olexxxxandr.carrepair.domain.repository.ModelRepository;
import com.olexxxxandr.carrepair.domain.repository.OrderRepository;
import com.olexxxxandr.carrepair.domain.repository.PayrollRepository;
import com.olexxxxandr.carrepair.domain.repository.PhoneRepository;
import com.olexxxxandr.carrepair.domain.repository.PositionRepository;
import com.olexxxxandr.carrepair.domain.repository.RoleRepository;
import com.olexxxxandr.carrepair.domain.repository.ServiceRepository;
import com.olexxxxandr.carrepair.domain.repository.SpareRepository;
import com.olexxxxandr.carrepair.domain.repository.UserRepository;
import com.olexxxxandr.carrepair.domain.repository.WorkroomRepository;
import com.olexxxxandr.carrepair.domain.repository.impl.AddressRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.BrandRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.CarPhotoRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.CarRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.ClientRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.CurrencyRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.DiscountRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.EmployeeRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.ModelRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.OrderRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.PayrollRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.PhoneRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.PositionRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.RoleRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.ServiceRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.SpareRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.UserRepositoryImpl;
import com.olexxxxandr.carrepair.domain.repository.impl.WorkroomRepositoryImpl;
import com.olexxxxandr.carrepair.persistence.DaoFactory;

public class RepositoryFactory {

    private final AddressRepositoryImpl addressRepositoryImpl;
    private final BrandRepositoryImpl brandRepositoryImpl;
    private final CarPhotoRepositoryImpl carPhotoRepositoryImpl;
    private final CarRepositoryImpl carRepositoryImpl;
    private final ClientRepositoryImpl clientRepositoryImpl;
    private final CurrencyRepositoryImpl currencyRepositoryImpl;
    private final DiscountRepositoryImpl discountRepositoryImpl;
    private final EmployeeRepositoryImpl employeeRepositoryImpl;
    private final ModelRepositoryImpl modelRepositoryImpl;
    private final OrderRepositoryImpl orderRepositoryImpl;
    private final PayrollRepositoryImpl payrollRepositoryImpl;
    private final PhoneRepositoryImpl phoneRepositoryImpl;
    private final PositionRepositoryImpl positionRepositoryImpl;
    private final RoleRepositoryImpl roleRepositoryImpl;
    private final ServiceRepositoryImpl serviceRepositoryImpl;
    private final SpareRepositoryImpl spareRepositoryImpl;
    private final UserRepositoryImpl userRepositoryImpl;
    private final WorkroomRepositoryImpl workroomRepositoryImpl;

    private RepositoryFactory() {
        addressRepositoryImpl = new AddressRepositoryImpl();
        brandRepositoryImpl = new BrandRepositoryImpl();
        carPhotoRepositoryImpl = new CarPhotoRepositoryImpl();
        carRepositoryImpl = new CarRepositoryImpl();
        clientRepositoryImpl = new ClientRepositoryImpl();
        currencyRepositoryImpl = new CurrencyRepositoryImpl();
        discountRepositoryImpl = new DiscountRepositoryImpl();
        employeeRepositoryImpl = new EmployeeRepositoryImpl();
        modelRepositoryImpl = new ModelRepositoryImpl();
        orderRepositoryImpl = new OrderRepositoryImpl();
        payrollRepositoryImpl = new PayrollRepositoryImpl();
        phoneRepositoryImpl = new PhoneRepositoryImpl();
        positionRepositoryImpl = new PositionRepositoryImpl();
        roleRepositoryImpl = new RoleRepositoryImpl();
        serviceRepositoryImpl = new ServiceRepositoryImpl();
        spareRepositoryImpl = new SpareRepositoryImpl();
        userRepositoryImpl = new UserRepositoryImpl();
        workroomRepositoryImpl = new WorkroomRepositoryImpl();
    }

    public AddressRepository getAddressRepository() {
        return addressRepositoryImpl;
    }

    public BrandRepository getBrandRepository() {
        return brandRepositoryImpl;
    }

    public CarPhotoRepository getCarPhotoRepository() {
        return carPhotoRepositoryImpl;
    }

    public CarRepository getCarRepository() {
        return carRepositoryImpl;
    }

    public ClientRepository getClientRepository() {
        return clientRepositoryImpl;
    }

    public CurrencyRepository getCurrencyRepository() {
        return currencyRepositoryImpl;
    }

    public DiscountRepository getDiscountRepository() {
        return discountRepositoryImpl;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepositoryImpl;
    }

    public ModelRepository getModelRepository() {
        return modelRepositoryImpl;
    }

    public OrderRepository getOrderRepository() {
        return orderRepositoryImpl;
    }

    public PayrollRepository getPayrollRepository() {
        return payrollRepositoryImpl;
    }

    public PhoneRepository getPhoneRepository() {
        return phoneRepositoryImpl;
    }

    public PositionRepository getPositionRepository() {
        return positionRepositoryImpl;
    }

    public RoleRepository getRoleRepository() {
        return roleRepositoryImpl;
    }

    public ServiceRepository getServiceRepository() {
        return serviceRepositoryImpl;
    }

    public SpareRepository getSpareRepository() {
        return spareRepositoryImpl;
    }

    public UserRepository getUserRepository() {
        return userRepositoryImpl;
    }

    public WorkroomRepository getWorkroomRepository() {
        return workroomRepositoryImpl;
    }

    public void closePool() {
        DaoFactory.getInstance().closeAllDaoConnections();
    }

    private static class SingletonHolder {
        public static final RepositoryFactory INSTANCE = new RepositoryFactory();
    }

    public static RepositoryFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
