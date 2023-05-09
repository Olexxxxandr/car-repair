package com.olexxxxandr.filter.impl;

import com.olexxxxandr.filter.FilterDto;

public record UserFilterDto(String email, String login) implements FilterDto {}
