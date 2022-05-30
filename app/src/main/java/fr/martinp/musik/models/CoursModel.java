package fr.martinp.musik.models;

import java.time.LocalDateTime;

public class CoursModel {

    LocalDateTime dateTime;
    String instrument;

    public CoursModel(LocalDateTime dateTime, String instrument){
        this.dateTime = dateTime;
        this.instrument = instrument;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
