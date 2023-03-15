package com.carasent.library.web.dto;

import java.util.Date;

public class EmptyDate extends Date {

    public static final EmptyDate VAL = new EmptyDate();
    private EmptyDate(){}
}
