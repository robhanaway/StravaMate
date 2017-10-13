package com.rh.stravamate.model.datalayer.primitives.exceptions;

/**
 * Created by robert.hanaway on 13/10/2017.
 */

public abstract class DataLayerException extends Exception {
    public DataLayerException(Exception e) {
        super(e);
    }
}
