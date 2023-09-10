package com.kycapps.courseinfo.repository.exceptions;

/*
    @author Mehmet T Kuyucu
    @on 10.09.2023 - 14:26
*/

import java.sql.SQLException;

public class RepositoryException extends RuntimeException {

    public RepositoryException(final String s, final SQLException e) {
        super(s, e);
    }
}
