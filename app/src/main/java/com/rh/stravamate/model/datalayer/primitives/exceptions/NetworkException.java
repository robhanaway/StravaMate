package com.rh.stravamate.model.datalayer.primitives.exceptions;

/**
 * Created by robert.hanaway on 13/10/2017.
 */

public class NetworkException extends DataLayerException {

    public NetworkException(Exception e) {
        super(e);
    }
}
