package de.fhs.pcm_214;

import java.util.Comparator;

public class TimestampComparator implements Comparator<Timestamp> {
    @Override
    public int compare(Timestamp o1, Timestamp o2) {
        if (o1.getDate().isAfter(o2.getDate())) {
            return 1;
        }


        if (o2.getDate().isAfter(o1.getDate())) {
            return -1;
        }

        if (o1.getDate().isEqual(o2.getDate())){
            return 0;
        }

        return 0;

    }
}
