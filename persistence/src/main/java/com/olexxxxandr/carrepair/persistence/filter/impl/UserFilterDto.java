package com.olexxxxandr.carrepair.persistence.filter.impl;

import com.olexxxxandr.carrepair.persistence.filter.FilterDto;

public record UserFilterDto(String email, String login) implements FilterDto {}
