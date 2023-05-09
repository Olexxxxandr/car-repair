package com.olexxxxandr.filter.impl;

import com.olexxxxandr.filter.FilterDto;

public record AddressFilterDto(
        String country, String region, String city, String street, String home)
        implements FilterDto {}
