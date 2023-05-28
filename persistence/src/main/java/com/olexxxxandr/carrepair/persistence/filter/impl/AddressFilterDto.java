package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record AddressFilterDto(String country, String region, String city, String street, String home)
        implements FilterDto {}
