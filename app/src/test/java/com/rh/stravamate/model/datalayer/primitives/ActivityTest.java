package com.rh.stravamate.model.datalayer.primitives;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by robert.hanaway on 11/10/2017.
 */

public class ActivityTest {

    static float AVERAGE_SPEED = 2.556f;
    static int EXPECTED = 391;
    @Test
    public void testAveragePerKm() {
       Activity activity = new Activity();
       activity.setAverageSpeed(AVERAGE_SPEED);

        Assert.assertEquals(EXPECTED, activity.getAverageSpeedPerKm());
    }
}
