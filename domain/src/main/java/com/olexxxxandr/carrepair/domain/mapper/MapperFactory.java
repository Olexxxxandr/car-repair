package com.olexxxxandr.carrepair.domain.mapper;

import com.olexxxxandr.carrepair.domain.mapper.impl.AddressMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.BrandMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.CarMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.CarPhotoMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.ClientMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.CurrencyMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.DiscountMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.EmployeeMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.ModelMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.OrderMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.PayrollMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.PhoneMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.PositionMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.RoleMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.ServiceMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.SpareMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.UserMapperImpl;
import com.olexxxxandr.carrepair.domain.mapper.impl.WorkroomMapperImpl;

public class MapperFactory {

    public AddressMapper getAddressMapper() {
        return AddressMapperImpl.getInstance();
    }

    public BrandMapper getBrandMapper() {
        return BrandMapperImpl.getInstance();
    }

    public CarMapper getCarMapper() {
        return CarMapperImpl.getInstance();
    }

    public CarPhotoMapper getCarPhotoMapper() {
        return CarPhotoMapperImpl.getInstance();
    }

    public ClientMapper getClientMapper() {
        return ClientMapperImpl.getInstance();
    }

    public CurrencyMapper getCurrencyMapper() {
        return CurrencyMapperImpl.getInstance();
    }

    public DiscountMapper getDiscountMapper() {
        return DiscountMapperImpl.getInstance();
    }

    public EmployeeMapper getEmployeeMapper() {
        return EmployeeMapperImpl.getInstance();
    }

    public ModelMapper getModelMapper() {
        return ModelMapperImpl.getInstance();
    }

    public OrderMapper getOrderMapper() {
        return OrderMapperImpl.getInstance();
    }

    public PayrollMapper getPayrollMapper() {
        return PayrollMapperImpl.getInstance();
    }

    public PhoneMapper getPhoneMapper() {
        return PhoneMapperImpl.getInstance();
    }

    public PositionMapper getPositionMapper() {
        return PositionMapperImpl.getInstance();
    }

    public RoleMapper getRoleMapper() {
        return RoleMapperImpl.getInstance();
    }

    public ServiceMapper getServiceMapper() {
        return ServiceMapperImpl.getInstance();
    }

    public SpareMapper getSpareMapper() {
        return SpareMapperImpl.getInstance();
    }

    public UserMapper getUserMapper() {
        return UserMapperImpl.getInstance();
    }

    public WorkroomMapper getWorkroomMapper() {
        return WorkroomMapperImpl.getInstance();
    }

    private MapperFactory() {}

    private static class SingletonHolder {
        public static final MapperFactory INSTANCE = new MapperFactory();
    }

    public static MapperFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
